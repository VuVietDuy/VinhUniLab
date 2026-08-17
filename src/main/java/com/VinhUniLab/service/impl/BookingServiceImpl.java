package com.VinhUniLab.service.impl;

import com.VinhUniLab.entity.Booking;
import com.VinhUniLab.entity.TimeSlot;
import com.VinhUniLab.enums.BookingStatus;
import com.VinhUniLab.exception.UnSuccessException;
import com.VinhUniLab.model.dto.TimeSlotAvailabilityDTO;
import com.VinhUniLab.repository.BookingRepository;
import com.VinhUniLab.repository.TimeSlotRepository;
import com.VinhUniLab.service.BookingService;
import com.VinhUniLab.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl extends BaseServiceImpl<Booking, BookingRepository> implements BookingService {
    private final TimeSlotRepository timeSlotRepository;

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
    public Booking returnRoom(Long id) {
        Booking booking = getById(id);
        if (booking.getStatus() != BookingStatus.APPROVED) {
            throw new UnSuccessException("Chỉ có thể trả phòng đối với lịch mượn đã được duyệt (APPROVED)", 400);
        }
        booking.setStatus(BookingStatus.RETURNED);
        return this.repository.save(booking);
    }

    @Override
    public Page<Booking> getRecentBookings(Pageable pageable) {
        return this.repository.findTopPendingBookings(pageable);
    }

    @Override
    public List<TimeSlotAvailabilityDTO> getAvailableTimeSlots(Long roomId, LocalDate date) {
        if (roomId == null || date == null) {
            throw new UnSuccessException("Phòng máy và ngày đăng ký không được để trống", 400);
        }

        List<TimeSlot> allSlots = this.timeSlotRepository.findAll();

        LocalDateTime dayStart = date.atStartOfDay();
        LocalDateTime dayEnd = date.atTime(LocalTime.MAX);

        List<Booking> activeBookings = this.repository.findActiveBookingsByRoomAndDateRange(roomId, dayStart, dayEnd);

        List<TimeSlotAvailabilityDTO> result = new ArrayList<>();

        for (TimeSlot slot : allSlots) {
            LocalDateTime slotStart = date.atTime(slot.getStartTime());
            LocalDateTime slotEnd = date.atTime(slot.getEndTime());

            Booking overlapping = activeBookings.stream()
                    .filter(b -> b.getStartTime().isBefore(slotEnd) && b.getEndTime().isAfter(slotStart))
                    .findFirst()
                    .orElse(null);

            boolean isAvailable = (overlapping == null);
            Long bookingId = (overlapping != null) ? overlapping.getId() : null;
            String bookedBy = (overlapping != null && overlapping.getUser() != null) ? overlapping.getUser().getFullName() : null;

            result.add(TimeSlotAvailabilityDTO.builder()
                    .id(slot.getId())
                    .slotName(slot.getSlotName())
                    .startTime(slot.getStartTime())
                    .endTime(slot.getEndTime())
                    .isAvailable(isAvailable)
                    .bookingId(bookingId)
                    .bookedBy(bookedBy)
                    .build());
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Booking create(Booking t) {
        if (t.getRoom() == null || t.getRoom().getId() == null) {
            throw new UnSuccessException("Phòng mượn không được để trống", 400);
        }

        if (t.getStartTime() == null || t.getEndTime() == null) {
            throw new UnSuccessException("Thời gian bắt đầu và kết thúc không được để trống", 400);
        }

        if (t.getStartTime().isAfter(t.getEndTime()) || t.getStartTime().isEqual(t.getEndTime())) {
            throw new UnSuccessException("Thời gian bắt đầu phải trước thời gian kết thúc", 400);
        }

        boolean isBooked = this.repository.existsOverlappingBooking(
                t.getRoom().getId(),
                t.getStartTime(),
                t.getEndTime()
        );

        if (isBooked) {
            throw new UnSuccessException("Phòng đã được đăng ký mượn trong khung giờ này", 400);
        }

        t.setUser(Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUser());

        return this.repository.save(t);
    }
}

