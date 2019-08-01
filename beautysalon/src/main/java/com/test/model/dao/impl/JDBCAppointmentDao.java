package com.test.model.dao.impl;

import com.test.App;
import com.test.model.dao.AppointmentDao;
import com.test.model.dao.mapper.AppointmentMapper;
import com.test.model.dao.mapper.MasterMapper;
import com.test.model.dao.mapper.ServiceMapper;
import com.test.model.dao.mapper.UserMapper;
import com.test.model.entity.Appointment;
import com.test.model.entity.Master;
import com.test.model.entity.Service;
import com.test.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCAppointmentDao implements AppointmentDao {
    //language=SQL
    String FIND_APPOINTMENTS_BY_CLIENT_USERNAME = "select app_id, app_date, app_time, m_id, m_full_name,\n" +
            "       m_position, m_email, m_password, m_username,\n" +
            "       m_instagram, m_role, name s_name, price s_price, c_id,\n" +
            "       c_username, c_full_name, c_email, c_password,c_role from\n" +
            "    (select * from\n" +
            "        (select ap.id app_id, app_date, app_time, master_id, service_id,\n" +
            "                c.id c_id, username c_username, email c_email, password c_password, role c_role, full_name c_full_name\n" +
            "        from all_appointments ap\n" +
            "            inner join\n" +
            "                (clients c inner join users u on c.id = u.id)\n" +
            "            on ap.client_id = c.id\n" +
            "        where username=?) f\n" +
            "            left join\n" +
            "            (select m.id m_id, full_name m_full_name, position m_position, email m_email, username m_username, password m_password, role m_role, instagram m_instagram\n" +
            "            from masters m\n" +
            "                inner join users u2\n" +
            "                    on m.id = u2.id) f1\n" +
            "                on f.master_id=m_id) f3\n" +
            "left join services ss on f3.service_id=ss.id;";

    String FIND_APPOINTMENTS_BY_MASTER_USERNAME = "";

    private Connection connection;

    JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Appointment> findAllByClient_Username(String username) {

        try (PreparedStatement ps = connection.prepareStatement(FIND_APPOINTMENTS_BY_CLIENT_USERNAME)) {

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            AppointmentMapper appMapper = new AppointmentMapper();
            MasterMapper masterMapper = new MasterMapper();
            ServiceMapper serviceMapper = new ServiceMapper();

            List<Appointment> apps = new ArrayList<>();

            while (rs.next()) {
                Appointment app = appMapper
                        .extractFromResultSet(rs);
                Master master = masterMapper.extractFromResultSet(rs);
                Service service = serviceMapper.extractFromResultSet(rs);
                app.setMaster(master);
                app.setService(service);
                apps.add(app);
            }
            return apps;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Appointment> findAllByMaster_Username(String username) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_APPOINTMENTS_BY_CLIENT_USERNAME)) {

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            AppointmentMapper appMapper = new AppointmentMapper();
            MasterMapper masterMapper = new MasterMapper();
            ServiceMapper serviceMapper = new ServiceMapper();

            List<Appointment> apps = new ArrayList<>();

            while (rs.next()) {
                Appointment app = appMapper
                        .extractFromResultSet(rs);
                Master master = masterMapper.extractFromResultSet(rs);
                Service service = serviceMapper.extractFromResultSet(rs);
                app.setMaster(master);
                app.setService(service);
                apps.add(app);
            }
            return apps;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void create(Appointment entity) {

    }

    @Override
    public Optional<Appointment> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
