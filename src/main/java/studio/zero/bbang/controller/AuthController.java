package studio.zero.bbang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.zero.bbang.dto.AuthDTO;
import studio.zero.bbang.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/code")
    public ResponseEntity<Void> sendVerificationCode(@RequestBody AuthDTO.ReqVerification authDTO) {
        authService.sendVerificationCode(authDTO.phone());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/verification")
    public ResponseEntity<Boolean> verifyCode(@RequestBody AuthDTO.ReqConfirm authDTO) {
        authService.verifyCode(authDTO.phone(), authDTO.code());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
