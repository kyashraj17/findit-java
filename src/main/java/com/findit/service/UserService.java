package com.findit.service;
import com.findit.model.AppUser;
import com.findit.model.Role;
import com.findit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    @Value("${app.admin-email:admin@findit.local}") private String adminEmail;
    public UserService(UserRepository repo, PasswordEncoder encoder) { this.repo=repo; this.encoder=encoder; }
    public void register(AppUser user, String raw) {
        String email=user.getEmail().trim().toLowerCase();
        if(repo.existsByEmailIgnoreCase(email)) throw new IllegalArgumentException("Email already registered.");
        user.setEmail(email);
        user.setPassword(encoder.encode(raw));
        user.setRole(email.equalsIgnoreCase(adminEmail) ? Role.ADMIN : Role.USER);
        repo.save(user);
    }
}