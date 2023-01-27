package dev.hsjung.project.controllers;


import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.entities.purchase.CartListEntity;
import dev.hsjung.project.entities.purchase.ItemEntity;
import dev.hsjung.project.entities.purchase.OrderListEntity;
import dev.hsjung.project.enums.CartResult;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.services.ItemService;
import dev.hsjung.project.services.MemberService;
import dev.hsjung.project.vos.CartListVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/cart")
public class CartListController {

    private final MemberService memberService;
    private final ItemService itemService;

    @Autowired
    public CartListController(MemberService memberService, ItemService itemService){
        this.memberService = memberService;
        this.itemService = itemService;
    }




    @GetMapping(value = "cart")
    public ModelAndView getCart(@SessionAttribute(value = "user",required = false)UserEntity user){

       if(user == null){
           ModelAndView modelAndView = new ModelAndView("redirect:/member/login");
           return modelAndView;
       }

        ModelAndView modelAndView = new ModelAndView("cart/cart");
        CartListVo[] carts = this.itemService.getCartItem(user.getIndex());
        modelAndView.addObject("carts",carts);
        return modelAndView;
    }
    @GetMapping(value = "order")
    public ModelAndView getOrder(@SessionAttribute(value = "user",required = false)UserEntity user){
        if(user == null){
            ModelAndView modelAndView = new ModelAndView("redirect:/member/login");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("orderList/order");
        OrderListEntity[] orders = this.itemService.getOrderItem(user.getIndex());
        modelAndView.addObject("orders",orders);
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

    @DeleteMapping(value = "cart")
    @ResponseBody
    public String deleteCartListItem(@SessionAttribute(value = "user") UserEntity user, int[] index){
        Enum<?> result = this.itemService.deleteCartListItem(user,index);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result",result.name().toLowerCase());
        if(result == CommonResult.SUCCESS){
            System.out.println("삭제 성공");
        }
        return  responseObject.toString();
    }





    @RequestMapping(value = "order",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  String postOrder(@SessionAttribute(value = "user",required = false)UserEntity user, OrderListEntity orderList, CartListEntity cartList, ItemEntity item,int[] index){
        Enum<?> result;
        JSONObject responseObject = new JSONObject();
        if(user == null){
            result=CartResult.NOT_ALLOWED;
        }else{
            orderList.setCartListIndex(cartList.getIndex());
            orderList.setUserIndex(user.getIndex());
            orderList.setItemIndex(item.getIndex());
            orderList.setFinalPrice(cartList.getFinalPrice());
            orderList.setFinalCount(cartList.getFinalCount());
            orderList.setItemName(cartList.getName());

            result = this.itemService.orderList(index);
        }
        responseObject.put("result",result.name().toLowerCase());
        return responseObject.toString();
    }





}
