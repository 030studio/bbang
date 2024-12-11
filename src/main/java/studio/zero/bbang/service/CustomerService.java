package studio.zero.bbang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.mapper.CustomerMapper;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    public CustomerDTO signUpCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByPhone(customerDTO.getPhone())) throw new IllegalArgumentException("phone number already exists");

        customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

        Customer customer = customerMapper.dtoToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToDto(savedCustomer);
    }
}
