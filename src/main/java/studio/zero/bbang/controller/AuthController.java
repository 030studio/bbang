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
    public ResponseEntity<Void> sendVerificationCode(@RequestBody AuthDTO authDTO) {
        authService.sendVerificationCode(authDTO.getPhone());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
