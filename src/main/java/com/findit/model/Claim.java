package com.findit.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="claims")
public class Claim {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="item_id") private Item item;
 @ManyToOne(optional=false) @JoinColumn(name="user_id") private AppUser claimant;
 @Column(nullable=false,columnDefinition="TEXT") private String proof;
 @Enumerated(EnumType.STRING) private ClaimStatus status=ClaimStatus.PENDING; private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public Item getItem(){return item;} public void setItem(Item v){item=v;}
 public AppUser getClaimant(){return claimant;} public void setClaimant(AppUser v){claimant=v;}
 public String getProof(){return proof;} public void setProof(String v){proof=v;}
 public ClaimStatus getStatus(){return status;} public void setStatus(ClaimStatus v){status=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}