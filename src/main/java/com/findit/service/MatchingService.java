package com.findit.service;
import com.findit.model.*;
import com.findit.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class MatchingService {
    private final ItemRepository items;
    private final MatchRepository matches;
    private final NotificationService notifications;
    public MatchingService(ItemRepository items, MatchRepository matches, NotificationService notifications) {
        this.items=items; this.matches=matches; this.notifications=notifications;
    }
    private String norm(String s){return s==null?"":s.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9 ]"," ").replaceAll("\\\\s+"," ").trim();}
    private double similarity(String a,String b){
        Set<String>x=new HashSet<>(Arrays.asList(norm(a).split(" ")));
        Set<String>y=new HashSet<>(Arrays.asList(norm(b).split(" ")));
        x.remove("");y.remove("");x.removeIf(t->t.length()<2);y.removeIf(t->t.length()<2);
        if(x.isEmpty()||y.isEmpty())return 0;
        if(norm(a).equals(norm(b)))return 100;
        if(norm(a).contains(norm(b))||norm(b).contains(norm(a)))return 80;
        Set<String>inter=new HashSet<>(x);inter.retainAll(y);
        Set<String>union=new HashSet<>(x);union.addAll(y);
        return 100.0*inter.size()/union.size();
    }
    private double field(String a,String b){if(norm(a).isEmpty()||norm(b).isEmpty())return 0;if(norm(a).equals(norm(b)))return 100;return similarity(a,b);}
    public double score(Item lost,Item found){
        double date=50;
        if(lost.getItemDate()!=null&&found.getItemDate()!=null)date=Math.max(0,100-Math.abs(ChronoUnit.DAYS.between(lost.getItemDate(),found.getItemDate()))*12);
        double score=field(lost.getCategory(),found.getCategory())*.30+field(lost.getColor(),found.getColor())*.20+
            field(lost.getLocation(),found.getLocation())*.20+similarity(lost.getItemName(),found.getItemName())*.15+
            similarity(lost.getDescription(),found.getDescription())*.10+date*.05;
        return Math.round(Math.min(100,Math.max(0,score))*100.0)/100.0;
    }
    @Transactional
    public List<MatchRecord> findMatches(Item lost){
        List<MatchRecord> out=new ArrayList<>();
        for(Item found:items.findByItemTypeAndStatus(ItemType.FOUND,ItemStatus.ACTIVE)){
            double score=score(lost,found); if(score<35)continue;
            MatchRecord record=matches.findByLostItemIdAndFoundItemId(lost.getId(),found.getId()).orElseGet(MatchRecord::new);
            record.setLostItem(lost);record.setFoundItem(found);record.setScore(score);out.add(matches.save(record));
            if(score>=60)notifications.create(lost.getOwner(),"Possible match for '"+lost.getItemName()+"': "+score+"% similarity.");
        }
        out.sort(Comparator.comparingDouble(MatchRecord::getScore).reversed());return out;
    }
    @Transactional
    public void compareFound(Item found){
        for(Item lost:items.findByItemTypeAndStatus(ItemType.LOST,ItemStatus.ACTIVE)){
            double score=score(lost,found);if(score<35)continue;
            MatchRecord record=matches.findByLostItemIdAndFoundItemId(lost.getId(),found.getId()).orElseGet(MatchRecord::new);
            record.setLostItem(lost);record.setFoundItem(found);record.setScore(score);matches.save(record);
            if(score>=60)notifications.create(lost.getOwner(),"New found item may match '"+lost.getItemName()+"': "+score+"%.");
        }
    }
}