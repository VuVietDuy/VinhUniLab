package com.VinhUniLab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@Builder
public class DateTimeFormatDto {
    private Boolean isDateFormat;
    private ZonedDateTime date;
}
