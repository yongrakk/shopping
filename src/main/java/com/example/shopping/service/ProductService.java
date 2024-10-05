package com.example.shopping.service;

import com.example.shopping.dto.ItemDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public int selectItemListCount();

    public List<ItemDto> selectItemList(Map<String, Integer> searchMap);

    public ItemDto selectItemById(String id);

}