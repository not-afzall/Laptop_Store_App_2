package com.afz.lapstore.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LaptopListResponseDTO {
    private List<LaptopResponseDTO> laptops;
}
