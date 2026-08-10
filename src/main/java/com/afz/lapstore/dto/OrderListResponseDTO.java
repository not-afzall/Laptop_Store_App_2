package com.afz.lapstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderListResponseDTO {

    private List<OrderResponseDTO> orders;

    private Map<String, Object> meta;
}
