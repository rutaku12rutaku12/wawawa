package web.user.mapper;

import web.user.dto.UserSignupDto;
import web.user.entity.User;

public class UserMapper {

    public static User toEntity(UserSignupDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword()) // 여기서 암호화는 Service에서
                .email(dto.getEmail())
                .build();
    }
}