package com.chc.tran_jdbc;

import com.chc.tran.constant.MysqlConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author chc
 * @create 2019-01-27 12:15
 **/
public class TranJdbcApplication1 {
    private static final Logger LOG = LoggerFactory.getLogger(TranJdbcApplication1.class);

    public static void main(String[] args) throws SQLException {
        String sql = "SELECT * FROM T_USER FOR UPDATE";
        String plusAmountSQL = "UPDATE T_USER SET amount = ? WHERE username = ?";

        Connection dbConnection = getDBConnection();
        LOG.debug("Begin session2");

        PreparedStatement queryPS = dbConnection.prepareStatement(sql);
        ResultSet rs = queryPS.executeQuery();
        int superManAmount = 0;
        while (rs.next()) {
            String name = rs.getString(2);
            int amount = rs.getInt(3);
            LOG.info("{} has amount:{}", name, amount);
            if (name.equals("SuperMan")) {
                superManAmount = amount;
            }
        }

        PreparedStatement updatePS = dbConnection.prepareStatement(plusAmountSQL);
        updatePS.setInt(1, superManAmount + 100);
        updatePS.setString(2, "SuperMan");
        updatePS.executeUpdate();

        LOG.debug("Done session2!");
        queryPS.close();
        updatePS.close();
        dbConnection.close();
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
