package com.MicroservicesProject.orderservice.controller;

import com.MicroservicesProject.orderservice.dto.OrderRequest;
import com.MicroservicesProject.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully ";

    }

//    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
//        return "Oops! Something went wrong, please order after some time!";
//    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<OrderRequest> getAllOrders(){
//        return OrderService.getAllOrders();
//    }

}
