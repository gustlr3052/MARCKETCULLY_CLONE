package dev.hsjung.project.bbs;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/bbs")
public class BbsController {

    // ▼ 고객센터 목록임
    @RequestMapping(value ="serviceCenter",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getBbs(){
        ModelAndView modelAndView = new ModelAndView("bbs/serviceCenter");
        return modelAndView;
    }

    @RequestMapping(value = "write",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getWrite(){
        ModelAndView modelAndView = new ModelAndView("bbs/write");
        return modelAndView;
    }



}
