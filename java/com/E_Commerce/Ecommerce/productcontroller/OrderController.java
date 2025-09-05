package com.E_Commerce.Ecommerce.productcontroller;


import com.E_Commerce.Ecommerce.domin.PaymentMethod;
import com.E_Commerce.Ecommerce.model.*;
import com.E_Commerce.Ecommerce.productservices.*;
import com.E_Commerce.Ecommerce.response.PaymentLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService ;
    private final UserService userService ;
    private final CartService cartService;
    private final SellerReportService sellerReportService;
    private final SellerService sellerService;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization")String jwt) throws Exception {


        User user = userService.finduserbyJwttoken(jwt);
        Cart cart = cartService.findUserCart(user);

        Set<Order> orders = orderService.createOrder(user , shippingAddress , cart);

        PaymentLinkResponse res = new PaymentLinkResponse();

        return new ResponseEntity<>(res , HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistoryHandler(
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user = userService.finduserbyJwttoken(jwt);
        List<Order> orders = orderService.userOrderHistory(user.getId());
        return  new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getorderById(@PathVariable Long orderId ,
                                              @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.finduserbyJwttoken(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order , HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public  ResponseEntity<OrderItems> getOrderItemById(
            @PathVariable Long orderItemId ,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user = userService.finduserbyJwttoken(jwt);
        OrderItems orderItems = orderService.findOrderItembyId(orderItemId);

        return  new ResponseEntity<>(orderItems , HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> camcelOrder(
            @PathVariable Long orderId ,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user = userService.finduserbyJwttoken(jwt);
        Order order = orderService.cancelOrder(orderId , user);

        Seller seller = sellerService.getSellerbyid(order.getSellerId());
        SellerReport sellerReport = sellerReportService.getSellerReport(seller);
        sellerReport.setCancelorders(sellerReport.getCancelorders()+1);
        sellerReport.setTotalrefunds(sellerReport.getTotalrefunds()+order.getTotasellingprice());
        sellerReportService.updateSellerreport(sellerReport);

        return  ResponseEntity.ok(order);
    }



}
