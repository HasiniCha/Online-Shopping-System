package org.OrderService.Controller;

import org.OrderService.DTO.orderDTO;
import org.OrderService.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class orderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrdes")
    public orderDTO addorders(@RequestBody orderDTO orderDTO) {
        return orderService.addtodatabse(orderDTO);
    }

    @GetMapping("/GetallOrders")
    public List<orderDTO> getallorders(){
        return  orderService.getallorders();
    }

    @GetMapping("/getUnique")
    public orderDTO getunique(@RequestParam Long id){
        return  orderService.getuniqueuser(id);
    }
    
    @DeleteMapping("/DeltebyID")
    public orderDTO deletebyID(@RequestParam Long id){
        return orderService.deleteorder(id);
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long id) {
        double total = orderService.calculateOrderTotal(id);
        return ResponseEntity.ok(total);
    }
}
