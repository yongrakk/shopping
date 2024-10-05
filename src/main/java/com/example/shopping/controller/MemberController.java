package com.example.shopping.controller;

import com.example.shopping.dto.LikeItem;
import com.example.shopping.dto.Pagination;
import com.example.shopping.dto.UserDto;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * index 접근 시 User 생성 후 메인으로 이동
     *
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();

        //test 유저 조회
        UserDto userDtoEx = userService.findByName("test");

        //유저 테이블에 test 유저가 없을때 생성 처리
        if(userDtoEx == null){
            UserDto userDto = new UserDto();
            userDto.setUserName("test");
            userDto.setPassword(passwordEncoder.encode("1234"));
            userDto.setUserEmail("test@Test.com");
            userService.saveUser(userDto);
        }

        mv.setViewName("redirect:/main");
        return mv;
    }

    /**
     * 로그인 처리 페이지
     * 로그인 완료 시 이전 요청 페이지로 이동
     * @param request
     * @return
     */
    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "exception", required = false) String exception,
                              HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        //로그인 처리 후 요청 전 페이지 이동
        String uri = request.getHeader("Referer");
        if(uri == null){
            uri ="/main";
        }
        request.getSession().setAttribute("prevPage", uri);

        mv.addObject("error",error);
        mv.addObject("exception",exception);

        mv.setViewName("content/loginForm");
        return mv;
    };

    /**
     * 찜하기 추가
     * @param id
     * @return
     */
    @RequestMapping(value = "/likeAdd",method = RequestMethod.GET)
    public String likeAdd(@RequestParam("itemId") String id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails)principal).getUsername();

        Map<String, String> likeMap = new HashMap<>();
        likeMap.put("userName", userName);
        likeMap.put("itemId",id);
        userService.saveLikeItem(likeMap);
        return "success";
    }

    /**
     * 찜하기 해제
     * @param id
     * @return
     */
    @RequestMapping(value = "/likeRemove",method = RequestMethod.GET)
    public String likeRemove(@RequestParam("itemId") String id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails)principal).getUsername();

        Map<String, String> likeMap = new HashMap<>();
        likeMap.put("userName", userName);
        likeMap.put("itemId",id);
        userService.removeLikeItem(likeMap);
        return "success";
    }

    /**
     * 유저별 찜하기 리스트
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/userLikeList",method = RequestMethod.GET)
    public ModelAndView userLikeList(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();

        //페이지 처리
        int totalListCount = userService.selectLikeListCount(userName);

        Pagination pagination = new Pagination(totalListCount,page);

        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("userName",userName);
        searchMap.put("startIndex",startIndex);
        searchMap.put("pageSize",pageSize);

        //찜하기 리스트 조회
        List<LikeItem> likeItemList = userService.selectLikeList(searchMap);

        mv.addObject("pagination",pagination);
        mv.addObject("userLikeItemList",likeItemList);
        mv.setViewName("content/userLikeList");

        return mv;
    }

}
