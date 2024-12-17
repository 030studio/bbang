package studio.zero.bbang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.dto.JwtDTO;
import studio.zero.bbang.dto.LoginDTO;
import studio.zero.bbang.mapper.CustomerMapper;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.repository.CustomerRepository;
import studio.zero.bbang.util.JwtProvider;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;
    private final JwtProvider jwtProvider;

    public CustomerDTO signUpCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByPhone(customerDTO.getPhone()))
            throw new IllegalArgumentException("phone number already exists");
        if (customerDTO.getIsAuthenticated() == null || !customerDTO.getIsAuthenticated())
            throw new IllegalArgumentException("phone number must be authenticated");

        customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

        Customer customer = customerMapper.dtoToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToDto(savedCustomer);
    }

    public JwtDTO loginCustomer(LoginDTO loginDTO) {
        Customer customer = customerRepository.getCustomerByPhone(loginDTO.getPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("customer doesn't exist"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), customer.getPassword())) {
            throw new IllegalArgumentException("password not matches");
        }

        return jwtProvider.generateToken(customer.getPhone());
    }
}
