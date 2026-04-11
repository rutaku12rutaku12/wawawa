package web.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.item.dto.ItemCreateDto;
import web.item.service.ItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Long createItem(@RequestBody ItemCreateDto dto) {
        return itemService.createItem(dto);
    }
}