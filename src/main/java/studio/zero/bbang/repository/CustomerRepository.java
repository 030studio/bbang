package studio.zero.bbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.zero.bbang.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByPhone(String phone);
}
