package studio.zero.bbang.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.repository.CustomerRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
    private final CustomerRepository customerRepository;

    public Customer findUserById(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("can not find customer by id: " + id));
    }
    public List<Double> findLocationByCustomer(Customer customer) {
        List<Double> locations = new ArrayList<>();

        Point location = customer.getLocation();
        double latitude = location.getY();
        double longitude = location.getX();

        locations.add(latitude);
        locations.add(longitude);

        return locations;
    }
    public void getStoreList() {

    }
}
