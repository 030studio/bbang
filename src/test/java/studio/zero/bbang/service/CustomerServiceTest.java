package studio.zero.bbang.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.dto.CustomerMapper;
import studio.zero.bbang.factory.CustomerTestDataFactory;
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

    @Test
    void saveAndReturnUser() {
        // given
        Customer customer = CustomerTestDataFactory.createCustomer();
        CustomerDTO customerDTO = CustomerTestDataFactory.createCustomerDTO();

        when(customerMapper.dtoToCustomer(any(CustomerDTO.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // when
        Customer savedCustomer = customerService.signUpCustomer(customerDTO);

        // then
        assertNotNull(savedCustomer);
        assertEquals(customer.getNickname(), savedCustomer.getNickname());
        assertEquals(customer.getPhone(), savedCustomer.getPhone());
    }

    @Test
    void saveDuplicatedPhone() {
        // given
        Customer customer = CustomerTestDataFactory.createCustomer();
        CustomerDTO customerDTO = CustomerTestDataFactory.createCustomerDTO();

        when(customerRepository.existsByPhone(customer.getPhone())).thenReturn(true);

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> customerService.signUpCustomer(customerDTO),
                        "phone number already exists");
    }

}