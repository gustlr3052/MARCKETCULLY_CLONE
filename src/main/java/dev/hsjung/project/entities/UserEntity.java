package dev.hsjung.project.entities;

import java.util.Date;
import java.util.Objects;

public class UserEntity {   //DB에 넣은 USER 테이블 객체 만들기

    private int index;
    private String email;
    private String password;;
    private String nickname;
    private String name;
    private String contact;
    private String addressPostal;
    private String addressPrimary;
    private String addressSecondary;
    private Date registeredOn;



    public int getIndex() {
        return index;
    }

    public UserEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserEntity setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public UserEntity setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getAddressPostal() {
        return addressPostal;
    }

    public UserEntity setAddressPostal(String addressPostal) {
        this.addressPostal = addressPostal;
        return this;
    }

    public String getAddressPrimary() {
        return addressPrimary;
    }

    public UserEntity setAddressPrimary(String addressPrimary) {
        this.addressPrimary = addressPrimary;
        return this;
    }

    public String getAddressSecondary() {
        return addressSecondary;
    }

    public UserEntity setAddressSecondary(String addressSecondary) {
        this.addressSecondary = addressSecondary;
        return this;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public UserEntity setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    // ▼ 해쉬함수

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        return this.index == ((UserEntity)o).index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, email, password, nickname, name, contact, addressPostal, addressPrimary, addressSecondary, registeredOn);
    }
}
