package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JDBCTemplateAdminRepository implements  AdminRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCTemplateAdminRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Admin save(Admin admin) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("admin").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", admin.getUser_id());
        parameters.put("name", admin.getName());
        parameters.put("password", admin.getPassword());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        admin.setId(key.intValue());
        return admin;
    }

    @Override
    public Optional<Admin> findById(int id) {
        List<Admin> result = jdbcTemplate.query("select * from admin where id = ?", adminRowMapper(), id);
        return result.stream().findAny();    }

    @Override
    public Optional<Admin> findByUserId(String user_id) {
        List<Admin> result = jdbcTemplate.query("select * from admin where user_id = ?", adminRowMapper(), user_id);
        return result.stream().findAny();
    }


    @Override
    public Optional<Admin> findByName(String name) {
        List<Admin> result = jdbcTemplate.query("select * from admin where name = ?", adminRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Admin> findAll() {
        return jdbcTemplate.query("select * from admin", adminRowMapper());
    }

    private RowMapper<Admin> adminRowMapper(){
        return (rs, rowNum) -> {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUser_id(rs.getString("user_id"));
                admin.setPassword(rs.getString("password"));
                admin.setName(rs.getString("name"));
                return admin;
        };
    }
}
