package com.afz.lapstore.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LaptopResponseDTO {
    private Long laptopId;
    private String brand;
    private String model;
    private String processor;
    private Integer ram;
    private Integer storage;
    private Double price;
    private String condition;
    private String imageUrl;
    private Boolean sold;
    private String sellerName;
    private Boolean success;
    private String errorKey;
}
