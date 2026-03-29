package web.user.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter @Setter
public class UserLoginDto {
    private String username;
    private String password;
}