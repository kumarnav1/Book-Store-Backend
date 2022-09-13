package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.model.BookData;
import com.bridgelabz.bookstorebackend.model.Order;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.repository.IBookRepository;
import com.bridgelabz.bookstorebackend.repository.IOrderRepository;
import com.bridgelabz.bookstorebackend.repository.IUserRegistrationRepository;
import com.bridgelabz.bookstorebackend.service.serviceInterface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepo;
    @Autowired
    private IBookRepository BookDataRepo;
    @Autowired
    private IUserRegistrationRepository userRepo;

    public Order insertOrder(OrderDTO orderdto) {
        Optional<BookData> data = BookDataRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
        if (data.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() < data.get().getQuantity()) {
                Order newOrder = new Order(data.get().getPrice(),orderdto.getQuantity(), orderdto.getAddress(), data.get(), user.get(), orderdto.isCancel(),data.get().getBookName());
                orderRepo.save(newOrder);
                data.get().setQuantity(data.get().getQuantity() - orderdto.getQuantity());
                return newOrder;
            } else {
                return null;//Requested quantity is not available
            }
        } else {
            return null;//data or User doesn't exists
        }
    }
    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepo.findAll();
        return orderList;
    }

    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            return order.get();

        } else {
            return null;//Order Record doesn't exists
        }
    }

    public Order updateOrderRecord(Integer id, OrderDTO dto) {
        Optional<Order> order = orderRepo.findById(id);
        Optional<BookData> book = BookDataRepo.findById(dto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(dto.getUserId());
        if (order.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() < book.get().getQuantity()) {
                    Order newOrder = new Order(id, dto.getQuantity(), dto.getAddress(), book.get(), user.get(), dto.isCancel(),book.get().getBookName());
                    orderRepo.save(newOrder);
                    return newOrder;
                } else {
                    return null;//Requested quantity is not available
                }
            } else {
                return null;//book or User doesn't exists

            }

        } else {
            return null;//Order Record doesn't exist
        }
    }

    public Order deleteOrderRecord(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            orderRepo.deleteById(id);
            return order.get();

        } else {
            return null;//Order Record doesn't exists
        }
    }
}
