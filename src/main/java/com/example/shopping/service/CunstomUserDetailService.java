package com.example.shopping.service;

import com.example.shopping.dto.UserDto;
import com.example.shopping.dto.UserRole;
import com.example.shopping.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CunstomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Spring Security login-form 처리
     * @param userNameOrEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

        //유저 이름으로 DB 유저 조회
        UserDto userDto = userMapper.findByName(userNameOrEmail);

        if (userDto != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            //유저 권한 설정
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(userDto.getUserName(),userDto.getPassword(), authorities);

            return user;
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

}
