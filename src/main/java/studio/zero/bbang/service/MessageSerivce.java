package studio.zero.bbang.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import studio.zero.bbang.repository.SmsCertificationDao;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MessageSerivce {
    @Value("${twilio.sid}")
    private String accountSid;
    @Value("${twilio.auth-token}")
    private String authToken;
    @Value("${twilio.phone}")
    private String twilioPhone;
    private final SmsCertificationDao smsCertificationDao;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }
    public void twilioMessageSender(String userPhoneNumber, String content) {

        String countryCode = "+82";
        String phoneWithCountryCode = String.format("%s%s", countryCode, userPhoneNumber.substring(1));

		Message message = Message
				.creator(
						new PhoneNumber(phoneWithCountryCode),
						new PhoneNumber(twilioPhone),
                        content)
				.create();
    }

    public void verificationMessageSender(String userPhoneNumber) {
        String randomNum = makeRandomNumber().toString();
        String verificationMessage = String.format("인증번호는 [%s]입니다.", randomNum);
//        twilioMessageSender(userPhoneNumber, verificationMessage);
        System.out.println("randomNum = " + randomNum);
        smsCertificationDao.createSmsCertification(userPhoneNumber, randomNum);
    }

    public StringBuilder makeRandomNumber() {
        Random r = new Random();
        StringBuilder randomNum = new StringBuilder();
        int digits = 6;

        for (int i = 0; i < digits; i++) {
            int number = r.nextInt(9);
            while (i == 0 && number == 0) {
                number = r.nextInt(9);
            }
            randomNum.append(number);
        }
        return randomNum;
    }

    public Boolean verifyCode(String phone, String code) {
        return !(smsCertificationDao.hasKey(phone)) && smsCertificationDao.getSmsCertification(phone).equals(code);
    }
}
