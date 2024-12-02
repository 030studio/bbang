package studio.zero.bbang.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageSerivce {
    @Value("${twilio.sid}")
    private String accountSid;
    @Value("${twilio.auth-token}")
    private String authToken;
    @Value("${twilio.phone}")
    private String twilioPhone;

    public void twilioMessageSender(String userPhoneNumber, String content) {
        Twilio.init(accountSid, authToken);

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

        String verificationMessage = String.format("인증번호는 [%s]입니다.", randomNum);

        twilioMessageSender(userPhoneNumber, verificationMessage);
    }
}
