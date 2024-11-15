package com.example.processor;

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProcessorUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        System.out.println("Before persisting the ProcessorUser entity");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("After persisting the ProcessorUser entity");
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        System.out.println("Before updating the ProcessorUser entity");
    }

}