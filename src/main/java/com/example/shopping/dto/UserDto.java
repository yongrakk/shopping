package com.example.shopping.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;        //유저 코드
    private String userName;    //유저 이름
    private String userEmail;   //유저 이메일
    private String password;    //유저 패스워드
    private String role;        //유저 권한

}
