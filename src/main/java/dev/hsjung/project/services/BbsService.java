package dev.hsjung.project.services;


import dev.hsjung.project.entities.bbs.ArticlesEntity;
import dev.hsjung.project.entities.bbs.BoardsEntity;
import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.enums.ArticleModifyResult;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.enums.WriteResult;
import dev.hsjung.project.interfaces.IResult;
import dev.hsjung.project.mappers.IBbsMapper;
import dev.hsjung.project.vos.ArticleReadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.Date;

@Service(value="dev.hsjung.project.services.BbsService")
public class BbsService {
    private final TemplateEngine templateEngine;

    private final IBbsMapper bbsMapper;

    @Autowired
    public BbsService(TemplateEngine templateEngine, IBbsMapper bbsMapper){
        this.templateEngine = templateEngine;
        this.bbsMapper = bbsMapper;
    }

    public BoardsEntity getBoard(String id){
        return this.bbsMapper.selectBoardById(id);
    }

    public  Enum<? extends IResult> write(ArticlesEntity article){
        BoardsEntity board = this.bbsMapper.selectBoardById(article.getBoardId());
        if(board == null){
            return WriteResult.NO_SUCH_BOARD;
        }
        return this.bbsMapper.insertArticles(article) > 0 ? CommonResult.SUCCESS : CommonResult.FAILURE;
    }

    public ArticleReadVo[] getArticles(BoardsEntity board){
        return this.bbsMapper.selectArticlesByBoardId(board.getId());
    }

    public Enum<? extends IResult> prepareModifyArticle(ArticlesEntity article,
                                                 UserEntity user){
        if(user == null){
            return ArticleModifyResult.NOT_SIGNED;
        }
        ArticlesEntity existingArticle = this.bbsMapper.selectArticleByIndex(article.getIndex());
        if(existingArticle == null){
            return ArticleModifyResult.NO_SUCH_ARTICLE;
        }
        if (!existingArticle.getUserEmail().equals(user.getEmail())){
            return ArticleModifyResult.NOT_ALLOWED;
        }
        article.setIndex(existingArticle.getIndex());
        article.setUserEmail(existingArticle.getUserEmail());
        article.setBoardId(existingArticle.getBoardId());
        article.setTitle(existingArticle.getTitle());
        article.setContent(existingArticle.getContent());
        article.setView(existingArticle.getView());
        article.setModifiedOn(existingArticle.getModifiedOn());
        return CommonResult.SUCCESS;
    }

    public Enum<? extends  IResult> modifyArticle(ArticlesEntity article,UserEntity user){
        if(user == null){
            return ArticleModifyResult.NOT_SIGNED;
        }
        ArticlesEntity existingArticle = this.bbsMapper.selectArticleByIndex(article.getIndex());
        System.out.println(article.getIndex());
        if(existingArticle == null){
            return ArticleModifyResult.NO_SUCH_ARTICLE;
        }
        existingArticle.setTitle(article.getTitle());
        existingArticle.setContent(article.getContent());
        existingArticle.setModifiedOn(new Date());
        return this.bbsMapper.updateArticle(existingArticle) > 0 ? CommonResult.SUCCESS : CommonResult.FAILURE;
    }

    public Enum<? extends IResult> deleteArticle(ArticlesEntity article,UserEntity user){
       ArticlesEntity existingArticle = this.bbsMapper.selectArticleByIndex(article.getIndex()); // 게시글 인덱스 받아옴
        System.out.println(article.getIndex());
       if(existingArticle == null){                                               // 없을
           System.out.println("어디서 걸리는거야1");
           return CommonResult.FAILURE;
       }
       if(user == null || !user.getEmail().equals(existingArticle.getUserEmail())){ //현재 사용자와 작성자가 일치하는 사람인지 확인
           System.out.println("어디서 걸리는거야2");
           return CommonResult.FAILURE;
       }
       article.setBoardId(existingArticle.getBoardId()); //
        System.out.println("제어문 통과 : 성공");
       return this.bbsMapper.deleteArticleByIndex(article.getIndex()) > 0 ? CommonResult.SUCCESS : CommonResult.FAILURE;
    }






}
