package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.OrderDTO.AddOrderDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.OrderResponseDTO;
import com.sber.stepanyan.compclub.service.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/account")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{accountNumber}/orders/")
    public Set<OrderResponseDTO> getAllOrdersByAccountNumber(@Pattern(regexp = "\\d{12}", message = "accountNumber должен состоять из 12 цифр")
                                                            @PathVariable String accountNumber){

        return orderService.getAllOrdersByAccountNumber(accountNumber);
    }

    @GetMapping("/orders/{orderNumber}")
    public OrderResponseDTO getOrderByOrderNumber(@PathVariable long orderNumber){
        return orderService.getOrderByOrderNumber(orderNumber);
    }


    @PostMapping("/orders")
    public Long addOrder(@Valid @RequestBody AddOrderDTO addOrderDTO){

        return orderService.addOrder(addOrderDTO);

    }


}
