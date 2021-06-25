package com.sber.stepanyan.compclub.controller;

import com.sber.stepanyan.compclub.DTO.OrderDTO.AddOrderDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.ConfirmOrderDTO;
import com.sber.stepanyan.compclub.DTO.OrderDTO.OrderResponseDTO;
import com.sber.stepanyan.compclub.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @ApiOperation(value = "получить все заказы по номеру счета")
    public Set<OrderResponseDTO> getAllOrdersByAccountNumber(@Pattern(regexp = "\\d{12}", message = "accountNumber должен состоять из 12 цифр")
                                                            @PathVariable String accountNumber){

        return orderService.getAllOrdersByAccountNumber(accountNumber);
    }

    @GetMapping("/orders/{orderNumber}")
    @ApiOperation(value = "получить заказ по номеру")
    public OrderResponseDTO getOrderByOrderNumber(@PathVariable long orderNumber){
        return orderService.getOrderByOrderNumber(orderNumber);
    }


    @PostMapping("/orders")
    @ApiOperation(value = "добавить заказ")
    public Long addOrder(@Valid @RequestBody AddOrderDTO addOrderDTO){
        return orderService.addOrder(addOrderDTO);
    }

    @PutMapping("/orders")
    @ApiOperation(value = "оплатить заказ")
    public OrderResponseDTO confirmOrder(@Valid @RequestBody ConfirmOrderDTO confirmOrderDTO){
        return orderService.confirmOrder(confirmOrderDTO);
    }


}
