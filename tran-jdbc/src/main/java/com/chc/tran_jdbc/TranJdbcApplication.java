package com.chc.tran_jdbc;

import com.chc.tran.constant.MysqlConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author chc
 * @create 2019-01-27 12:15
 **/
public class TranJdbcApplication {
    private static final Logger LOG = LoggerFactory.getLogger(TranJdbcApplication.class);

    public static void main(String[] args) throws SQLException {


        String plusAmountSQL = "UPDATE T_USER SET amount = amount + 100 WHERE username = ?";
        String minusAmountSQL = "UPDATE T_USER SET amount = amount - 100 WHERE username = ?";

        Connection dbConnection = getDBConnection();
        LOG.debug("Begin");
        // 关闭事务自动提交
        dbConnection.setAutoCommit(false);

        PreparedStatement plusAmountPS = dbConnection.prepareStatement(plusAmountSQL);
        plusAmountPS.setString(1, "SuperMan");
        plusAmountPS.executeUpdate();
        // 抛异常
        simulateError();

        PreparedStatement minusAmountPS = dbConnection.prepareStatement(minusAmountSQL);
        minusAmountPS.setString(1, "BatMan");
        minusAmountPS.executeUpdate();
        // 提交事务
        dbConnection.commit();
        LOG.debug("Done!");

        // 关闭连接
        plusAmountPS.close();
        minusAmountPS.close();
        dbConnection.close();
    }

    private static void simulateError() throws SQLException {
        throw new SQLException("Simulate some error!");
    }
    private static Connection getDBConnection() throws SQLException {

        String DB_CONNECTION = MysqlConstant.getConnectionString("dist_tran_course");
        try {
            Class.forName(MysqlConstant.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
        return DriverManager.getConnection(DB_CONNECTION, MysqlConstant.DB_USER, MysqlConstant.DB_PASSWORD);
    }

}
