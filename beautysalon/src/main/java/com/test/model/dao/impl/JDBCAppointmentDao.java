package com.test.model.dao.impl;

import com.test.model.dao.AppointmentDao;
import com.test.model.dao.mapper.*;
import com.test.model.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JDBCAppointmentDao implements AppointmentDao {

    private Connection connection;

    //language=SQL
    private String FIND_APPOINTMENTS_BY_CLIENT_USERNAME = "select app_id, app_date, app_time, m_id, m_full_name,\n" +
            "       m_position, m_email, m_password, m_username,\n" +
            "       m_instagram, m_role, id s_id, name s_name, price s_price, c_id,\n" +
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

    //language=SQL
    private String FIND_APPOINTMENTS_BY_MASTER_USERNAME = "select app_id, app_date, app_time, m_id, m_full_name,\n" +
            "       m_position, m_email, m_password, m_username,\n" +
            "       m_instagram, m_role, id s_id, name s_name, price s_price, c_id,\n" +
            "       c_username, c_full_name, c_email, c_password,c_role from\n" +
            "    (select * from\n" +
            "        (select ap.id app_id, app_date, app_time, client_id, service_id,\n" +
            "                m_id, m_full_name, m_position, m_email, m_username, m_password,\n" +
            "                m_role, m_instagram\n" +
            "         from all_appointments ap\n" +
            "                  inner join\n" +
            "              (select m.id m_id, full_name m_full_name, position m_position, email m_email, username m_username, password m_password, role m_role, instagram m_instagram\n" +
            "               from masters m\n" +
            "                        inner join users u2\n" +
            "                                   on m.id = u2.id) ms\n" +
            "                on ap.master_id=ms.m_id\n"+
            "         where m_username=?) f\n" +
            "            left join\n" +
            "              (select c.id c_id, username c_username, email c_email, password c_password, role c_role, full_name c_full_name from clients c inner join users u on c.id = u.id) cs\n" +
            "              on client_id = c_id) f1\n" +
            "        left join services ss on f1.service_id=ss.id;";

    //language=SQL
    private String FIND_ALL_APPOINTMENTS = "select app_id, app_date, app_time, m_id, m_full_name,\n" +
            "       m_position, m_email, m_password, m_username,\n" +
            "       m_instagram, m_role, id s_id, name s_name, price s_price, c_id,\n" +
            "       c_username, c_full_name, c_email, c_password,c_role from\n" +
            "    (select * from\n" +
            "        (select ap.id app_id, app_date, app_time, master_id, service_id,\n" +
            "                c.id c_id, username c_username, email c_email, password c_password, role c_role, full_name c_full_name\n" +
            "         from all_appointments ap\n" +
            "                  inner join\n" +
            "              (clients c inner join users u on c.id = u.id)\n" +
            "              on ap.client_id = c.id) f\n" +
            "            left join\n" +
            "        (select m.id m_id, full_name m_full_name, position m_position, email m_email, username m_username, password m_password, role m_role, instagram m_instagram\n" +
            "         from masters m\n" +
            "                  inner join users u2\n" +
            "                             on m.id = u2.id) f1\n" +
            "        on f.master_id=m_id) f3\n" +
            "        left join services ss on f3.service_id=ss.id;";

    //language=SQL
    private String FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE = "select ap.id app_id, app_date, app_time from all_appointments ap where master_id=(?) and app_date=(?)";

    //language=SQL
    private String INSERT_APPOINTMENT = "insert into all_appointments (app_date, app_time, client_id, master_id, service_id)\n" +
            "values (?,?,?,?,?);";

    JDBCAppointmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Appointment> findAllByClientOrMaster_Username(String username, Role role) {

        try (PreparedStatement ps =
                     connection.prepareStatement(
                             role == Role.CLIENT
                                     ? FIND_APPOINTMENTS_BY_CLIENT_USERNAME
                                     : FIND_APPOINTMENTS_BY_MASTER_USERNAME)) {

            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            return extractListAppointments(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void create(Appointment entity) {
        try(PreparedStatement ps =
                     connection.prepareStatement(INSERT_APPOINTMENT)){

            ps.setString(1,entity.getAppDate().toString());
            ps.setString(2,entity.getAppTime().toString());
            ps.setLong(3,entity.getClient().getId());
            ps.setLong(4,entity.getMaster().getId());
            ps.setLong(5,entity.getService().getId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Appointment> findAllByMasterIdAndDate(Long masterId, LocalDate date) {
        try (PreparedStatement ps =
                     connection.prepareStatement(FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE)) {

            ps.setLong(1,masterId);
            ps.setString(2,date.toString());

            ResultSet rs = ps.executeQuery();

            AppointmentMapper appMapper = new AppointmentMapper();

            List<Appointment> apps = new ArrayList<>();

            while (rs.next()) {
                Appointment app = appMapper
                        .extractFromResultSet(rs);
                apps.add(app);
            }

            return apps;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_APPOINTMENTS);
            return extractListAppointments(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Appointment> extractListAppointments(ResultSet rs) throws SQLException {

        AppointmentMapper appMapper = new AppointmentMapper();
        MasterMapper masterMapper = new MasterMapper();
        ServiceMapper serviceMapper = new ServiceMapper();
        ClientMapper clientMapper = new ClientMapper();

        Map<Long, Master> masterCache = new HashMap<>();
        Map<Long, Service> serviceCache = new HashMap<>();
        Map<Long, Client> clientCache = new HashMap<>();

        List<Appointment> apps = new ArrayList<>();

        while (rs.next()) {
            Appointment app = appMapper
                    .extractFromResultSet(rs);
            Master master = masterMapper.extractFromResultSet(rs);
            Service service = serviceMapper.extractFromResultSet(rs);
            Client client = clientMapper.extractFromResultSet(rs);
            master = masterMapper.makeUnique(masterCache, master);
            service = serviceMapper.makeUnique(serviceCache, service);
            client = clientMapper.makeUnique(clientCache, client);
            app.setMaster(master);
            app.setService(service);
            app.setClient(client);
            apps.add(app);
        }

        return apps;
    }

    private Appointment extractAppointment(ResultSet rs){
        return null;
    }
}
