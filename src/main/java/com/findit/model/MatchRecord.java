package com.findit.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="matches",uniqueConstraints=@UniqueConstraint(columnNames={"lost_item_id","found_item_id"}))
public class MatchRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="lost_item_id") private Item lostItem;
 @ManyToOne(optional=false) @JoinColumn(name="found_item_id") private Item foundItem;
 private double score; private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public Item getLostItem(){return lostItem;} public void setLostItem(Item v){lostItem=v;}
 public Item getFoundItem(){return foundItem;} public void setFoundItem(Item v){foundItem=v;}
 public double getScore(){return score;} public void setScore(double v){score=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}