package dev.hsjung.project.mappers;

import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.entities.purchase.CartListEntity;
import dev.hsjung.project.entities.purchase.ItemEntity;
import dev.hsjung.project.entities.purchase.OrderListEntity;
import dev.hsjung.project.vos.CartListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IItemMapper {


    ItemEntity[] selectItems(); // items들을 배열로 객체화해서 불러오기

    ItemEntity selectItemByIndex(@Param(value = "index")int index);

    int insertCartList(CartListEntity cartList);

    CartListEntity[] selectCartList(@Param(value = "userIndex")int index);

    CartListVo[] selectCartListItem(@Param(value = "userIndex")int index); // 기존의 CartListEntity에서 없는 entityPrice를 가져와서 확장하기 위함

    CartListVo[] selectCartListByIndex(@Param(value = "index")int[] index);

    int deleteCartListItemByIndex(@Param(value = "index")int index);

    int insertOrderList(OrderListEntity orderList);

    OrderListEntity[] selectOrderListItem(@Param(value ="userIndex")int index);






}
