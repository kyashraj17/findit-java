package com.findit.repository;
import com.findit.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ClaimRepository extends JpaRepository<Claim,Long>{
 List<Claim> findByClaimantIdOrderByCreatedAtDesc(Long id); List<Claim> findAllByOrderByCreatedAtDesc();
 long countByClaimantId(Long id); long countByStatus(ClaimStatus status);
}