package dev.hsjung.project.entities.purchase;

public class CartListEntity {

    private int index;
    private int itemIndex;
    private int userIndex;
    private String itemName;
    private int finalCount;

    private String finalPrice;

    public int getIndex() {
        return index;
    }

    public CartListEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public CartListEntity setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public CartListEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public String getName() {
        return itemName;
    }

    public CartListEntity setName(String itemName) {
        this.itemName = itemName;
        return this;
    }


    public int getFinalCount() {
        return finalCount;
    }

    public CartListEntity setFinalCount(int finalCount) {
        this.finalCount = finalCount;
        return this;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public CartListEntity setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
        return this;
    }
}
