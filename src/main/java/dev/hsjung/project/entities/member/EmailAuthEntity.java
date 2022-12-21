package dev.hsjung.project.entities.member;

import java.util.Date;

public class EmailAuthEntity {

    private int index;
    private String email;
    private String code;
    private String salt;
    private Date createdOn;
    private Date expiresOn;
    private boolean isExpired;

    public int getIndex() {
        return index;
    }

    public EmailAuthEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmailAuthEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCode() {
        return code;
    }

    public EmailAuthEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public EmailAuthEntity setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public EmailAuthEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public EmailAuthEntity setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public EmailAuthEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }
}
