package com.findit.repository;
import com.findit.model.Notification; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface NotificationRepository extends JpaRepository<Notification,Long>{
 List<Notification> findTop8ByUserIdOrderByCreatedAtDesc(Long id); List<Notification> findByUserIdOrderByCreatedAtDesc(Long id);
}