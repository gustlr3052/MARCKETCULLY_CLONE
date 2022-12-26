package dev.hsjung.project.member;


import dev.hsjung.project.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/main")
public class HomeController {

    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }

    @RequestMapping(value = "main",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getMain(){
        ModelAndView modelAndView = new ModelAndView("main/main");
        return modelAndView;
    }


}
