package studio.zero.bbang.service;

import lombok.RequiredArgsConstructor;
import net.datafaker.providers.base.Bool;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MessageSerivce messageSerivce;

    public void sendVerificationCode(String phoneNumber) {
        messageSerivce.verificationMessageSender(phoneNumber);
    }

    public Boolean verifyCode(String phone, String code) {
        return messageSerivce.verifyCode(phone, code);
    }
}
