package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Room;
import com.VinhUniLab.repository.RoomRepository;
import com.VinhUniLab.service.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends BaseServiceImpl<Room, RoomRepository> implements RoomService {
}
