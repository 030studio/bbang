package studio.zero.bbang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class JwtDTO {
    private String accessToken;
    private String refreshToken;
}
