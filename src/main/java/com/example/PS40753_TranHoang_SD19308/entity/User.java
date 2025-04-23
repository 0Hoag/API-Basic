package com.example.PS40753_TranHoang_SD19308.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String email;

    @ManyToMany
    Set<Role> roles;

    @Override
    public String toString() {
        return "User{" + "userId='"
                + userId + '\'' + ", username='"
                + username + '\'' + ", email='"
                + email + '\'' + ", rolesCount="
                + (roles != null ? roles.size() : 0) + '}';
    }
}
