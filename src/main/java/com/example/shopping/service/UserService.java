package com.example.shopping.service;

import com.example.shopping.dto.LikeItem;
import com.example.shopping.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    public void saveUser(UserDto userDto);
    public UserDto findByName(String name);

    public void saveLikeItem(Map<String,String> likeMap);

    public void removeLikeItem(Map<String,String> likeMap);

    public int selectLikeListCount(String userName);

    public List<LikeItem> selectMainLikeList(String UserName);
    public List<LikeItem> selectLikeList(Map<String, Object> searchMap);

}
