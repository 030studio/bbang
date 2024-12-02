package studio.zero.bbang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MessageSerivce messageSerivce;

    public void sendVerificationCode(String phoneNumber) {
        messageSerivce.verificationMessageSender(phoneNumber);
    }
}
