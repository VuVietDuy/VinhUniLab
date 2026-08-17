package com.VinhUniLab.service;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.model.dto.TimeSlotAvailabilityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BookingService extends BaseService<Booking> {
    Page<Booking> getMyBookings(Pageable pageable);
    Booking approveBooking(Long id);
    Booking rejectBooking(Long id);
    Booking cancelBooking(Long id);
    Booking returnRoom(Long id);
    Page<Booking> getRecentBookings(Pageable pageable);
    List<TimeSlotAvailabilityDTO> getAvailableTimeSlots(Long roomId, LocalDate date);
}
