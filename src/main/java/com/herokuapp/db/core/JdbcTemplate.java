/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herokuapp.db.core;

import com.herokuapp.constant.DbConstant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 647-np
 */
public class JdbcTemplate<T> {

    private void setParams(PreparedStatement stmt, Object[] params) throws SQLException {
        int counter = 1;
        for (Object param : params) {
            stmt.setObject(counter, param);
            counter++;
        }
    }
//For select all query

    public List<T> query(String sql, RowMapper<T> mapper) throws ClassNotFoundException, SQLException {
        List<T> rows = new ArrayList<>();

        Class.forName(DbConstant.DB_DRIVER);

        Connection conn = DriverManager.getConnection(DbConstant.DB_URL,
                DbConstant.DB_USER, DbConstant.DB_Password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            rows.add(mapper.maprow(rs));
        }
        conn.close();
        return rows;
    }
//  For select(get by id) query  

    public T queryForSingleObject(String sql, Object[] params, RowMapper<T> mapper) throws ClassNotFoundException, SQLException {
        T row = null;
        Class.forName(DbConstant.DB_DRIVER);

        Connection conn = DriverManager.getConnection(DbConstant.DB_URL,
                 DbConstant.DB_USER, DbConstant.DB_Password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParams(stmt, params);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            row = mapper.maprow(rs);
        }
        conn.close();
        return row;
    }
//    Object[] a and Object... a is the same thing(i.e. array of objects)

    public int update(String sql, Object... params) throws ClassNotFoundException, SQLException {

        Class.forName(DbConstant.DB_DRIVER);

        Connection conn = DriverManager.getConnection(DbConstant.DB_URL,
                 DbConstant.DB_USER, DbConstant.DB_Password);

        PreparedStatement stmt = conn.prepareStatement(sql);
        setParams(stmt, params);
        int result = stmt.executeUpdate();
        conn.close();
        return result;
    }
}
