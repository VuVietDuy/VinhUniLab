package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Computer;
import com.VinhUniLab.repository.ComputerRepository;
import com.VinhUniLab.service.ComputerService;
import org.springframework.stereotype.Service;

@Service
public class ComputerServiceImpl extends BaseServiceImpl<Computer, ComputerRepository> implements ComputerService {
}
