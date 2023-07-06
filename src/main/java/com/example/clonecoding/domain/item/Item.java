package com.example.clonecoding.domain.item;

import com.example.clonecoding.domain.Category;
import com.example.clonecoding.exception.NotEnoughStockException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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

    @ManyToMany(mappedBy = "items")
    private List<Category> categoryies = new ArrayList<>();


    //재고 증가 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //재고 감소 로직
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock <0) {
            throw new NotEnoughStockException("need more stock");

        }
        this.stockQuantity = restStock;
    }


}
