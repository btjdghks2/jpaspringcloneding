package com.example.clonecoding.api;

import com.example.clonecoding.domain.Address;
import com.example.clonecoding.domain.Order;
import com.example.clonecoding.domain.OrderItem;
import com.example.clonecoding.domain.OrderStatus;
import com.example.clonecoding.repository.OrderRepository;
import com.example.clonecoding.repository.OrderSearch;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }

    @Data
    static class OrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItem> orderItems;


        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());

                }


            }

    @Getter
    static class OrderItemDto {

        private String itemName; // 삼품명
        private int orderPrice; //주문 가격
        private int count; // 주문 수량

        public OrderItemDto(OrderItem orderItem) {

            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();

        }
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {

        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;

    }

}


