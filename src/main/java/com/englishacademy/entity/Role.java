package com.englishacademy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    String name;

    String description;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_name"), // khóa chính của Role
            inverseJoinColumns = @JoinColumn(name = "permission_name") // khóa chính của Permission
    )
    Set<Permission> permissions;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    Date createdAt;

    @Column(name = "created_by", updatable = false)
    String createdBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}
