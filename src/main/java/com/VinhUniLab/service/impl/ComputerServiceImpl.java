package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Computer;
import com.VinhUniLab.entity.Room;
import com.VinhUniLab.exception.UnSuccessException;
import com.VinhUniLab.repository.ComputerRepository;
import com.VinhUniLab.repository.RoomRepository;
import com.VinhUniLab.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServiceImpl extends BaseServiceImpl<Computer, ComputerRepository> implements ComputerService {
    @Autowired
    private RoomRepository roomRepository;

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

        if (computer.getRoomId() != null) {
            Room room = roomRepository.findById(computer.getRoomId())
                    .orElseThrow(() -> new UnSuccessException("Không tìm thấy phòng máy", 404));
            computer.setRoom(room);
        } else {
            throw new UnSuccessException("Phòng máy không được để trống", 400);
        }

        computer.setComputerCode(code);
        return repository.save(computer);
    }
}
