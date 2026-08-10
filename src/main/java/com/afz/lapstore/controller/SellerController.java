package com.afz.lapstore.controller;

import com.afz.lapstore.dto.LaptopListResponseDTO;
import com.afz.lapstore.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping("/dashboard/{sellerId}")
    public LaptopListResponseDTO dashboard(
            @PathVariable Long sellerId) {

        return laptopService.getMyLaptops(sellerId);
    }
}
