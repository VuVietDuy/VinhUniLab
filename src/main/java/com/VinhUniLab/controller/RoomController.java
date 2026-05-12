package com.VinhUniLab.controller;

import com.VinhUniLab.entity.Room;
import com.VinhUniLab.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/rooms")
public class RoomController extends BaseController<Room, RoomService> {
}
