package com.afz.lapstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    private String processor;

    private Integer ram;

    private Integer storage;

    private Double price;

    private String condition;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean sold =  false;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User seller;
}
