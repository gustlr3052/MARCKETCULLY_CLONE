package dev.hsjung.project.vos;

import dev.hsjung.project.entities.purchase.CartListEntity;

public class CartListVo extends CartListEntity {
    private String unitPrice;
    private byte[] itemImage;
    private String itemImageType;

    public String getUnitPrice() {
        return unitPrice;
    }

    public CartListVo setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }


    public byte[] getItemImage() {
        return itemImage;
    }

    public CartListVo setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
        return this;
    }

    public String getItemImageType() {
        return itemImageType;
    }

    public CartListVo setItemImageType(String itemImageType) {
        this.itemImageType = itemImageType;
        return this;
    }
}
