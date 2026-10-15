package com.findit.service;
import com.findit.model.AppUser;
import com.findit.model.Notification;
import com.findit.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository repo;
    public NotificationService(NotificationRepository repo){this.repo=repo;}
    public void create(AppUser user,String message){Notification n=new Notification();n.setUser(user);n.setMessage(message);repo.save(n);}
}