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

/**
 * Represents a simple domain model for the Role entity.
 * 
 * <p>This class maps to the "role" table and contains
 * role names.
 * 
 * <p>Jackson requires a no-args constructor to deserialize JSON data,
 * that is why {@link lombok.NoArgsConstructor} is included. Additionally,
 * {@link lombok.Data} is used instead of {@link lombok.Value} or Java {@code record},
 * because JPA entity classes require mutability for ORM operations.
 */
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
    private String name;
}
