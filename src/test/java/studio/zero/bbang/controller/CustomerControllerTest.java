package studio.zero.bbang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.factory.CustomerTestDataFactory;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.service.CustomerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomer() throws Exception {
        // given
        CustomerDTO customerDTO = CustomerTestDataFactory.createCustomerDTO();
        Customer customer = CustomerTestDataFactory.createCustomer();

        when(customerService.signUpCustomer(any(CustomerDTO.class))).thenReturn(customerDTO);

        // when & then
        mockMvc.perform(post("/customer/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nickname").value(customer.getNickname()))
                .andExpect(jsonPath("$.phone").value(customer.getPhone()));
    }

    @Test
    void createCustomerWithoutNickname() throws Exception {
        // given
        CustomerDTO invalidCustomerDTO = CustomerTestDataFactory.customerDTOWithoutNickname();

        // when & then
        mockMvc.perform(post("/customer/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCustomerDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
                    assertTrue(result.getResolvedException().getMessage().contains("Nickname cannot be null"));
                });
    }
}