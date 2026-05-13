package com.VinhUniLab.service;

import com.VinhUniLab.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService extends BaseService<Booking> {
    Page<Booking> getMyBookings(Pageable pageable);
    Booking approveBooking(Long id);
    Booking rejectBooking(Long id);
    Booking cancelBooking(Long id);
    Page<Booking> getRecentBookings(Pageable pageable);
}
