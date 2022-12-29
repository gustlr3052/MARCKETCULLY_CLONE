package dev.hsjung.project.mappers;

import dev.hsjung.project.entities.bbs.ArticlesEntity;
import dev.hsjung.project.entities.bbs.BoardsEntity;
import dev.hsjung.project.vos.ArticleReadVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IBbsMapper {

    BoardsEntity selectBoardById(@Param(value = "id")String id);

    int insertArticles(ArticlesEntity article);

    ArticleReadVo[] selectArticlesByBoardId(@Param(value="boardId")String boardId);


}
