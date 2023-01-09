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

    ArticleReadVo[] selectArticlesByBoardId(@Param(value="boardId")String boardId); //게시판에 게시글 넣기

    ArticleReadVo selectArticleByIndex(@Param(value="index")int index); // 게시글 수정하기
    int updateArticle(ArticlesEntity article);

    int deleteArticleByIndex(@Param(value = "index") int index);


}
