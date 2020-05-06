package com.projectpad.server1.service;

import com.projectpad.server1.entity.*;
import com.projectpad.server1.exception.OrderNotFound;
import com.projectpad.server1.repository.OrderDBRepository;
import com.projectpad.server1.repository.OrderItemDBRepository;
import com.projectpad.server1.repository.ProductRepository;
import com.projectpad.server1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderItemDBRepository orderItemDBRepository;

    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public OrderDB addOrder(String userToken, List<PostData> postData) {
        User user = userService.findByToken(userToken);
        OrderDB orderDB = new OrderDB();
        orderDB.setUid(user.getId());
        orderDB.setNrItems(postData.size());
        orderDB.setData(new Date());
        orderDB.setState("Sent");
        orderDB.setTotalPrice(productService.getTotalPrice(postData));
        OrderDB fromDB = orderDBRepository.save(orderDB);
        List<OrderItemDB> orderItemDBS = postData.stream().map(item -> {
            OrderItemDB orderItem = new OrderItemDB();
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPid(item.getPid());
            orderItem.setOidFik(fromDB.getOid());
            return orderItem;
        }).collect(Collectors.toList());
        fromDB.setOrderItems(orderItemDBS);
        return orderDBRepository.save(fromDB);
    }

    public List<OrderDB> getOrdersByUserID(int userID) {
        return orderDBRepository.getOrderDBSByUid(userID);
    }

    public OrderDB updateOrderStatus(int id, String status) {
        OrderDB thisOrder = orderDBRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("No order found for id: " + id));
        OrderDB updatedOrder = thisOrder.toBuilder().state(status).build();
        return orderDBRepository.save(updatedOrder);
    }

    public List<Product> getProductsFromOrderByID(int id) {
        OrderDB thisOrder = orderDBRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound("No order found for id: " + id));

        List<OrderItemDB> orderItemDBS = thisOrder.getOrderItems();
        List<Integer> orderIntegers = orderItemDBS
                        .stream()
                        .map(OrderItemDB::getPid)
                        .collect(Collectors.toList());
        return productRepository.findAllById(orderIntegers);
    }
}
