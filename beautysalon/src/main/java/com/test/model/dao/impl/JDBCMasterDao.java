package com.test.model.dao.impl;

import com.test.model.dao.MasterDao;
import com.test.model.dao.mapper.MasterMapper;
import com.test.model.dao.mapper.ServiceMapper;
import com.test.model.entity.Master;
import com.test.model.entity.Service;

import java.sql.*;
import java.util.*;

public class JDBCMasterDao implements MasterDao {

    //language=SQL
    private String FIND_ALL_MASTERS = "select m.id m_id, email m_email, full_name m_full_name, password m_password, role m_role, instagram m_instagram, position m_position, username m_username, image_path m_image_path from users right join masters m on users.id = m.id";

    //language=SQL
    private String FIND_MASTER_BY_ID = "select m_id, m_email, m_full_name,\n" +
            "       m_password, m_role, m_instagram,\n" +
            "       m_position, m_username, s_id, m_image_path,\n" +
            "       name s_name, price s_price\n" +
            "from\n" +
            "    (select m_id, m_email, m_full_name, m_password, m_role, m_instagram,\n" +
            "           m_position, m_username, m_image_path, service_id s_id\n" +
            "    from\n" +
            "                  (select m.id m_id, email m_email, full_name m_full_name,\n" +
            "                           password m_password, role m_role,\n" +
            "                           instagram m_instagram, position m_position,\n" +
            "                           username m_username, image_path m_image_path\n" +
            "                    from users\n" +
            "                        right join\n" +
            "                        masters m\n" +
            "                            on users.id = m.id \n" +
            "                    where m.id=(?)) f \n" +
            "                      left join master_service ms \n" +
            "                          on m_id=ms.master_id) f2\n" +
            "        left join services ss\n" +
            "            on f2.s_id=ss.id;";

    //language=SQL
    private String INSERT_USER = "insert into users (email, full_name, password, role, username) values(?,?,?,?,?);";

    //language=SQL
    private String INSERT_MASTER = "insert into masters (id,instagram,position, image_path) values(?,?,?,?);";

    //language=SQL
    private String INSERT_MASTER_SERVICE = "insert into master_service (master_id, service_id) values(?,?);";

    private Connection connection;

    JDBCMasterDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Master entity) {
        try (PreparedStatement insertUserPs =
                     connection.prepareStatement(INSERT_USER,
                             Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertMasterPs =
                     connection.prepareStatement(INSERT_MASTER);
            PreparedStatement insertMasterServicePs =
                     connection.prepareStatement(INSERT_MASTER_SERVICE)) {

            connection.setAutoCommit(false);

            insertUserPs.setString(1, entity.getEmail());
            insertUserPs.setString(2, entity.getFullName());
            insertUserPs.setString(3, entity.getPassword());
            insertUserPs.setString(4, entity.getRole().name());
            insertUserPs.setString(5, entity.getUsername());

            int affectedRows = insertUserPs.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertUserPs.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    long id = generatedKeys.getLong(1);
                    
                    insertMasterPs.setLong(1, id);
                    insertMasterPs.setString(2,entity.getInstagram());
                    insertMasterPs.setString(3,entity.getPosition().name());
                    insertMasterPs.setString(4,entity.getImagePath());
                    
                    insertMasterPs.executeUpdate();

                    for (Service service: entity.getServices()) {
                        insertMasterServicePs.setLong(1,id);
                        insertMasterServicePs.setLong(2,service.getId());
                        insertMasterServicePs.executeUpdate();
                    }

                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
