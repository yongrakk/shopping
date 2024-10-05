package com.example.shopping.mapper;

import com.example.shopping.dto.LikeItem;
import com.example.shopping.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    public void saveUser(UserDto userDto);

    public UserDto findByName(String name);

    public void saveLikeItem(Map<String,String> likeMap);

    public void removeLikeItem(Map<String,String> likeMap);

    public int selectLikeListCount(String userName);

    public List<LikeItem> selectMainLikeList(String userName);

    public List<LikeItem> selectLikeList(Map<String, Object> searchMap);

}
