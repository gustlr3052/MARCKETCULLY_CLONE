package dev.hsjung.project.vos;

import dev.hsjung.project.entities.bbs.ArticlesEntity;

public class ArticleReadVo extends ArticlesEntity {
    private String userNickname;

    public String getUserNickname() {
        return userNickname;
    }

    public ArticleReadVo setUserNickname(String userNickname) {
        this.userNickname = userNickname;
        return this;
    }
}
