package dev.hsjung.project.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CryptoUtils {
    public static String hashSha512(String input){
       try{
           StringBuilder passwordHashBuilder = new StringBuilder();
           MessageDigest md = MessageDigest.getInstance("SHA-512");
           md.update(input.getBytes(StandardCharsets.UTF_8));
           for(byte hashByte : md.digest()){
               passwordHashBuilder.append(String.format("%02x",hashByte));
           }
           return passwordHashBuilder.toString();
       }catch (NoSuchAlgorithmException ignored){
           return null;
       }
    }
    private CryptoUtils(){

    }
}
/*
* 1.hashSha512 메서드 로직 완성하기
*  -input으로 "test1234"들어오면
* "..."반환되게
*  - 이 메서드는 throws 시그니처를 사용하지 않음. 고로 NoSuchAlgorithmException 발생 시 null 반환 하도록 조치.
* 2.CryptoUtils 클래스 객체화 못하게 막기
* */