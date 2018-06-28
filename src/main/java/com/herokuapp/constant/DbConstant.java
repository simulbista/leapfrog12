/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herokuapp.constant;

/**
 *
 * @author 647-np
 */
public class DbConstant {
//    for running internally msql server(just to test)
//    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//    public static final String DB_URL = "jdbc:mysql://localhost/leapfrog_project";
//    public static final String DB_USER = "root";
//    public static final String DB_Password = "";

//    for running postgres in webpage externally(this is what we want)
    public static final String DB_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql:"
            + "//ec2-54-163-229-212.compute-1."
            + "amazonaws.com/d6cv9vltji77dt?sslmodel  = require";
    public static final String DB_USER = "fpyxgobvizinrb";
    public static final String DB_Password
            = "a9389d3d32cfda88f2675c73d237ce97cf6c4c79306c47a434322f37cf2d935d";
}
