package dev.hsjung.project.entities.bbs;

public class BoardsEntity {

    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public BoardsEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public BoardsEntity setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object o){
        if(this == o ){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        return  this.id == ((BoardsEntity)o).id;
    }
}
