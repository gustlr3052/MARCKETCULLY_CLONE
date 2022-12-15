package dev.hsjung.project.services;

import dev.hsjung.project.mapper.IMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service(value="dev.hsjung.project.services.MemberService") // 서비스 주소 경로
public class MemberService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    private final IMemberMapper memberMapper;

    @Autowired
    public MemberService(JavaMailSender mailSender, TemplateEngine templateEngine, IMemberMapper memberMapper) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.memberMapper = memberMapper;
    }




}
