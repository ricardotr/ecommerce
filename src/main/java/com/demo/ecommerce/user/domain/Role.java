package com.demo.ecommerce.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @NonNull
    @Id
    @Column(nullable = false, unique = true)
    private String name;  // ROLE_USER, ROLE_ADMIN
}
