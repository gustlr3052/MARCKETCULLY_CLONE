package dev.hsjung.project.entities.purchase;

public class ItemEntity {

    private int index;
    private String name;
    private String price;
    private String shipType;
    private String seller;
    private String packType;
    private String salesUnit;
    private String weight;
    private String allergyInformation;

    private byte[] image;
    private String imageType;

    private int count;


    public int getIndex() {
        return index;
    }

    public ItemEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ItemEntity setPrice(String price) {
        this.price = price;
        return this;
    }


    public String getSeller() {
        return seller;
    }

    public ItemEntity setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public byte[] getImage() {
        return image;
    }

    public ItemEntity setImage(byte[] image) {
        this.image = image;
        return this;
    }

    public String getImageType() {
        return imageType;
    }

    public ItemEntity setImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public String getPackType() {
        return packType;
    }

    public ItemEntity setPackType(String packType) {
        this.packType = packType;
        return this;
    }

    public String getShipType() {
        return shipType;
    }

    public ItemEntity setShipType(String shipType) {
        this.shipType = shipType;
        return this;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public ItemEntity setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public ItemEntity setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getAllergyInformation() {
        return allergyInformation;
    }

    public ItemEntity setAllergyInformation(String allergyInformation) {
        this.allergyInformation = allergyInformation;
        return this;
    }

    public int getCount() {
        return count;
    }

    public ItemEntity setCount(int count) {
        this.count = count;
        return this;
    }
}
