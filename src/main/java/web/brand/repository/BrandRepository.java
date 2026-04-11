package web.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.brand.entity.Brand;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);
}