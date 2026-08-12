package com.VinhUniLab.controller;

import com.VinhUniLab.entity.TimeSlot;
import com.VinhUniLab.service.TimeSlotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/time-slots")
public class TimeSlotController extends BaseController<TimeSlot, TimeSlotService> {
}
