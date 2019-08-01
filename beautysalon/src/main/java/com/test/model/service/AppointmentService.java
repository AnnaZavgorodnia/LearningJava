package com.test.model.service;

import com.test.model.dao.AppointmentDao;
import com.test.model.dao.DaoFactory;
import com.test.model.dao.MasterDao;
import com.test.model.entity.Appointment;
import com.test.model.entity.Master;
import com.test.model.entity.Role;

import java.util.List;

public class AppointmentService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Appointment> getAllAppointmentsByClientOrMaster_Username(String username, Role role){
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAllByClientOrMaster_Username(username, role);
        }
    }

    public List<Appointment> findAll() {
        try (AppointmentDao dao = daoFactory.createAppointmentDao()) {
            return dao.findAll();
        }
    }
}
