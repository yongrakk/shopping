package com.example.shopping.dto;

import lombok.Data;

@Data
public class LikeItem {

    private Long itemId;            //아이템코드
    private String itemName;        //상품명
    private String  itemThumbnail1; //상품 대표이미지
    private Integer itemOriPrice;   //상품원가
    private Integer itemSalePrice;  //상품할인가
    private Integer itemSaleRate;   //상품할인율
    private String itemDistributor; //상품유통사

}
