package dev.hsjung.project.controllers;


import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.entities.purchase.CartListEntity;
import dev.hsjung.project.entities.purchase.ItemEntity;
import dev.hsjung.project.entities.purchase.OrderListEntity;
import dev.hsjung.project.enums.CartResult;
import dev.hsjung.project.services.ItemService;
import dev.hsjung.project.services.MemberService;
import dev.hsjung.project.vos.CartListVo;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/main")
public class HomeController {

    private final MemberService memberService;
    private final ItemService itemService;

    @Autowired
    public HomeController(MemberService memberService,ItemService itemService){
        this.memberService = memberService;
        this.itemService = itemService;
    }

    @RequestMapping(value = "main",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getMain(){
        ModelAndView modelAndView = new ModelAndView("main/main");
        ItemEntity[] items = this.itemService.getItems();
        System.out.println(items.length); // 입력한 DB 갯수가 다 넘어오는지 확인하기 위함 thymeleaf 불러오기 위해서
        modelAndView.addObject("items",items);
        return modelAndView;
    }

    @RequestMapping(value = "goods",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getGood(@RequestParam(value = "id")int id){
        ModelAndView modelAndView = new ModelAndView("goods/good");
        ItemEntity item = this.itemService.getItem(id);
        System.out.println(item);
        modelAndView.addObject("item",item);
        return modelAndView;
    }


    @GetMapping(value = "goodImage") // GetMapping = method:RequestMet :get  + ResponseBody 임
    public ResponseEntity<byte[]> getGoodsImage(@RequestParam(value = "gi")int index) {
        ResponseEntity<byte[]> responseEntity;
        ItemEntity item = this.itemService.getItem(index);
        if(item == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(item.getImageType()));
            headers.setContentLength(item.getImage().length);
            responseEntity = new ResponseEntity<>(item.getImage(),headers,HttpStatus.OK);
        }

        return  responseEntity;
    }

    @RequestMapping(value = "cart",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postCart(@SessionAttribute(value = "user",required = false) UserEntity user,
                           @RequestParam(value = "name",required = false)String name,
                           @RequestParam(value = "gid",required = false)String gid,
                           @RequestParam(value = "count",required = false)int count,
                           @RequestParam(value = "totalPrice",required = false)String totalPrice,
                           CartListEntity cartList){
        Enum<?> result;
        JSONObject responseObject = new JSONObject();
        if(user == null){
            result = CartResult.NOT_ALLOWED;
        }else {
            cartList.setUserIndex(user.getIndex());
            cartList.setName(name);
            cartList.setItemIndex(Integer.parseInt(gid));
            cartList.setFinalCount(count);
            cartList.setFinalPrice(totalPrice);
            System.out.println(user);
            result = this.itemService.list(cartList);
        }
        responseObject.put("result",result.name().toLowerCase());
        return responseObject.toString();
    }









}
