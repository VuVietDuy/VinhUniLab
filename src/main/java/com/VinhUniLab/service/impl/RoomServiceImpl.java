package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Room;
import com.VinhUniLab.exception.UnSuccessException;
import com.VinhUniLab.repository.RoomRepository;
import com.VinhUniLab.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl extends BaseServiceImpl<Room, RoomRepository> implements RoomService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Room create(Room room) {
        String roomCode = room.getRoomCode();
        if (roomCode == null || roomCode.trim().isEmpty()) {
            throw new UnSuccessException("Mã phòng không được để trống", 400);
        }

        roomCode = roomCode.trim();
        if (repository.existsByRoomCodeIgnoreCase(roomCode)) {
            throw new UnSuccessException("Mã phòng đã tồn tại", 400);
        }

        room.setRoomCode(roomCode);
        return repository.save(room);
    }
}
