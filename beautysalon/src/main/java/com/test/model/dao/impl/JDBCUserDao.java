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

    //language=SQL
    private String FIND_USER_BY_USERNAME = "select * from users where username = (?)";

    //language=SQL
    private String FIND_ALL_USERS = "select * from users";

    //language=SQL
    private String INSERT_USER = "insert into users (email, full_name, password, role, username) values(?,?,?,?,?);";

    //language=SQL
    private String INSERT_CLIENT = "insert into clients (id) values(?);";

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByUsername(String username) {

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
        long id = 0;
        try (PreparedStatement ps =
                     connection.prepareStatement(INSERT_USER,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getFullName());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole().name());
            ps.setString(5, entity.getUsername());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement ps =
                     connection.prepareStatement(INSERT_CLIENT,
                             Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (Statement st = connection.createStatement()) {

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