package com.VinhUniLab.repository;

import com.VinhUniLab.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends BaseRepository<Booking> {
    Page<Booking> findAllByUserId(Long userId, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.status = 'PENDING' ORDER BY b.createdAt DESC")
    Page<Booking> findTopPendingBookings(Pageable pageable);
}
