package com.MicroservicesProject.orderservice.service;

import com.MicroservicesProject.orderservice.dto.InventoryResponse;
import com.MicroservicesProject.orderservice.dto.OrderLineItemsDto;
import com.MicroservicesProject.orderservice.dto.OrderRequest;
import com.MicroservicesProject.orderservice.model.Order;
import com.MicroservicesProject.orderservice.model.OrderLineItems;
import com.MicroservicesProject.orderservice.repository.OrderRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Builder
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

//        order.setOrderLineItemsList(orderLineItems);

//        List<String >skuCode = order.getOrderLineItemsList().stream()
//                .map(OrderLineItems::getSkuCode)
//                .toList();

        // Call Inventory Service, and place order if product is in stock
        InventoryResponse[] inventoryResponsesArray = webClient.get()
                        .uri("http://locahost:8082/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock){
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}
