package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.domin.OrderStatus;
import com.E_Commerce.Ecommerce.domin.PaymentStatus;
import com.E_Commerce.Ecommerce.model.*;
import com.E_Commerce.Ecommerce.productservices.OrderService;
import com.E_Commerce.Ecommerce.repo.Addressrepo;
import com.E_Commerce.Ecommerce.repo.OrderItemsRepo;
import com.E_Commerce.Ecommerce.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceimpl implements OrderService {

    public final OrderRepo orderRepo;
    public final Addressrepo addressrepo;
    private final OrderItemsRepo orderItemsRepo;
    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if(!user.getAddress().contains(shippingAddress)){
            user.getAddress().add(shippingAddress);
        }
        Address address = addressrepo.save(shippingAddress);

        Map<Long , List<CartItems>> itemsBySeller = cart.getCartItems().stream().collect(Collectors.groupingBy(item->item.getProduct()
                .getSeller().getId()));

        Set<Order> orders = new HashSet<>();

        for (Map.Entry<Long , List<CartItems>> entry:itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItems> items = entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(
                    CartItems::getSellingptice
            ).sum();

            int totalItems = items.stream().mapToInt(CartItems::getQuantity).sum();

            Order createdOrder = new Order();
            createdOrder.setUser(user);
            createdOrder.setSellerId(sellerId);
            createdOrder.setTotalprice(totalOrderPrice);
            createdOrder.setTotasellingprice(totalOrderPrice);
            createdOrder.setTotalitem(totalItems);
            createdOrder.setShippingaddress(address);
            createdOrder.setOrderStatus(OrderStatus.PENDING);
            createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
             Order savedOrder = orderRepo.save(createdOrder);

             orders.add(savedOrder);

             List<OrderItems> orderItems = new ArrayList<>();

             for (CartItems item : items){
                 OrderItems orderItem = new OrderItems();
                 orderItem.setOrder(savedOrder);
                 orderItem.setMrpPrice(item.getMrp());
                 orderItem.setProduct(item.getProduct());
                 orderItem.setQuantity(item.getQuantity());
                 orderItem.setSize(item.getSize());
                 orderItem.setUserId(item.getId());
                 orderItem.setSellingPrice(item.getSellingptice());

                 savedOrder.getOrderItems().add(orderItem);

                 OrderItems created = orderItemsRepo.save(orderItem);
                 orderItems.add(created);
             }
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long id) throws Exception {

        return orderRepo.findById(id).orElseThrow(()->
                new Exception("order not found....."));

    }

    @Override
    public List<Order> userOrderHistory(Long userid) {


        return orderRepo.findByuserId(userid);
    }

    @Override
    public List<Order> sellerOrder(Long sellerId) {
        return orderRepo.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepo.save(order) ;
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {

        Order order = findOrderById(orderId);

        if(user.getId()!=order.getUser().getId()){
            throw new Exception("you cant cancel this order ...");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepo.save(order) ;

    }

    @Override
    public OrderItems findOrderItembyId(Long id) throws Exception {
        return orderItemsRepo.findById(id).orElseThrow(()->
               new Exception("order item not exsists.....") );
    }
}
