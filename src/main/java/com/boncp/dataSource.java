package com.boncp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCPDataSource;
/**
 * 说明：利用BoneCP提供的连接池而不是直接使用JDBC来管理连接的
 */
public class dataSource {

    public static void main(String[] args) {

        //创建一个DataSource对象
        BoneCPDataSource ds = new BoneCPDataSource();
        Connection connection = null;

        try {
            //加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            //设置JDBC URL
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT");
            //设置用户名
            ds.setUsername("cyy");
            //设置密码
            ds.setPassword("cyy7215217758");
            //设置其它可选属性
            //ds.setXXXX(...);

            connection = ds.getConnection();

            //这里操作数据库
            //...

            if (connection != null){
                System.out.println("Connection successful!");
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
                while(rs.next()){
                    System.out.println(rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME") + " " + rs.getInt(3) + " " + rs.getString("SEX") + " " + rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库连接
            if (connection != null) {
                try {
                    connection.close();
                    ds.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
