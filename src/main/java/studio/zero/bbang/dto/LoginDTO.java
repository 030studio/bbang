package studio.zero.bbang.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginDTO {
    @NotBlank(message = "phoneNumber cannot be null")
    private String phone;
    @NotBlank(message = "password cannot be null")
    private String password;
}
