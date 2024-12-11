package studio.zero.bbang.mapper;

import org.mapstruct.Mapper;
import studio.zero.bbang.dto.CustomerDTO;
import studio.zero.bbang.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToDto(Customer customer);
    Customer dtoToCustomer(CustomerDTO customerDTO);
}
