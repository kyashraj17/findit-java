package com.findit.model;
import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="items")
public class Item {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="user_id") private AppUser owner;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ItemType itemType;
 @Column(nullable=false) private String itemName; @Column(nullable=false) private String category;
 private String color; @Column(nullable=false) private String location;
 @Column(columnDefinition="TEXT") private String description; private String imagePath; private LocalDate itemDate;
 @Enumerated(EnumType.STRING) private ItemStatus status=ItemStatus.ACTIVE; private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public AppUser getOwner(){return owner;} public void setOwner(AppUser v){owner=v;}
 public ItemType getItemType(){return itemType;} public void setItemType(ItemType v){itemType=v;}
 public String getItemName(){return itemName;} public void setItemName(String v){itemName=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;}
 public String getColor(){return color;} public void setColor(String v){color=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public String getImagePath(){return imagePath;} public void setImagePath(String v){imagePath=v;}
 public LocalDate getItemDate(){return itemDate;} public void setItemDate(LocalDate v){itemDate=v;}
 public ItemStatus getStatus(){return status;} public void setStatus(ItemStatus v){status=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}