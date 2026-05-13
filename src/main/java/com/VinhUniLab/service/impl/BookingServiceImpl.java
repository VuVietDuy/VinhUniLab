package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.enums.BookingStatus;
import com.VinhUniLab.repository.BookingRepository;
import com.VinhUniLab.service.BookingService;
import com.VinhUniLab.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl extends BaseServiceImpl<Booking, BookingRepository> implements BookingService {
    @Override
    public Page<Booking> getMyBookings(Pageable pageable) {
        Long userId = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUserId();
        return this.repository.findAllByUserId(userId, pageable);
    }

    @Override
    public Booking approveBooking(Long id) {
        Booking booking = getById(id);
        booking.setStatus(com.VinhUniLab.enums.BookingStatus.APPROVED);
        return this.repository.save(booking);
    }

    @Override
    public Booking rejectBooking(Long id) {
        Booking booking = getById(id);
        booking.setStatus(BookingStatus.REJECTED);
        return this.repository.save(booking);
    }

    @Override
    public Booking cancelBooking(Long id) {
        Booking booking = getById(id);
        booking.setStatus(BookingStatus.CANCELLED);
        return this.repository.save(booking);
    }

    @Override
    public Page<Booking> getRecentBookings(Pageable pageable) {
        return this.repository.findTopPendingBookings(pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Booking create(Booking t) {
        t.setUser(Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUser());
        return this.repository.save(t);
    }
}
