package com.salon.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO{

    private LocalDateTime appDate;

    private Long master;

    private String serviceName;
}
