package dev.hsjung.project.controllers;

import dev.hsjung.project.entities.bbs.BoardsEntity;
import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.services.BbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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

    @RequestMapping(value = "write", // 로그인시 글 쓸 수 있도록 하기
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getWrite(@SessionAttribute(value = "user",required = false)UserEntity user,
                                 @RequestParam(value = "bid",required = false)String bid){
        ModelAndView modelAndView;

        if(user == null){
            modelAndView = new ModelAndView("redirect:/member/login");
        }else{
            BoardsEntity board = bid == null ? null : this.bbsService.getBoard(bid);
             modelAndView = new ModelAndView("bbs/write");
            modelAndView.addObject("board",board);
        }
        return modelAndView;





    }



}
