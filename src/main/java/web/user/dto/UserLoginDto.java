package web.user.dto;

import lombok.*;

@Getter @Setter
public class UserLoginDto {
    private String username;
    private String password;
}