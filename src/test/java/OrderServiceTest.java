package org.OrderService.Services;

import org.OrderService.DTO.orderDTO;
import org.OrderService.Models.orders;
import org.OrderService.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testAddToDatabase() {
        orderDTO orderDto = new orderDTO();
        orders orderEntity = new orders();

        when(modelMapper.map(orderDto, orders.class)).thenReturn(orderEntity);
        when(orderRepo.save(orderEntity)).thenReturn(orderEntity);

        orderDTO result = orderService.addtodatabse(orderDto);

        assertEquals(orderDto, result);
        verify(orderRepo, times(1)).save(orderEntity);
    }

    @Test
    public void testGetUniqueUser_WhenIdIsNull() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.getuniqueuser(null));
        assertEquals("Id is null", exception.getMessage());
    }

    @Test
    public void testGetUniqueUser_Success() {
        Long id = 1L;
        orders orderEntity = new orders();
        orderDTO orderDto = new orderDTO();

        when(orderRepo.findById(id)).thenReturn(Optional.of(orderEntity));
        when(modelMapper.map(orderEntity, orderDTO.class)).thenReturn(orderDto);

        orderDTO result = orderService.getuniqueuser(id);

        assertEquals(orderDto, result);
        verify(orderRepo, times(1)).findById(id);
    }
}
