package com.example.clonecoding.api;

import com.example.clonecoding.domain.Address;
import com.example.clonecoding.domain.Order;
import com.example.clonecoding.domain.OrderStatus;
import com.example.clonecoding.repository.OrderRepository;
import com.example.clonecoding.repository.OrderSearch;
import com.example.clonecoding.repository.SimpleOrderQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        return all;
    }

//    @GetMapping("api/v3/simple-orders")
//    public List<SimpleOrderDto> ordersV3() {
//        List<Order> order = orderRepository.findAllWithMemberDelivery();
//        List<SimpleOrderDto> result = order.stream()
//                .map(o -> new SimpleOrderDto(o))
//                .collect(Collectors.toList());
//
//        return result;
//    }

    @Data
    static class SimpleOrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }


    }

    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderQueryDto> orderV4() {
        return orderRepository.findOrderDtos();

    }




}
