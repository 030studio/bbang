package studio.zero.bbang.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < 8 || password.length() > 16) {
            return false;
        }

        int count = 0;
        if (password.matches(".*[a-z].*")) count++; // 영소문자
        if (password.matches(".*[A-Z].*")) count++; // 영대문자
        if (password.matches(".*\\d.*")) count++;  // 숫자
        if (password.matches(".*[!@#$%^&*()\\-_=+{};:,<.>].*")) count++; // 특수문자

        return count >= 2;
    }
}
