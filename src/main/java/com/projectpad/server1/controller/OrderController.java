package com.projectpad.server1.controller;

import com.projectpad.server1.entity.OrderDB;
import com.projectpad.server1.entity.PostData;
import com.projectpad.server1.entity.Product;
import com.projectpad.server1.exception.OrderNotFound;
import com.projectpad.server1.service.MailService;
import com.projectpad.server1.service.OrderService;
import com.projectpad.server1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailService mailService;

    @PostMapping("/order/post")
    public OrderDB addOrder(@RequestBody List<PostData> items,
                            @RequestHeader("Authorization") String token) {
        String t = token.substring(7);
        userService.unauthorizedUser(t);
        logger.info("New Orders added");
        System.out.println(t);
        items.forEach( item -> System.out.println(item.toString()));

        OrderDB orderDB = orderService.addOrder(t, items);

        ResponseEntity<String> response = mailService.postOrderMail(orderDB);

        logger.info("Server2: " + response.getBody());

        return orderDB;
    }

    @GetMapping("/order/user/{id}")
    public List<OrderDB> getOrderByUserID(//@RequestHeader("Authorization") String token,
                                          @PathVariable int id) {
        logger.info("Get orders for user with id: {}", id);
//        String t = token.substring(7);
//        userService.unauthorizedUser(t);
        return orderService.getOrdersByUserID(id);
    }

    @PutMapping("/order/{id}/status/{status}")
    public OrderDB updateOrderStatus(@PathVariable int id, @PathVariable String status) {
        logger.info("Update status for order with id: {} to status: {}", id, status);
        OrderDB newOrder;
        try {
            newOrder = orderService.updateOrderStatus(id, status);
            logger.info("Order update OK");
        } catch (OrderNotFound onf) {
            logger.error("Order with id {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", onf);
        }
        return newOrder;
    }

    @GetMapping("/order/{id}/product")
    public List<Product> getProductsFromOrderById(@PathVariable int id) {
        logger.info("Get products from order id");
        List<Product> products;
        try {
            products = orderService.getProductsFromOrderByID(id);
            logger.info("Products from order id found");
        } catch (OrderNotFound onf) {
            logger.error("Can't fetch products for order with id: {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found", onf);
        }
        return products;
    }
}
