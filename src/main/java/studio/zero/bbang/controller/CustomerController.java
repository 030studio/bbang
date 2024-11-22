package studio.zero.bbang.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public Customer signUpCustomer(CustomerDTO customerDTO) {
        return customerService.signUpCustomer(customerDTO);
    }
}
