package dev.hsjung.project.entities.purchase;

public class OrderListEntity {


    private int index;
    private int userIndex;
    private int itemIndex;

    private String itemName;

    private int cartListIndex;
    private String userName;
    private int finalCount;
    private String finalPrice;




    public int getUserIndex() {
        return userIndex;
    }

    public OrderListEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }



    public int getFinalCount() {
        return finalCount;
    }

    public OrderListEntity setFinalCount(int finalCount) {
        this.finalCount = finalCount;
        return this;
    }

    public String getFinalPrice() {
        return finalPrice;
    }
    public OrderListEntity setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
        return this;
    }


    public String getUserName() {
        return userName;
    }

    public OrderListEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public int getIndex() {
        return index;
    }

    public OrderListEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public OrderListEntity setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
        return this;
    }

    public int getCartListIndex() {
        return cartListIndex;
    }

    public OrderListEntity setCartListIndex(int cartListIndex) {
        this.cartListIndex = cartListIndex;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public OrderListEntity setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }
}
