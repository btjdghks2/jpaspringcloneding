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

    public List<Order> findAllWithItem() {

        return em.createQuery("" +
                "select distinct o from Order o" +
                "jin fetch o.member m " +
                "join fetch o.orderItems oi" +
                "join fetch oi.item i ", Order.class)
                //.setFirstResult(1) - 페이징 기능
                //.setMaxResults(100) - 페이징 기능
                .getResultList(); // distinct 키워드는 2가지 기능이 있는데 sql에 그대로 distinct 를 날려주는 기능과 결과값에 중복을 날려주는 기능이 있다
        //여기서 distinct는 sql 에 날려도 아무 효과가 없지만 중복을 제거해주는 효과가 있다
        //fetch 조인을 하면 페이징 불가능 - 어플리케이션에서 메모리로 옮겨서 처리하는데 그렇게 되면 아웃오브메모리가 됨(용량 오바)
        // 페이징 예시 getResultList 위에 있는 주석
        // 일대다 관계에서만 하면 안됨, member나 delibery 같은덴 상관없음 -> 걍 쓰지마라

        // 컬렉션 패치조인은 1개만 사용할 수 있다, 컬렉션 둘 이상에 패치조인을 사용하면 안된다, 일대다대다 이런식으로 되어 있으면 말도 안되는 양의 데이터를 가져오는 에러가 나기 때문


    }
}
