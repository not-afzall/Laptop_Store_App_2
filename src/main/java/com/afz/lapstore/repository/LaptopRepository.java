package com.afz.lapstore.repository;

import com.afz.lapstore.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findByBrandIgnoreCase(String brand);

    List<Laptop> findByPriceLessThanEqual(Double price);

    List<Laptop> findByRamGreaterThanEqual(Integer ram);

    List<Laptop> findBySellerId(Long sellerId);
}
