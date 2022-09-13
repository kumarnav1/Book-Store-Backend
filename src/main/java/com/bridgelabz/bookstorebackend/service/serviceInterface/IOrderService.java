package com.bridgelabz.bookstorebackend.service.serviceInterface;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.model.Order;

import java.util.List;

public interface IOrderService {
    public Order insertOrder(OrderDTO orderdto);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Integer id, OrderDTO dto);

    public Order deleteOrderRecord(Integer id);
}
