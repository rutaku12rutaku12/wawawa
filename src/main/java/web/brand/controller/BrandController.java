package web.brand.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.brand.entity.Brand;
import web.brand.service.BrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/search")
    public List<Brand> search(@RequestParam String keyword) {
        return brandService.searchBrand(keyword);
    }
}