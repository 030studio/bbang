package studio.zero.bbang.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.dto.CustomerMapper;
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
    @Spy
    private CustomerMapper customerMapper;

    private final String nickname = "test nickname";
    private final String phone = "010-1111-1111";

    @Test
    void saveAndReturnUser() {
        // given
        when(customerMapper.dtoToCustomer(any(CustomerDTO.class))).thenReturn(makeCustomer(nickname, phone));
        when(customerRepository.save(any(Customer.class))).thenReturn(makeCustomer(nickname, phone));

        // when
        Customer savedCustomer = customerService.signUpCustomer(makeCustomerDTO(nickname, phone));

        // then
        assertNotNull(savedCustomer);
        assertEquals(nickname, savedCustomer.getNickname());
        assertEquals(phone, savedCustomer.getPhone());
    }

    @Test
    void saveDuplicatedPhone() {
        // given
        when(customerRepository.existsByPhone(phone)).thenReturn(true);
        CustomerDTO customerDTO = makeCustomerDTO(nickname, phone);

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> customerService.signUpCustomer(customerDTO),
                        "phone number already exists");
    }


    CustomerDTO makeCustomerDTO(String nickname, String phone) {
        return CustomerDTO.builder()
                .nickname(nickname)
                .phone(phone)
                .build();
    }

    Customer makeCustomer(String nickname, String phone) {
        return Customer.builder()
                .nickname(nickname)
                .phone(phone)
                .build();
    }

}