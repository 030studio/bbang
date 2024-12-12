package studio.zero.bbang.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studio.zero.bbang.annotation.ValidPassword;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "nickname cannot be null")
    @Size(min = 2, max = 10, message = "Nickname must be between 2 and 10 characters")
    private String nickname;

    @ValidPassword
    @NotBlank(message = "password cannot be null")
    private String password;

    @NotBlank(message = "phone number cannot be null")
    private String phone;

    @NotNull(message = "phone number must be authenticated")
    private Boolean isAuthenticated;
}

