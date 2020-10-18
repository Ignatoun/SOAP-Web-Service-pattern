package com.example.soap_1.service;

import com.example.soap_1.model.GSC;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class GSCRepository {

    Connection conn;

    @PostConstruct
    public void connect() throws SQLException {
        String dbURL = "jdbc:mysql://localhost/service?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";

        conn = DriverManager.getConnection(dbURL, username, password);
        if (conn != null) {
            System.out.println("Connected");
        }
    }

    @PreDestroy
    public void closeConnection() throws SQLException {
        conn.close();
    }

    public GSC addGSC(GSC gsc) throws SQLException {
        String sql = "INSERT INTO general_singlevalued_configuration (attribute_name, " +
                "attribute_value, attribute_desc) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, gsc.getAttributeName());
        statement.setString(2, gsc.getAttributeValue());
        statement.setString(3, gsc.getAttributeDesc());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new GSC was inserted successfully!");
        }

        return gsc;
    }

    public List<GSC> getAllGSC() throws SQLException {
        String sql = "SELECT * FROM general_singlevalued_configuration";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<GSC> gscList = new ArrayList<>();

        while (result.next()){
            GSC gsc = new GSC();

            String attribute_name = result.getString(1);
            String attribute_value = result.getString(2);
            String attribute_desc = result.getString(3);

            gsc.setAttributeName(attribute_name);
            gsc.setAttributeValue(attribute_value);
            gsc.setAttributeDesc(attribute_desc);

            gscList.add(gsc);
        }

        return gscList;
    }

    public boolean updateGSC(GSC gscUpdated, String attribute_name) throws SQLException {
        String sql = "UPDATE general_singlevalued_configuration SET attribute_value=?, " +
                "attribute_desc=? WHERE general_singlevalued_configuration.attribute_name=" + "'" + attribute_name + "'";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, gscUpdated.getAttributeValue());
        statement.setString(2, gscUpdated.getAttributeDesc());

        boolean ifUpdated = false;
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing GSC was updated successfully!");
            ifUpdated = true;
        }

        return ifUpdated;
    }

    public boolean deleteGSC(String attribute_name) throws SQLException {
        String sql = "DELETE FROM general_singlevalued_configuration WHERE attribute_name=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, attribute_name);

        boolean ifDeleted = false;
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A GSC was deleted successfully!");
            ifDeleted = true;
        }

        return ifDeleted;
    }

    public GSC getGSCByName(String attribute_name) throws SQLException {
        String sql = "SELECT * FROM general_singlevalued_configuration WHERE general_singlevalued_configuration.attribute_name=" + "'" + attribute_name + "'";

        Statement statement = conn.prepareStatement(sql);

        ResultSet resultSet = null;
        resultSet = statement.executeQuery(sql);
        if (resultSet != null) {
            System.out.println("A GSC was found successfully!");
        }

        GSC gscResult = new GSC();
        resultSet.next();
        gscResult.setAttributeName(resultSet.getString(1));
        gscResult.setAttributeValue(resultSet.getString(2));
        gscResult.setAttributeDesc(resultSet.getString(3));

        return gscResult;
    }

}
