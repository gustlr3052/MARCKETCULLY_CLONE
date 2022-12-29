package dev.hsjung.project.services;


import dev.hsjung.project.entities.bbs.ArticlesEntity;
import dev.hsjung.project.entities.bbs.BoardsEntity;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.enums.WriteResult;
import dev.hsjung.project.interfaces.IResult;
import dev.hsjung.project.mappers.IBbsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

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


}
