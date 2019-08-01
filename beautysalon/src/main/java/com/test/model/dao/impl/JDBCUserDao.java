package com.test.model.dao.impl;

import com.test.model.dao.UserDao;
import com.test.model.dao.mapper.UserMapper;
import com.test.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    private Connection connection;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        //language=SQL
        String FIND_USER_BY_USERNAME = "select * from users where username = (?)";
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_USERNAME)) {

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            UserMapper userMapper = new UserMapper();

            if(rs.next()) {
                return Optional.of(userMapper
                        .extractFromResultSet(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (Statement st = connection.createStatement()) {

            //language=SQL
            String FIND_ALL_USERS = "select * from users";

            ResultSet rs = st.executeQuery(FIND_ALL_USERS);

            UserMapper userMapper = new UserMapper();
            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User entity) {

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
