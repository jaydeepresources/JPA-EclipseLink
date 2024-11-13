package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GST {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String gstNumber;

    @OneToOne(mappedBy = "gst", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Company company;
}