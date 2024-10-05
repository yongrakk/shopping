package com.example.shopping.service;

import com.example.shopping.dto.ItemDto;
import com.example.shopping.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductMapper productMapper;

    @Override
    public int selectItemListCount() {
        return productMapper.selectItemListCount();
    }

    @Override
    public List<ItemDto> selectItemList(Map<String, Integer> searchMap) {
        return productMapper.selectItemList(searchMap);
    }

    @Override
    public ItemDto selectItemById(String id) {
        return productMapper.selectItemById(id);
    }
}
