package dev.hsjung.project.entities.bbs;

import java.util.Date;

public class ArticlesEntity {

    private  int index;
    private String userEmail;
    private String boardId;
    private String title;
    private String content;
    private int view;
    private Date writtenOn;
    private Date modifiedOn;


    public int getIndex() {
        return index;
    }

    public ArticlesEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public ArticlesEntity setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getBoardId() {
        return boardId;
    }

    public ArticlesEntity setBoardId(String boardId) {
        this.boardId = boardId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticlesEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticlesEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public int getView() {
        return view;
    }

    public ArticlesEntity setView(int view) {
        this.view = view;
        return this;
    }

    public Date getWrittenOn() {
        return writtenOn;
    }

    public ArticlesEntity setWrittenOn(Date writtenOn) {
        this.writtenOn = writtenOn;
        return this;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public ArticlesEntity setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }
}
