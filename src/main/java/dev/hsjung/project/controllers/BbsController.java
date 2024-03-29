package dev.hsjung.project.controllers;

import dev.hsjung.project.entities.bbs.ArticlesEntity;
import dev.hsjung.project.entities.bbs.BoardsEntity;
import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.enums.WriteResult;
import dev.hsjung.project.services.BbsService;
import dev.hsjung.project.vos.ArticleReadVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/bbs")
public class BbsController {


    private final BbsService bbsService;

    @Autowired public BbsController(BbsService bbsService){
        this.bbsService = bbsService;
    }
    // ▼ 고객센터 목록임
    @RequestMapping(value ="serviceCenter",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getBbs(){
        ModelAndView modelAndView = new ModelAndView("bbs/serviceCenter");
        return modelAndView;
    }

    @RequestMapping(value="otoWriteList",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getOtoWriteList(@RequestParam(value = "bid")String bid){

        ModelAndView modelAndView = new ModelAndView("bbs/otoWriteList");
        BoardsEntity board = this.bbsService.getBoard(bid);
        modelAndView.addObject("board",board);
        if(board != null){
            ArticleReadVo[] articles = this.bbsService.getArticles(board);
            modelAndView.addObject("articles",articles);
        }
        return  modelAndView;
    }





    @RequestMapping(value = "write", // 로그인시 글 쓸 수 있도록 하기
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getWrite(@SessionAttribute(value = "user",required = false)UserEntity user,
                                 @RequestParam(value = "bid",required = false)String bid) {
        ModelAndView modelAndView;

        if (user == null) {
            modelAndView = new ModelAndView("redirect:/member/login");
        } else {
            BoardsEntity board = this.bbsService.getBoard(bid);
            modelAndView = new ModelAndView("bbs/write");
            modelAndView.addObject("board", board);
        }
        return modelAndView;
    }



    @RequestMapping(value="write",  // 글쓰기
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postWrite(@SessionAttribute(value = "user",required = false)UserEntity user,
                            @RequestParam(value = "bid",required = false)String bid,
                            ArticlesEntity article){
        Enum<?> result;
        JSONObject responseObject = new JSONObject();
        if(user == null){
            result = WriteResult.NOT_ALLOWED; // 유저가 없으면 허락 x
        }
        else if(bid == null){
            result = WriteResult.NO_SUCH_BOARD;
        }
        else {
            article.setUserEmail(user.getEmail());
            article.setBoardId(bid);
            result = this.bbsService.write(article);
        }
        responseObject.put("result",result.name().toLowerCase());
        if(result == CommonResult.SUCCESS){
            responseObject.put("index",article.getIndex());
            System.out.println(article.getIndex());
        }
        return responseObject.toString();
    }

    @RequestMapping(value = "modify", // 수정주소 보이기 : aid값에 index 연동시켜서 불러오기
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getModify(@SessionAttribute(value = "user",required = false)UserEntity user,
                                  @RequestParam(value = "aid",required = false)int aid) {

        ArticlesEntity article = new ArticlesEntity();
        article.setIndex(aid);
        Enum<?> result = this.bbsService.prepareModifyArticle(article,user);
        ModelAndView modelAndView = new ModelAndView("bbs/modify");
        modelAndView.addObject("article",article);
        modelAndView.addObject("result",result.name());
        if(result == CommonResult.SUCCESS){
            modelAndView.addObject("board",this.bbsService.getBoard(article.getBoardId()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "modify", // 실제 수정 값 넣기
    method = RequestMethod.PATCH,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String patchModify(@SessionAttribute(value = "user",required = false)UserEntity user,
                              @RequestParam(value="aid",required = false)int aid,
                              ArticlesEntity article)
    {
        article.setIndex(aid);
        Enum<?>result = bbsService.modifyArticle(article,user);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result",result.name().toLowerCase());
        if(result == CommonResult.SUCCESS){
            responseObject.put("aid",aid);
        }
        return responseObject.toString();
    }
    @RequestMapping(value = "otoWriteList",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteOtoWriteList(@SessionAttribute(value = "user",required = false)UserEntity user, ArticlesEntity article){
        Enum<?> result = this.bbsService.deleteArticle(article,user);
        JSONObject responseObject= new JSONObject();
        responseObject.put("result",result.name().toLowerCase());
        if(result == CommonResult.SUCCESS){
//            responseObject.put("bid",article.getBoardId());
            System.out.println("삭제 성공했습니다.");
        }

        return responseObject.toString();

    }
}
