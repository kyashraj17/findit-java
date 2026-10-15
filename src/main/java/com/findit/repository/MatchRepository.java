package com.findit.repository;
import com.findit.model.MatchRecord; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface MatchRepository extends JpaRepository<MatchRecord,Long>{
 List<MatchRecord> findByLostItemIdOrderByScoreDesc(Long id);
 Optional<MatchRecord> findByLostItemIdAndFoundItemId(Long lost,Long found);
 long countByScoreGreaterThanEqual(double score);
}