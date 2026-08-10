package com.afz.lapstore.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LaptopRequestDTO {
    private String brand;
    private String model;
    private String processor;
    private Integer ram;
    private Integer storage;
    private Double price;
    private String condition;
    private String imageUrl;
    private Long sellerId;
}
