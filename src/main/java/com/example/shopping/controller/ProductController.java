package com.example.shopping.controller;

import com.example.shopping.dto.ItemDto;
import com.example.shopping.dto.LikeItem;
import com.example.shopping.dto.Pagination;
import com.example.shopping.service.ProductServiceImpl;
import com.example.shopping.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    /**
     * 상품리스트
     * 로그인 여부로 찜 상품 조회
     * @param page
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView mainList(@RequestParam(defaultValue = "1" ,required = false) int page) {
        ModelAndView mv = new ModelAndView();

        //페이지 처리
        int totalListCount = productService.selectItemListCount();
        Pagination pagination = new Pagination(totalListCount, page);

        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        Map<String, Integer> searchMap = new HashMap<>();
        searchMap.put("startIndex", startIndex);
        searchMap.put("pageSize", pageSize);

        //상품리스트 조회
        List<ItemDto> ItemList = productService.selectItemList(searchMap);

        String isLogin = "N";
        //로그인 유저 정보 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //로그인 되었을때 찜한 상품여부
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            isLogin = "Y";
            List<LikeItem> likeItemList = userService.selectMainLikeList(username);

            for (ItemDto itemDto : ItemList) {
                for (LikeItem likeItem : likeItemList) {
                    if (itemDto.getItemId() == likeItem.getItemId()) {
                        itemDto.setIsLikeItem("Y");
                    }
                }
            }
        }

        mv.addObject("pagination", pagination);
        mv.addObject("isLogin", isLogin);
        mv.addObject("ItemList", ItemList);
        mv.setViewName("content/main");

        return mv;
    }

    /**
     * 상품 상세보기
     * 로그인 되었을때 찜한 상품인지 체크
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/productView", method = RequestMethod.GET)
    public ModelAndView itemView(
            @RequestParam(value = "id", required = false) String id
    ) {
        ModelAndView mv = new ModelAndView();

        //상품 코드로 상세 조회
        ItemDto itemDto = productService.selectItemById(id);

        String isLogin = "N";

        //로그인 유저 조회
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //로그인 되었을때 찜한 상품여부
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            isLogin = "Y";
            List<LikeItem> likeItemList = userService.selectMainLikeList(username);

            for (LikeItem likeItem : likeItemList) {
                if (likeItem.getItemId() == itemDto.getItemId()) {
                    itemDto.setIsLikeItem("Y");
                }
            }
        }

        mv.addObject("isLogin", isLogin);
        mv.addObject("itemInfo", itemDto);
        mv.setViewName("content/itemView");
        return mv;
    }
}