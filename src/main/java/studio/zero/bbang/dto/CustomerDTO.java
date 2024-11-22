package studio.zero.bbang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
@AllArgsConstructor
public class CustomerDTO {
    private String nickname;
    private String password;
    private String phone;
    private Point location;
}

