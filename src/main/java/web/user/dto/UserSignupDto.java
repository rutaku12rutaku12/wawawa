package web.user.dto;

import lombok.*;

@Getter @Setter
public class UserSignupDto {
    private String username;
    private String password;
    private String email;
}