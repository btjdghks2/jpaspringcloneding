package com.example.clonecoding.repository;

import com.example.clonecoding.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);

    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);


    }

    public List<Order> findAll(OrderSearch orderSearch) {
        List<Order> resultList = em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :status" +
                        "and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMamberName())
                // .setFirstResult(100) 페이징 할때 쓰는거
                .setMaxResults(1000) // 최대 1000건
                .getResultList();
        return resultList;
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
    }
}
