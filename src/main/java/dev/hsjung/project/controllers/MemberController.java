package dev.hsjung.project.controllers;

import dev.hsjung.project.entities.member.EmailAuthEntity;
import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.services.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(value="/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    // member/register 보이기
    @RequestMapping(value="register",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getRegister(){
        ModelAndView modelAndView = new ModelAndView("member/register");

        return modelAndView;
    }

    @RequestMapping(value = "email",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postEmail(UserEntity user, EmailAuthEntity emailAuth) throws NoSuchAlgorithmException,MessagingException {
        Enum<?> result = this.memberService.sendEmailAuth(user, emailAuth);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result",result.toString().toLowerCase());
        if(result == CommonResult.SUCCESS){
            responseObject.put("salt",emailAuth.getSalt());
            System.out.println(emailAuth.getSalt());
        }
        return responseObject.toString();

    }

    @RequestMapping(value = "email",
    method = RequestMethod.PATCH,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String patchEmail(EmailAuthEntity emailAuth){
        Enum<?> result = this.memberService.verifyEmailAuth(emailAuth);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result",result.name().toLowerCase());
        return responseObject.toString();
    }

    @RequestMapping(value = "register",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postRegister(UserEntity user, EmailAuthEntity emailAuth){
        Enum<?> result = this.memberService.register(user,emailAuth);
        JSONObject responseObject = new JSONObject();
        responseObject.put("result",result.name().toLowerCase());

        return responseObject.toString();

    }

    @RequestMapping(value = "login",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getLogin(){
        ModelAndView modelAndView = new ModelAndView("member/login");
        return modelAndView;
    }

    @RequestMapping(value = "login",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postLogin(HttpSession session,UserEntity user){
        Enum<?> result = this.memberService.login(user);
        if(result == CommonResult.SUCCESS){
            session.setAttribute("user",user);
            System.out.println("이메일/비밀번호 맞음");
        }else{
            System.out.println("이메일/비밀번호 틀림");
        }
        JSONObject responseObject = new JSONObject();
        responseObject.put("result",result.name().toLowerCase());
        return responseObject.toString();
    }

    @RequestMapping(value = "logout",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public  ModelAndView getLogout(HttpSession session){
        session.setAttribute("user",null);
        ModelAndView modelAndView = new ModelAndView("redirect:login");
        return modelAndView;
    }


}
