package com.springbootdistributed.repo;

import com.springbootdistributed.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepo extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

}

