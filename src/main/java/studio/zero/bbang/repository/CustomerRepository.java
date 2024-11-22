package studio.zero.bbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.zero.bbang.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
