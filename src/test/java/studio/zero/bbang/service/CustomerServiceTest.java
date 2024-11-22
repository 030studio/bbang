package studio.zero.bbang.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    private final String nickname = "test nickname";
    private final String phone = "010-1111-1111";

//    @Test
//    void saveAndReturnUser() {
//        // given
//        when(customerRepository.save(any(Customer.class))).thenReturn(makeCustomer());
//
//        // when
//        Customer savedCustomer = customerService.signUpCustomer(makeCustomerDTO());
//
//        // then
//        assertNotNull(savedCustomer);
//        assertEquals(nickname, savedCustomer.getNickname());
//        assertEquals(phone, savedCustomer.getPhone());
//    }

    CustomerDTO makeCustomerDTO() {
        return CustomerDTO.builder()
                .nickname(nickname)
                .phone(phone)
                .build();
    }

    Customer makeCustomer() {
        return Customer.builder()
                .nickname(nickname)
                .phone(phone)
                .build();
    }

}