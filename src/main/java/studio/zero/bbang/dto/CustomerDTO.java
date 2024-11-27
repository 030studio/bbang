package studio.zero.bbang.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "Nickname cannot be null")
    @Size(min = 2, max = 10, message = "Nickname must be between 2 and 10 characters")
    private String nickname;

    private String password;

    @NotBlank(message = "phone number cannot be null")
    private String phone;
    private Point location;
}

