package dev.hsjung.project.services;

import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.entities.purchase.CartListEntity;
import dev.hsjung.project.entities.purchase.ItemEntity;
import dev.hsjung.project.entities.purchase.OrderListEntity;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.interfaces.IResult;
import dev.hsjung.project.mappers.IItemMapper;
import dev.hsjung.project.vos.CartListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dev.hsjung.project.services.ItemService")
public class ItemService {

    private final IItemMapper itemMapper;

    @Autowired
    public ItemService(IItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }


    public ItemEntity[] getItems(){
        return  this.itemMapper.selectItems();
    } //아이템들 main 화면에 가져올 데이터




    public ItemEntity getItem (int index){
    return  this.itemMapper.selectItemByIndex(index);
    }

    public Enum<? extends IResult>list(CartListEntity cartList){
        return this.itemMapper.insertCartList(cartList) > 0 ? CommonResult.SUCCESS : CommonResult.FAILURE;
    }

//    public CartListEntity[] getCart(int index){
//        return this.itemMapper.selectCartList(index);
//    }

    public CartListVo[] getCartItem(int index){
        return this.itemMapper.selectCartListItem(index);
    }



    public Enum<? extends IResult>deleteCartListItem(UserEntity user,int[] index){
        CartListVo[] existingCartList = this.itemMapper.selectCartListByIndex(index);
        System.out.println();
        if(existingCartList == null){
            System.out.println("여기1");
            return CommonResult.FAILURE;
        }
        if(user == null){
            System.out.println("여기2");
            return CommonResult.FAILURE;
        }
        System.out.println("성공");
       for(CartListVo cartListVo : existingCartList){
           this.itemMapper.deleteCartListItemByIndex(cartListVo.getIndex());
           System.out.println(cartListVo.getIndex());
       }
      return CommonResult.SUCCESS;
    }

    public Enum<? extends IResult>orderList(int[] index){
        CartListVo[] existingCartList = this.itemMapper.selectCartListByIndex(index);
        for(CartListVo x : existingCartList){
            OrderListEntity orderList = new OrderListEntity();
            orderList.setCartListIndex(x.getIndex());
            orderList.setUserIndex(x.getUserIndex());
            orderList.setItemIndex(x.getItemIndex());
            orderList.setFinalCount(x.getFinalCount());
            orderList.setFinalPrice(x.getFinalPrice());
            orderList.setItemName(x.getName());


            this.itemMapper.insertOrderList(orderList);
        }
        return CommonResult.SUCCESS;
    }

    public OrderListEntity[] getOrderItem(int index){
        return this.itemMapper.selectOrderListItem(index);
    }








//    public Enum<? extends IResult>modifyCartList(UserEntity user,CartListEntity cartList){
//        if(user == null){
//            return CommonResult.FAILURE;
//        }
////        CartListEntity cartList1 = this.itemMapper.selectCartListItem(cartList.getIndex());
//    }




//    public Enum<? extends IResult>deleteCartListItem(int[] index) {
//        try {
//            CartListVo[] existingCartList = this.itemMapper.selectCartListByIndex(index);
//
//            for (CartListVo cartListVo : existingCartList) {
//                this.itemMapper.deleteCartListItemByIndex(cartListVo.getIndex());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return CommonResult.FAILURE;
//        }
//
//        return CommonResult.SUCCESS;
//    }



}
