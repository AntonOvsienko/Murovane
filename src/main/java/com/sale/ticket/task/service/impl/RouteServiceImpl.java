package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Route;
import com.sale.ticket.task.repository.RouteRepository;
import com.sale.ticket.task.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }
}
