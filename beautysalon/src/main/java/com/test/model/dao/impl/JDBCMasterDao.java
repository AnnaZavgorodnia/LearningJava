package com.test.model.dao.impl;

import com.test.model.dao.MasterDao;
import com.test.model.dao.mapper.MasterMapper;
import com.test.model.dao.mapper.UserMapper;
import com.test.model.entity.Master;
import com.test.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCMasterDao implements MasterDao {

    //language=SQL
    String FIND_ALL_MASTERS = "select m.id m_id, email m_email, full_name m_full_name, password m_password, role m_role, instagram m_instagram, position m_position, username m_username from users right join masters m on users.id = m.id";


    private Connection connection;

    JDBCMasterDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Master entity) {

    }

    @Override
    public Optional<Master> findById(int id) {
        return Optional.empty();
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
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
