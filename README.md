# FindIt — Java Spring Boot + MySQL

## Requirements
- JDK 17+
- Maven 3.8+
- MySQL 8+

## Run
1. Create the database using `database.sql`.
2. Edit `src/main/resources/application.properties` and set your MySQL password.
3. In this folder run `mvn spring-boot:run`.
4. Visit http://localhost:8080.
5. Register. To create an admin, register `admin@findit.local` (or promote an account with SQL below).

```sql
UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';
```

## Features
Responsive homepage, login/register/logout, Spring Security + BCrypt, lost/found reports, image upload, searchable gallery, item details, weighted match score, match suggestions, claims, notifications, admin dashboard and claim review.

Uploads are stored in `uploads/items`. Match score is a heuristic and not proof of ownership. Claims should be verified manually.
