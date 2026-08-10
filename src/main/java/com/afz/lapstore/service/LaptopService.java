package com.afz.lapstore.service;

import com.afz.lapstore.dto.*;
import com.afz.lapstore.entity.*;
import com.afz.lapstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private UserRepository userRepository;

    public LaptopListResponseDTO getAllLaptops(
            String brand,
            Double price,
            Integer ram) {

        List<Laptop> laptops;

        if (brand != null) {

            laptops = laptopRepository.findByBrandIgnoreCase(brand);

        } else if (price != null) {

            laptops = laptopRepository.findByPriceLessThanEqual(price);

        } else if (ram != null) {

            laptops = laptopRepository.findByRamGreaterThanEqual(ram);

        } else {

            laptops = laptopRepository.findAll();
        }

        List<LaptopResponseDTO> laptopResponseDTOs = laptops
                .stream()
                .map(this::convertToResponseDTO)
                .toList();

        LaptopListResponseDTO responseDTO = new LaptopListResponseDTO();
        responseDTO.setLaptops(laptopResponseDTOs);

        return responseDTO;
    }

    public LaptopResponseDTO getLaptopById(Long id) {

        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));

        return convertToResponseDTO(laptop);
    }

    public LaptopResponseDTO saveLaptop(LaptopRequestDTO requestDTO) {

        Laptop laptop = new Laptop();

        laptop.setBrand(requestDTO.getBrand());
        laptop.setModel(requestDTO.getModel());
        laptop.setProcessor(requestDTO.getProcessor());
        laptop.setRam(requestDTO.getRam());
        laptop.setStorage(requestDTO.getStorage());
        laptop.setPrice(requestDTO.getPrice());
        laptop.setCondition(requestDTO.getCondition());
        laptop.setImageUrl(requestDTO.getImageUrl());
        laptop.setSold(false);

        if (requestDTO.getSellerId() != null) {

            User seller = userRepository.findById(requestDTO.getSellerId())
                    .orElseThrow(() -> new RuntimeException("Seller not found"));

            laptop.setSeller(seller);
        }

        laptop = laptopRepository.save(laptop);

        return convertToResponseDTO(laptop);
    }

    public LaptopListResponseDTO getMyLaptops(Long sellerId) {

        List<LaptopResponseDTO> laptops = laptopRepository
                .findBySellerId(sellerId)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();

        LaptopListResponseDTO responseDTO = new LaptopListResponseDTO();

        responseDTO.setLaptops(laptops);

        return responseDTO;
    }

    public LaptopResponseDTO updateLaptop(Long id, LaptopRequestDTO requestDTO) {

        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));

        laptop.setBrand(requestDTO.getBrand());
        laptop.setModel(requestDTO.getModel());
        laptop.setProcessor(requestDTO.getProcessor());
        laptop.setRam(requestDTO.getRam());
        laptop.setStorage(requestDTO.getStorage());
        laptop.setPrice(requestDTO.getPrice());
        laptop.setCondition(requestDTO.getCondition());
        laptop.setImageUrl(requestDTO.getImageUrl());

        laptop = laptopRepository.save(laptop);

        return convertToResponseDTO(laptop);
    }

    public void deleteLaptop(Long id) {

        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));

        laptopRepository.delete(laptop);
    }

    private LaptopResponseDTO convertToResponseDTO(Laptop laptop) {

        LaptopResponseDTO responseDTO = new LaptopResponseDTO();

        responseDTO.setLaptopId(laptop.getId());
        responseDTO.setBrand(laptop.getBrand());
        responseDTO.setModel(laptop.getModel());
        responseDTO.setProcessor(laptop.getProcessor());
        responseDTO.setRam(laptop.getRam());
        responseDTO.setStorage(laptop.getStorage());
        responseDTO.setPrice(laptop.getPrice());
        responseDTO.setCondition(laptop.getCondition());
        responseDTO.setImageUrl(laptop.getImageUrl());
        responseDTO.setSold(laptop.getSold());

        if (laptop.getSeller() != null) {
            responseDTO.setSellerName(laptop.getSeller().getName());
        }

        return responseDTO;
    }
}
