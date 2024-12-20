package studio.zero.bbang.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.dto.JwtDTO;
import studio.zero.bbang.dto.LoginDTO;
import studio.zero.bbang.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> signUpCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.signUpCustomer(customerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> loginCustomer(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.loginCustomer(loginDTO));
    }
}
