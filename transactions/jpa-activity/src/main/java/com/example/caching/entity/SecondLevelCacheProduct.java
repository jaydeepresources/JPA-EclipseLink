package com.example.caching.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Cache(type = CacheType.SOFT, size = 64000, shared = true)
public class SecondLevelCacheProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column
    private Double discount;

    @Column
    private Integer sales;

    @Column
    private Double rating;
}
