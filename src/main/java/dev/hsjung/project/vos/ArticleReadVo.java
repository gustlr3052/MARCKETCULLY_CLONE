package dev.hsjung.project.vos;

import dev.hsjung.project.entities.bbs.ArticlesEntity;

public class ArticleReadVo extends ArticlesEntity {
    private String userNickName;

    public String getUserNickName() {
        return userNickName;
    }

    public ArticleReadVo setUserNickName(String userNickName) {
        this.userNickName = userNickName;
        return this;
    }
}
