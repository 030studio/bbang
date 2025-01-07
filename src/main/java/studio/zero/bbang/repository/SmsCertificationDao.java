package studio.zero.bbang.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class SmsCertificationDao {
    private final String PREFIX = "sms: ";
    private final int TIME_LIMIT = 3 * 60;

    private final StringRedisTemplate stringRedisTemplate;

    public void createSmsCertification(String phone, String certificationNumber) {
        stringRedisTemplate.opsForValue()
                .set(PREFIX + phone, certificationNumber, Duration.ofSeconds(TIME_LIMIT));
    }

    public String getSmsCertification(String phone) {
        return stringRedisTemplate.opsForValue().get(PREFIX+phone);
    }

    public void removeSmsCertification(String phone) {
        stringRedisTemplate.delete(PREFIX+phone);
    }

    public boolean hasKey(String phone) {
        return stringRedisTemplate.hasKey(PREFIX+phone);
    }
}
