package com.sales.quiz.repository;

import com.sales.quiz.model.OrderProduct;
import com.sales.quiz.model.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {


    @Transactional
    void deleteAllByPkOrderId(Long orderId);
}
