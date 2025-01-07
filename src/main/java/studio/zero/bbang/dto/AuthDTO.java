package studio.zero.bbang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthDTO {
    public record ReqVerification(String phone) { }
    public record ReqConfirm(String phone, String code) { }
}
