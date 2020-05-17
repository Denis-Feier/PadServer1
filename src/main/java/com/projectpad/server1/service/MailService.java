package com.projectpad.server1.service;

import com.projectpad.server1.entity.OrderDB;
import com.projectpad.server1.entity.OrderMail;
import com.projectpad.server1.entity.Product;
import com.projectpad.server1.entity.User;
import com.projectpad.server1.exception.UserNotFound;
import com.projectpad.server1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

//    private static final String URL = "http://localhost:8443/comenzi";
    private static final String URL = "https://padservermail.herokuapp.com/comenzi";

    public ResponseEntity<String> postOrderMail(OrderDB orderDB) {
        int userId = orderDB.getUid();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFound("No user found for id: " + userId));
        OrderMail orderMail = new OrderMail();

        orderMail.setUid(userId);
        orderMail.setUserEmail(user.getEmail());
        orderMail.setNrItems(orderDB.getNrItems());
        orderMail.setData(orderDB.getData());
        orderMail.setState(orderDB.getState());
        orderMail.setOid(orderDB.getOid());
        orderMail.setTotalPrice(orderDB.getTotalPrice());

        List<Product> productList = orderService.getProductsFromOrderByID(orderDB.getOid());
        orderMail.setOrderItems(productList);

        ResponseEntity<String> result = restTemplate.postForEntity(URL, orderMail, String.class);

        return result;
    }

}
