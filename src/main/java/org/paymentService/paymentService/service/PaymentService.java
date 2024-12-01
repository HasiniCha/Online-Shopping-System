package org.paymentService.paymentService.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.paymentService.paymentService.dto.PaymentDto;
import org.paymentService.paymentService.exception.PaymentNotFoundException;
import org.paymentService.paymentService.mapper.PaymentMapper;
import org.paymentService.paymentService.repository.PaymentRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceMain{

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;
//    private final PaymentConfirmation confirmationPayment;

    @Override
    public List<PaymentDto> findAll() {

        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::map)
                .distinct()
                .toList();
    }

    @Override
    public PaymentDto findById(Long paymentId) {
        return this.paymentRepository.findById(paymentId)
                .map(PaymentMapper::map)
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id: %d not found", paymentId)));
    }


    @Override

    public PaymentDto create(PaymentDto paymentDto) {
        return PaymentMapper.map(this.paymentRepository.save(PaymentMapper.map(paymentDto)));
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return PaymentMapper.map(this.paymentRepository.save(PaymentMapper.map(paymentDto)));
    }

    @Override
    public void deleteById(Long paymentId) {
        this.paymentRepository.deleteById(paymentId);
    }


}
