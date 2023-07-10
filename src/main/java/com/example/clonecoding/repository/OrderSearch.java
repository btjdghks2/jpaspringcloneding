package com.example.clonecoding.repository;

import com.example.clonecoding.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String mamberName; //회원 이름
    private OrderStatus orderStatus; //주문 상태(order, cancel)

}
