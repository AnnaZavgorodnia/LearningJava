package com.test.model.dao.impl;

import com.test.model.dao.MasterDao;
import com.test.model.dao.mapper.MasterMapper;
import com.test.model.dao.mapper.ServiceMapper;
import com.test.model.dao.mapper.UserMapper;
import com.test.model.entity.Client;
import com.test.model.entity.Master;
import com.test.model.entity.Service;
import com.test.model.entity.User;

import java.sql.*;
import java.util.*;

public class JDBCMasterDao implements MasterDao {

    //language=SQL
    private String FIND_ALL_MASTERS = "select m.id m_id, email m_email, full_name m_full_name, password m_password, role m_role, instagram m_instagram, position m_position, username m_username from users right join masters m on users.id = m.id";


    //language=SQL
    private String FIND_MASTER_BY_ID = "select m_id, m_email, m_full_name,\n" +
            "       m_password, m_role, m_instagram,\n" +
            "       m_position, m_username, s_id,\n" +
            "       name s_name, price s_price\n" +
            "from\n" +
            "    (select m_id, m_email, m_full_name, m_password, m_role, m_instagram,\n" +
            "           m_position, m_username, service_id s_id\n" +
            "    from\n" +
            "                  (select m.id m_id, email m_email, full_name m_full_name,\n" +
            "                           password m_password, role m_role,\n" +
            "                           instagram m_instagram, position m_position,\n" +
            "                           username m_username\n" +
            "                    from users\n" +
            "                        right join\n" +
            "                        masters m\n" +
            "                            on users.id = m.id \n" +
            "                    where m.id=(?)) f \n" +
            "                      left join master_service ms \n" +
            "                          on m_id=ms.master_id) f2\n" +
            "        left join services ss\n" +
            "            on f2.s_id=ss.id;";


    private Connection connection;

    JDBCMasterDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Master entity) {

    }

    @Override
    public Optional<Master> findById(Long id) {

        try (PreparedStatement ps =
                     connection.prepareStatement(FIND_MASTER_BY_ID)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            MasterMapper masterMapper = new MasterMapper();
            ServiceMapper serviceMapper = new ServiceMapper();

            Map<Long, Master> masterCache = new HashMap<>();
            Map<Long, Service> serviceCache = new HashMap<>();

            List<Service> services = new ArrayList<>();

            Master master = null;
            while (rs.next()) {
                master = masterMapper.extractFromResultSet(rs);
                Service service = serviceMapper.extractFromResultSet(rs);

                master = masterMapper.makeUnique(masterCache, master);
                service = serviceMapper.makeUnique(serviceCache, service);

                services.add(service);
            }
            if(master != null){
                master.setServices(services);
                return Optional.of(master);
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Master> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_MASTERS);

            MasterMapper masterMapper = new MasterMapper();
            List<Master> masters = new ArrayList<>();

            while (rs.next()) {
                Master master = masterMapper
                        .extractFromResultSet(rs);
                masters.add(master);
            }
            return masters;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Master entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }
}
