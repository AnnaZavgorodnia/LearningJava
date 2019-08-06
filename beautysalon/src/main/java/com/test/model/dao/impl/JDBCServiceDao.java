package com.test.model.dao.impl;

import com.test.model.dao.ServiceDao;
import com.test.model.dao.mapper.ServiceMapper;
import com.test.model.entity.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCServiceDao implements ServiceDao {

    private Connection connection;

    JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    //language=SQL
    private String FIND_ALL_SERVICES = "select id s_id, name s_name, price s_price from services ss;";

    @Override
    public void create(Service entity) {

    }

    @Override
    public Optional<Service> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Service> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_SERVICES);

            ServiceMapper serviceMapper = new ServiceMapper();
            List<Service> services = new ArrayList<>();

            while (rs.next()) {
                Service service = serviceMapper
                        .extractFromResultSet(rs);
                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Service entity) {

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
