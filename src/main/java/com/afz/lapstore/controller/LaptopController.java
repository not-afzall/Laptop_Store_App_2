package com.afz.lapstore.controller;

import com.afz.lapstore.dto.*;
import com.afz.lapstore.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public LaptopListResponseDTO getAllLaptops(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer ram) {

        return laptopService.getAllLaptops(brand, price, ram);
    }

    @GetMapping("/{id}")
    public LaptopResponseDTO getLaptopById(
            @PathVariable Long id) {

        return laptopService.getLaptopById(id);
    }

    @PostMapping
    public LaptopResponseDTO addLaptop(
            @RequestBody LaptopRequestDTO requestDTO) {

        return laptopService.saveLaptop(requestDTO);
    }

    @PutMapping("/{id}")
    public LaptopResponseDTO updateLaptop(
            @PathVariable Long id,
            @RequestBody LaptopRequestDTO requestDTO) {

        return laptopService.updateLaptop(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteLaptop(
            @PathVariable Long id) {

        laptopService.deleteLaptop(id);

        return "Laptop deleted successfully";
    }
}
