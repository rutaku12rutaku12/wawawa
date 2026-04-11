package web.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.brand.entity.Brand;
import web.brand.repository.BrandRepository;
import web.item.dto.ItemCreateDto;
import web.item.entity.Item;
import web.item.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public Long createItem(ItemCreateDto dto) {

        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("브랜드 없음"));

        Item item = new Item(
                dto.getName(),
                dto.getCategory(),
                dto.getImageUrl(),
                dto.getPrice(),
                dto.getLink(),
                dto.getDescription(),
                brand
        );

        itemRepository.save(item);

        return item.getId();
    }
}