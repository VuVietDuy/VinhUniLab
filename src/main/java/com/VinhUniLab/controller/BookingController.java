package com.VinhUniLab.controller;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.service.BookingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/bookings")
public class BookingController extends BaseController<Booking, BookingService> {
}
