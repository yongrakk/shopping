package com.example.shopping.dto;

import lombok.Data;

@Data
public class ItemDto {

    private Long itemId;            //상품코드
    private Integer itemCateId;     //카테고리코드
    private String itemCateName;    //카테고리명
    private String itemName;        //상품명
    private String itemThumbnail1;  //대표이미지
    private String itemThumbnail2;  //추가이미지1
    private String itemThumbnail3;  //추가이미지2
    private String itemThumbnail4;  //추가이미지3
    private String itemThumbnail5;  //추가이미지4
    private String itemDetail;      //상품상세설명
    private Integer itemOriPrice;   //상품원가
    private Integer itemSalePrice;  //상품할인가
    private Integer itemSaleRate;   //상품할인율
    private String itemCompany;     //상품제조사
    private String itemDistributor; //상품유통사
    private String isLikeItem ="N"; //찜하기 상품여부

}
