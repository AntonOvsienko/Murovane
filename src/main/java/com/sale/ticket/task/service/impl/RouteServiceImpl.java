package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Route;
import com.sale.ticket.task.repository.PaymentRepository;
import com.sale.ticket.task.repository.RouteRepository;
import com.sale.ticket.task.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoute() {
        List<Route> list = routeRepository.findAll();
        list = list.stream().sorted((o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return 1;
            }
            if (o1.getId() < o2.getId()) {
                return -1;
            }
            return 0;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public Route getRouteByIdPayment(Integer id) {
        return routeRepository.getRouteByPaymentId(id);
    }
}
