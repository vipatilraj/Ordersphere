package com.example.ordersphere.controller;

import com.example.ordersphere.DTO.OrderDTO;
import com.example.ordersphere.DTO.OrderItemDTO;
import com.example.ordersphere.DTO.OrderResponseDTO;
import com.example.ordersphere.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void PlaceOrderSuccessfully() throws Exception {

        OrderDTO request = new OrderDTO();
        request.setCustomerId(1L);
        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(2);

        request.setItems(List.of(itemDTO));

        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(1L);
        response.setTotalAmount(5000);

        Mockito.when(orderService.createOrder(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.totalAmount").value(5000));
    }
}
