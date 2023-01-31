package dev.hsjung.project.services;


import dev.hsjung.project.entities.member.EmailAuthEntity;
import dev.hsjung.project.entities.member.UserEntity;
import dev.hsjung.project.enums.CommonResult;
import dev.hsjung.project.enums.RegisterResult;
import dev.hsjung.project.enums.SendEmailAuthResult;
import dev.hsjung.project.enums.VerifyEmailAuthResult;
import dev.hsjung.project.interfaces.IResult;
import dev.hsjung.project.mappers.IMemberMapper;
import dev.hsjung.project.utils.CryptoUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


@Service(value="dev.hsjung.project.services.MemberService") // 서비스 주소 경로 해당
public class MemberService {

    private final IMemberMapper memberMapper;
    private final TemplateEngine templateEngine;

    private final JavaMailSender mailSender;

    @Autowired // 요구되는 타입 객체화 해서 전달 토록 하기 위함, Service - Mapper 간 의존성 주입을 위해 사용
    public MemberService(IMemberMapper memberMapper,TemplateEngine templateEngine,JavaMailSender mailSender){
        this.memberMapper = memberMapper;
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    @Transactional
    public Enum <? extends IResult> sendEmailAuth(UserEntity user, EmailAuthEntity emailAuth) throws NoSuchAlgorithmException, MessagingException {
        UserEntity existingUser = this.memberMapper.selectUserByEmail(user.getEmail()); //index 가져와서 existingUser 에 잡아 넣기
        if(existingUser != null) { // 유저가 중복될때
            return SendEmailAuthResult.EMAIL_DUPLICATED;
        }                           //중복 안되고 인증 절차 거칠때 인증 처리 로직
        String authCode = RandomStringUtils.randomNumeric(6); // 랜덤숫자함수 authCode 생성
        String authSalt = String.format("%s%s%f%f",                 //  랜덤함수 authSalt  생성
                user.getEmail(),
                authCode,
                Math.random(),
                Math.random());
        StringBuilder authSaltHashBuilder = new StringBuilder();    // authCode + authSalt 하기위한 해쉬값바꾸기 위한 객체 생성
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(authSalt.getBytes(StandardCharsets.UTF_8));
        for(byte hashByte: md.digest()){
            authSaltHashBuilder.append(String.format("%02x",hashByte));
        }
        authSalt = authSaltHashBuilder.toString();

        Date createdOn = new Date();    // 인증만료 시간을 위해 현재시간 생성
        Date expiresOn = DateUtils.addMinutes(createdOn,5); // 현재시간에 5분 더하는 시간생성


        emailAuth.setEmail(user.getEmail());
        emailAuth.setCode(authCode);
        emailAuth.setSalt(authSalt);
        emailAuth.setCreatedOn(createdOn);
        emailAuth.setExpiresOn(expiresOn);
        emailAuth.setExpired(false);

        if(this.memberMapper.insertEmailAuth(emailAuth) == 0) {
            return  CommonResult.FAILURE; // 이메일 인증과정을 index로 하는데 0일리가 없으니까 지워도 되는 부분 아닌가??
        }


        Context context = new Context(); // 컨텍스트 객체 생성: 서비스에서 Html 파일을 이용하기 위해서 registerEmail.html을 이용한다.
        context.setVariable("code", emailAuth.getCode());

        String text = this.templateEngine.process("member/registerEmailAuth",context);
        MimeMessage mail = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail,"UTF-8");
        helper.setFrom("gustlr3052@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("[스터디] 회원가입 인증 번호");
        helper.setText(text,true); // true 를 반드시 적어줘야함
        this.mailSender.send(mail);

        return CommonResult.SUCCESS;

    }

    // 회원가입 인증 확인
    public Enum<? extends IResult> verifyEmailAuth(EmailAuthEntity emailAuth){
        EmailAuthEntity existingEmailAuth = this.memberMapper.selectEmailAuthByEmailCodeSalt(
                emailAuth.getEmail(),
                emailAuth.getCode(),
                emailAuth.getSalt());
        if(existingEmailAuth == null)
        {
            return CommonResult.FAILURE; // 인증확인이 안되었을때
        }
        if(existingEmailAuth.getExpiresOn().compareTo(new Date()) < 0){
            return VerifyEmailAuthResult.EXPIRED;
        }
        existingEmailAuth.setExpired(true); // 인증확인됨
        if(this.memberMapper.updateEmailAuth(existingEmailAuth)==0){ // 업데이트 즉 수정된 값이 0이라면 ,없다면
            return CommonResult.FAILURE;
        }
        return  CommonResult.SUCCESS;
    }

    public Enum<? extends IResult>register(UserEntity user,EmailAuthEntity emailAuth){
        EmailAuthEntity existingEmailAuth = this.memberMapper.selectEmailAuthByEmailCodeSalt(
                emailAuth.getEmail(),
                emailAuth.getCode(),
                emailAuth.getSalt());
        if(existingEmailAuth == null || !existingEmailAuth.isExpired()){
            return RegisterResult.EMAIL_NOT_VERIFIED;
        }
        user.setPassword(CryptoUtils.hashSha512(user.getPassword()));

        if(this.memberMapper.insertUser(user)==0){
            return CommonResult.FAILURE;
        }
        return CommonResult.SUCCESS;
    }

    public Enum<? extends IResult>login(UserEntity user){
        UserEntity existingUser = this.memberMapper.selectUserByEmail(user.getEmail());
        if(existingUser == null){
            return CommonResult.FAILURE;
        }
        user.setPassword(CryptoUtils.hashSha512(user.getPassword()));
        if(!user.getPassword().equals(existingUser.getPassword())){
            return CommonResult.FAILURE;
        }
        user.setIndex(existingUser.getIndex());
        user.setName(existingUser.getName());
        user.setContact(existingUser.getContact());
        user.setAddressPrimary(existingUser.getAddressPrimary());
        user.setAddressSecondary(existingUser.getAddressSecondary());


        return CommonResult.SUCCESS;
    }

}
