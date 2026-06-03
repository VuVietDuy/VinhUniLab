package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Computer;
import com.VinhUniLab.entity.Room;
import com.VinhUniLab.exception.UnSuccessException;
import com.VinhUniLab.repository.ComputerRepository;
import com.VinhUniLab.service.ComputerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServiceImpl extends BaseServiceImpl<Computer, ComputerRepository> implements ComputerService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Computer create(Computer computer) {
        String code = computer.getComputerCode();
        if (code == null || code.trim().isEmpty()) {
            throw new UnSuccessException("Mã máy tính không được để trống", 400);
        }

        code = code.trim();
        if (repository.existsByComputerCodeIgnoreCase(code)) {
            throw new UnSuccessException("Mã máy tính đã tồn tại", 400);
        }

        computer.setComputerCode(code);
        return repository.save(computer);
    }
}
