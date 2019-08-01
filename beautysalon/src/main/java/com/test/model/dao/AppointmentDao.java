package com.test.model.dao;

import com.test.model.entity.Appointment;

import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment>{
    List<Appointment> findAllByClient_Username(String username);

    List<Appointment> findAllByMaster_Username(String username);
}
