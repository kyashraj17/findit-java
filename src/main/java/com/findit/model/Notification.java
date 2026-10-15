package com.findit.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="notifications")
public class Notification {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="user_id") private AppUser user;
 @Column(nullable=false,columnDefinition="TEXT") private String message; private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;}
 public String getMessage(){return message;} public void setMessage(String v){message=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}