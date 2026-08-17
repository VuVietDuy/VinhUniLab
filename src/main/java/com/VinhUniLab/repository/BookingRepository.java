package com.VinhUniLab.repository;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.enums.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends BaseRepository<Booking> {
    Page<Booking> findAllByUserId(Long userId, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.status = 'PENDING' ORDER BY b.createdAt DESC")
    Page<Booking> findTopPendingBookings(Pageable pageable);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.room.id = :roomId " +
           "AND b.status IN :statuses " +
           "AND b.startTime < :endTime AND b.endTime > :startTime")
    boolean existsOverlappingBooking(
            @Param("roomId") Long roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("statuses") List<BookingStatus> statuses
    );

    default boolean existsOverlappingBooking(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
        return existsOverlappingBooking(roomId, startTime, endTime, List.of(BookingStatus.APPROVED, BookingStatus.PENDING));
    }

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
           "AND b.status IN :statuses " +
           "AND b.startTime < :dayEnd AND b.endTime > :dayStart")
    List<Booking> findActiveBookingsByRoomAndDateRange(
            @Param("roomId") Long roomId,
            @Param("dayStart") LocalDateTime dayStart,
            @Param("dayEnd") LocalDateTime dayEnd,
            @Param("statuses") List<BookingStatus> statuses
    );

    default List<Booking> findActiveBookingsByRoomAndDateRange(Long roomId, LocalDateTime dayStart, LocalDateTime dayEnd) {
        return findActiveBookingsByRoomAndDateRange(roomId, dayStart, dayEnd, List.of(BookingStatus.APPROVED, BookingStatus.PENDING));
    }
}


