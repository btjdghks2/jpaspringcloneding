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
        return null; //쓰다만거
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        "join fetch o.member m " +
                        "join fetch o.delivery d" , Order.class)
                .getResultList();
    }

    public List<SimpleOrderQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o" +
                        "join o.member m" +
                        "join o.delivery d", SimpleOrderQueryDto.class)  //dto를 쿼리에 직접 적으르려면 select 옆에 new 를 선언해주고 dto의 주소를 적어줘야 한다
                .getResultList();

        // 여기 로직은 jpql로 일일히 설정해 쿼리를 최적화 한것, v3의 방법보다 최적화가 잘 되지만 직접 적었기 때문에 유연성이 떨어진다

    }
}
