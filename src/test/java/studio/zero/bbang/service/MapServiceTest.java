package studio.zero.bbang.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import studio.zero.bbang.factory.CustomerTestDataFactory;
import studio.zero.bbang.model.Customer;
import studio.zero.bbang.repository.CustomerRepository;

import java.awt.*;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MapServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private MapService mapService;

    @Test
    void getCurrentPoint() {
        // given - 현재 위치 받아서 전달
        // 고객 정보 반환 - 11은 db데이터 중 mk의 id임
        Customer mockCustomer = CustomerTestDataFactory.createCustomer();
        Long userId = mockCustomer.getId();
        given(customerRepository.findById(userId)).willReturn(Optional.of(mockCustomer));

        Customer customer = mapService.findUserById(userId);
        List<Double> locations = mapService.findLocationByCustomer(customer);

        assertEquals(33.450701, locations.get(0));
        assertEquals(126.570667, locations.get(1));
    }

    @Test
    void getStoreList() {
        // 모든 가게 위치 다 한번에 불러옴....
        mapService.getStoreList();

        // then
        // 리스트 잘 불러왔는지 확인
    }
}