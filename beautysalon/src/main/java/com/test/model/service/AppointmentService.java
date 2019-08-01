package com.test.model.service;

import com.test.model.dao.AppointmentDao;
import com.test.model.dao.DaoFactory;
import com.test.model.dao.MasterDao;
import com.test.model.entity.Appointment;
import com.test.model.entity.Master;

import java.util.List;

public class AppointmentService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Appointment> getAllAppointmentsByClient_Username(String username){
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAllByClient_Username(username);
        }
    }

    public List<Appointment> getAllAppointmentsByMaster_Username(String username) {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAllByMaster_Username(username);
        }
    }

    public List<Appointment> findAll() {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAll();
        }
    }
}
