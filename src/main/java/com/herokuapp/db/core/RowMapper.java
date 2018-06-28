/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herokuapp.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 647-np
 */
public interface RowMapper<T> {
    T maprow(ResultSet rs)throws SQLException;
}
