package edu.lk.ijse.farm.dto;

import edu.lk.ijse.farm.entity.OrderDetailsEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private String orderId;
    private String customerId;
    private String date;
    private double totalAmount;
    private String status="Pending";
    private ArrayList<OrderDetailDto> orderDetails;

}