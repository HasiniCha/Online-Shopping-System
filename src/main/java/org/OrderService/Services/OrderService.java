package org.OrderService.Services;


import org.OrderService.DTO.orderDTO;
import org.OrderService.Models.cart;
import org.OrderService.Models.cartItem;
import org.OrderService.Models.orderItem;
import org.OrderService.Models.orders;
import org.OrderService.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public orderDTO addtodatabse(orderDTO orderdto) {
        orders orderEntity = modelMapper.map(orderdto, orders.class);
        orders savedOrder = orderRepo.save(orderEntity);
        return modelMapper.map(savedOrder, orderDTO.class);

    }

    public List<orderDTO> getallorders(){
        List<orders>ordersList = orderRepo.findAll();
        return modelMapper.map(ordersList,  new TypeToken<List<orderDTO>>(){}.getType());
    }
    public orderDTO getuniqueuser(Long id){

        if(id == null){
                throw new RuntimeException("Id is null");
        }else{
            Optional<orders> optionalOrders = orderRepo.findById(id);
            if (optionalOrders.isEmpty()) {
                throw new RuntimeException("Order not found for id: " + id);
            }
            return modelMapper.map(optionalOrders.get(), orderDTO.class);
        }


    }

    public orderDTO deleteorder(Long id){
        if(id == null){
            throw new RuntimeException("Id is null");
        }
        else{
            Optional<orders> optionalOrders = orderRepo.findById(id);
            if (optionalOrders.isEmpty()) {
                throw new RuntimeException("Order not found for id: " + id);
            }
            orderRepo.deleteById(id);
            return modelMapper.map(optionalOrders.get(), orderDTO.class);
        }
    }
    public double calculateOrderTotal(Long orderId) {
        orders orders = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<orderItem> items = orders.getOrderItems();
        double total = 0;
        for (orderItem item : items) {
            total += item.getTotalPrice() ;
        }
        return total;

    }


}
