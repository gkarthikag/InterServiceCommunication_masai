package com.example.OrderServicePro.repo;

import com.example.OrderServicePro.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {


}
