package com.example.clonecoding.domain.item;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 매핑의 경우 부모 클래스에게 이걸 걸어야 한다
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "Item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
