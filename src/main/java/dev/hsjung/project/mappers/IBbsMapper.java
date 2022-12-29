package dev.hsjung.project.mappers;

import dev.hsjung.project.entities.bbs.ArticlesEntity;
import dev.hsjung.project.entities.bbs.BoardsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IBbsMapper {

    BoardsEntity selectBoardById(@Param(value = "id")String id);

    int insertArticles(ArticlesEntity article);

}
