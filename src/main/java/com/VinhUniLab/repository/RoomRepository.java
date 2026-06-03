package com.VinhUniLab.repository;

import com.VinhUniLab.entity.Room;

public interface RoomRepository extends BaseRepository<Room> {
    boolean existsByRoomCodeIgnoreCase(String roomCode);
}
