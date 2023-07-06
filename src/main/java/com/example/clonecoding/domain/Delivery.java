package com.example.clonecoding.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private Address address;

    @Enumerated(EnumType.STRING) // 오디널 절대 쓰면 안됨, 숫자로 저장됨
    private DeliveryStatus status; //
}
