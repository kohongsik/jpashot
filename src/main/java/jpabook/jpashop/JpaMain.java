package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Order order = new Order();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderPrice(213);
            orderItem.setCount(1);
            order.addOrderItem(orderItem);
            Member member = new Member();
            member.setCity("SEOUL");
            member.setZipcode("21341");
            member.setName("SOMEONE");
            member.addOrder(order);
            em.persist(member);
            em.persist(orderItem);
            em.persist(order);
            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
