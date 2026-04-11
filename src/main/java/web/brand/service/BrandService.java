package web.brand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.brand.entity.Brand;
import web.brand.repository.BrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> searchBrand(String keyword) {
        return brandRepository.findAll().stream()
                .filter(b -> b.getName().contains(keyword))
                .limit(10)
                .toList();
    }
}