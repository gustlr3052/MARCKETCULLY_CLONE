package dev.hsjung.project.services;


import dev.hsjung.project.mapper.IMemberMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;


@Service(value="dev.hsjung.project.services.MemberService") // 서비스 주소 경로
public class MemberService {

    private final IMemberMapper memberMapper;

    public MemberService(IMemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

}
