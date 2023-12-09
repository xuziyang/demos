package com.example.druiddemo;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;

public class DruidDemo {
    public static DataSource getDataSource() throws SQLException {
        DruidDataSource dataSource1 = new DruidDataSource();
        // dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("");
        dataSource1.setUsername("");
        dataSource1.setPassword("");
        dataSource1.addFilters("stat");
        dataSource1.setMinIdle(1);
        dataSource1.setMaxIdle(1);
        dataSource1.setInitialSize(1);
        // dataSource1.setCreateScheduler(Executors.newScheduledThreadPool(1));
        dataSource1.setAsyncInit(true);
        dataSource1.setAsyncInit(true);
        dataSource1.setKeepAlive(true);
        return dataSource1;

    }

    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM t_mch_info";


        Connection conn = getDataSource().getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getMetaData().getColumnName(1) + "." + rs.getString(1));
                System.out.println(rs.getMetaData().getColumnName(2) + ":" + rs.getString(2));
            }
        }
        conn.close();
    }
}