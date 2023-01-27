package dev.hsjung.project.mappers;

import dev.hsjung.project.entities.member.EmailAuthEntity;
import dev.hsjung.project.entities.member.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMemberMapper {
    //Param이란 object요소에 의해 불러와지는 플러그인을 위한 매개변수를 정의
    // name : 매개변수의 이름을 나타냄 , value : 매개변수의 값을 나타냄
    UserEntity selectUserByEmail(@Param(value="email")String email);   //

    EmailAuthEntity selectEmailAuthByEmailCodeSalt(@Param(value = "email")String email,
                                                   @Param(value = "code")String code,
                                                   @Param(value = "salt")String salt); //email,code,salt 값 가져오기

    UserEntity selectUsers(@Param(value = "email")String email,
                           @Param(value = "index")int index);

    int insertEmailAuth(EmailAuthEntity emailAuth);

    int updateEmailAuth(EmailAuthEntity emailAuth);

    int insertUser(UserEntity user);


}
