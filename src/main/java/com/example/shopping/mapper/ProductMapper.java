package com.example.shopping.mapper;

import com.example.shopping.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    public int selectItemListCount();
    public List<ItemDto> selectItemList(Map<String, Integer> searchMap);

    public ItemDto selectItemById(String id);

}
