package com.afz.lapstore.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private String buyerName;
    private String laptopBrand;
    private String laptopModel;
    private Double price;
    private String status;
}
