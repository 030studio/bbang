package studio.zero.bbang.dto;

import org.mapstruct.Mapper;
import studio.zero.bbang.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    public CustomerDTO customerToDto(Customer customer);
    public Customer dtoToCustomer(CustomerDTO customerDTO);
}
