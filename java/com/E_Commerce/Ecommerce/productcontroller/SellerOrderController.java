package com.E_Commerce.Ecommerce.productcontroller;

import com.E_Commerce.Ecommerce.domin.OrderStatus;
import com.E_Commerce.Ecommerce.model.Order;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.productservices.OrderService;
import com.E_Commerce.Ecommerce.productservices.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {
    private final OrderService orderService;
    private final SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersHandler(
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        Seller seller = sellerService.getSellerprofile(jwt);
        List<Order> orders = orderService.sellerOrder(seller.getId());

        return  new ResponseEntity<>(orders , HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderid}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(
            @RequestHeader("Authorization")String jwt,
            @PathVariable Long orderid ,
            @PathVariable OrderStatus orderStatus
            ) throws Exception {

        Order order = orderService.updateOrderStatus(orderid, orderStatus);

        return  new ResponseEntity<>(order , HttpStatus.ACCEPTED);
    }
}
