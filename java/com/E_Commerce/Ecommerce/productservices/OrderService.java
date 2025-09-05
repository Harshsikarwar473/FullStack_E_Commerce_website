package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.domin.OrderStatus;
import com.E_Commerce.Ecommerce.model.*;

import java.util.List;
import java.util.Set;

public interface OrderService {

    Set<Order> createOrder(User user , Address shippingAddress , Cart Address);
    Order findOrderById(Long id ) throws Exception;
    List<Order> userOrderHistory(Long userid);
    List<Order> sellerOrder(Long sellerId);
    Order updateOrderStatus(Long orderId , OrderStatus orderStatus) throws Exception;
    Order cancelOrder(Long orderId , User user) throws Exception;
    OrderItems findOrderItembyId(Long id) throws Exception;
}
