package com.VinhUniLab.controller;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.model.dto.TimeSlotAvailabilityDTO;
import com.VinhUniLab.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/available-slots")
    public ResponseEntity<List<TimeSlotAvailabilityDTO>> getAvailableTimeSlots(
            @RequestParam("roomId") Long roomId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(service.getAvailableTimeSlots(roomId, date));
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

    @PutMapping("/return")
    public ResponseEntity<?> returnRoom(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(service.returnRoom(id));
    }
}
