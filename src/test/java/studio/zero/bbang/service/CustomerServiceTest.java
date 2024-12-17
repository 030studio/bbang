package studio.zero.bbang.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.dto.JwtDTO;
import studio.zero.bbang.dto.LoginDTO;
import studio.zero.bbang.factory.CustomerTestDataFactory;
import studio.zero.bbang.mapper.CustomerMapper;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.repository.CustomerRepository;
import studio.zero.bbang.util.JwtProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private JwtProvider jwtProvider;
    @Spy
    private CustomerMapper customerMapper;
    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void saveAndReturnUser() {
        // given
        Customer customer = CustomerTestDataFactory.createCustomer();
        CustomerDTO customerDTO = CustomerTestDataFactory.createCustomerDTO();

        when(customerMapper.dtoToCustomer(any(CustomerDTO.class))).thenReturn(customer);
        when(customerMapper.customerToDto(any(Customer.class))).thenReturn(customerDTO);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // when
        CustomerDTO savedCustomerDTO = customerService.signUpCustomer(customerDTO);

        // then
        assertNotNull(savedCustomerDTO);
        assertEquals(customer.getNickname(), savedCustomerDTO.getNickname());
        assertEquals(customer.getPhone(), savedCustomerDTO.getPhone());
    }

    @Test
    void failWhenSaveDuplicatedPhone() {
        // given
        Customer customer = CustomerTestDataFactory.createCustomer();
        CustomerDTO customerDTO = CustomerTestDataFactory.createCustomerDTO();

        // when
        when(customerRepository.existsByPhone(customer.getPhone())).thenReturn(true);

        // then
        assertThrows(IllegalArgumentException.class,
                () -> customerService.signUpCustomer(customerDTO),
                "phone number already exists");
    }

    @Test
    void failWhenSaveWithoutPhoneAuthenticated() {
        // given
        CustomerDTO customerDTO = CustomerTestDataFactory.customerDTOAboutAuthenticated(null);

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> customerService.signUpCustomer(customerDTO),
                "phone number must be authenticated");
    }

    @Test
    void failWhenSaveWithFalsePhoneAuthenticated() {
        // given
        CustomerDTO customerDTO = CustomerTestDataFactory.customerDTOAboutAuthenticated(false);

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> customerService.signUpCustomer(customerDTO),
                "phone number must be authenticated");
    }

    @Test
    void loginSuccess() {
        // given
        ReflectionTestUtils.setField(jwtProvider, "jwtKey", "testKey");

        String phoneNumber = "01012345678";
        String password = "encryptedPassword";
        String expectedAccessToken = "testAccessToken";
        String expectedRefreshToken = "testRefreshToken";
        Customer customer = CustomerTestDataFactory.createCustomerWithPhone(phoneNumber);

        LoginDTO customerLoginDTO = new LoginDTO(phoneNumber, password);
        JwtDTO expectedJwtToken = new JwtDTO(expectedAccessToken, expectedRefreshToken);

        when(customerRepository.getCustomerByPhone(customerLoginDTO.getPhoneNumber())).thenReturn(Optional.of(customer));
        when(passwordEncoder.matches(customerLoginDTO.getPassword(), customer.getPassword())).thenReturn(true);
        when(jwtProvider.generateToken(customerLoginDTO.getPhoneNumber())).thenReturn(expectedJwtToken);

        // when
        JwtDTO actualJwtToken = customerService.loginCustomer(customerLoginDTO);

        // then
        assertEquals(expectedJwtToken, actualJwtToken);
    }

//    @Test
//    void testLoginCustomerWithClaims() {
//        // given
//        Customer customer = CustomerTestDataFactory.createCustomer();
//        customer.setPassword("encryptedPassword123");
//        CustomerLoginDTO customerLoginDTO = new CustomerLoginDTO("01012345678", "rawPassword");
//
//        when(customerRepository.getCustomerByPhone(customerLoginDTO.getCustomerPhone())).thenReturn(customer);
//        when(passwordEncoder.matches(customerLoginDTO.getPassword(), customer.getPassword())).thenReturn(true);
//
//        // when
//        String jwtToken = customerService.loginCustomer(customerLoginDTO);
//
//        // then
//        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("testKey"))
//                .build()
//                .verify(jwtToken);
//
//        assertEquals("test jwt", decodedJWT.getSubject());
//        assertEquals("01012345678", decodedJWT.getClaim("phoneNumber").asString());
//    }
}