package com.findit.repository;
import com.findit.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ItemRepository extends JpaRepository<Item,Long>{
 List<Item> findTop8ByStatusOrderByCreatedAtDesc(ItemStatus status);
 List<Item> findByOwnerIdOrderByCreatedAtDesc(Long id);
 List<Item> findByItemTypeAndStatus(ItemType type,ItemStatus status);
 long countByOwnerIdAndItemType(Long id,ItemType type); long countByItemType(ItemType type);
}