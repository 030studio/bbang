package studio.zero.bbang.service;

import org.junit.jupiter.api.Test;
import studio.zero.bbang.factory.CustomerTestDataFactory;
import studio.zero.bbang.model.Customer;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MapServiceTest {

    private MapService mapService;

    void getCurrentPoint() {
        // given - 현재 위치 받아서 전달
        // 고객 생성
        Customer customer = CustomerTestDataFactory.createCustomer();
        Point location = customer.getLocation();

        // when
        // 현재 위치정보 지정 실패
        mapService.getCurrentPoint();

        //then
        // 현재 위치 정보로 맵에 현 위치 설정
    }
    @Test
    void getStoreList() {
        // 모든 가게 위치 다 한번에 불러옴....
        mapService.getStoreList();

        // then
        // 리스트 잘 불러왔는지 확인
    }
}