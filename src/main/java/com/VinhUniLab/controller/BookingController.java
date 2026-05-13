package com.VinhUniLab.controller;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController extends BaseController<Booking, BookingService> {
    @GetMapping("/mine")
    public ResponseEntity<Page<Booking>> getMyBookings(Pageable pageable) {
        return ResponseEntity.ok(service.getMyBookings(pageable));
    }

    @GetMapping("/recent")
    public ResponseEntity<Page<Booking>> getRecentBookings(Pageable pageable) {
        return ResponseEntity.ok(service.getRecentBookings(pageable));
    }

    @PutMapping("/approve")
    public ResponseEntity<?> approveBooking(@RequestParam(value = "id") Long id) {
        return  ResponseEntity.ok(service.approveBooking(id));
    }

    @PutMapping("/reject")
    public ResponseEntity<?> rejectBooking(@RequestParam(value = "id") Long id) {
        return  ResponseEntity.ok(service.rejectBooking(id));
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestParam(value = "id") Long id) {
        return  ResponseEntity.ok(service.cancelBooking(id));
    }
}
