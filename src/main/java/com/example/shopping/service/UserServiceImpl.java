package com.example.shopping.service;


import com.example.shopping.dto.LikeItem;
import com.example.shopping.dto.UserDto;
import com.example.shopping.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(UserDto userDto) {
        userMapper.saveUser(userDto);
    }

    @Override
    public UserDto findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public void saveLikeItem(Map<String, String> likeMap) {
        userMapper.saveLikeItem(likeMap);
    }

    @Override
    public void removeLikeItem(Map<String, String> likeMap) {
        userMapper.removeLikeItem(likeMap);
    }

    @Override
    public int selectLikeListCount(String userName) {
        return userMapper.selectLikeListCount(userName);
    }

    @Override
    public List<LikeItem> selectMainLikeList(String userName) {
        return userMapper.selectMainLikeList(userName);
    }
    @Override
    public List<LikeItem> selectLikeList(Map<String, Object> searchMap) {
        return userMapper.selectLikeList(searchMap);
    }
}
