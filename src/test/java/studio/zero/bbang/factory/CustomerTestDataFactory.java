package studio.zero.bbang.factory;

import net.datafaker.Faker;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.model.Customer;

import java.util.Locale;

public class CustomerTestDataFactory {
    private static final Faker faker = new Faker(new Locale("kr"));
    private static final String nickname = "nickname";
    private static final String phone = faker.phoneNumber().cellPhone();
    private static final String password = "aA123456";
    private static final Boolean isAuthenticated = true;

    public static CustomerDTO createCustomerDTO() {
        return CustomerDTO.builder()
                .nickname(nickname)
                .phone(phone)
                .password(password)
                .isAuthenticated(isAuthenticated)
                .build();
    }

    public static CustomerDTO customerDTOWithoutNickname() {
        return CustomerDTO.builder()
                .phone(phone)
                .password(password)
                .isAuthenticated(isAuthenticated)
                .build();
    }

    public static CustomerDTO customerDTOAboutAuthenticated(Boolean value) {
        return CustomerDTO.builder()
                .phone(phone)
                .password(password)
                .isAuthenticated(value)
                .build();
    }

    public static CustomerDTO customerDTOAboutPassword(String password) {
        return CustomerDTO.builder()
                .phone(phone)
                .password(password)
                .isAuthenticated(true)
                .build();
    }

    public static CustomerDTO customerDTOWithInvlidPhoneNumber() {
        return CustomerDTO.builder()
                .phone("123456")
                .nickname(nickname)
                .password(password)
                .build();
    }

    public static Customer createCustomer() {
        return Customer.builder()
                .nickname(nickname)
                .phone(phone)
                .password(password)
                .build();
    }
}
