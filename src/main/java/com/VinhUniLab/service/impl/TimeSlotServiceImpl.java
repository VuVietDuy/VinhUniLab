package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.TimeSlot;
import com.VinhUniLab.repository.TimeSlotRepository;
import com.VinhUniLab.service.TimeSlotService;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotServiceImpl extends BaseServiceImpl<TimeSlot, TimeSlotRepository> implements TimeSlotService {
}
