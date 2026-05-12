package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.repository.BookingRepository;
import com.VinhUniLab.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl extends BaseServiceImpl<Booking, BookingRepository> implements BookingService {
}
