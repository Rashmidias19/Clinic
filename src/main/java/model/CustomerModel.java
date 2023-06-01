//package model;
//
//import db.DBConnection;
//import dto.Customer;
//import dto.User;
//import javafx.scene.control.Alert;
//import dao.impl.util.CrudUtil;
//
//import java.sql.*;
//import java.dao.impl.util.ArrayList;
//import java.dao.impl.util.List;
//import java.dao.impl.util.Properties;
//
//public class CustomerModel {
//
//    public static List<String> loadCustomerID() throws SQLException, ClassNotFoundException {
//        Connection con = DBConnection.getInstance().getConnection();
//        ResultSet resultSet = con.createStatement().executeQuery("SELECT CustomerID FROM Customer");
//
//        List<String> data = new ArrayList<>();
//
//        while (resultSet.next()) {
//            data.add(resultSet.getString(1));
//        }
//        return data;
//    }
//    public static Customer searchById(String ID) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT * FROM Customer WHERE CustomerID =?");
//        pstm.setString(1, ID);
//        ResultSet resultSet = pstm.executeQuery();
//
//        if(resultSet.next()) {
//            return new Customer(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getInt(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8),
//                    resultSet.getString(9),
//                    resultSet.getString(10)
//
//            );
//        }
//        return null;
//    }
//
//
//    public static List<Customer> getAll() throws SQLException, ClassNotFoundException {
//        List<Customer> data = new ArrayList<>();
//
//        String sql = "SELECT * FROM Customer";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        while (resultSet.next()) {
//            data.add(new Customer(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getInt(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8),
//                    resultSet.getString(9),
//                    resultSet.getString(10)
//            ));
//        }
//        return data;
//    }
//
//    public static String getNextCustomerId() throws SQLException, ClassNotFoundException {
//        Connection con = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT CustomerID FROM customer ORDER BY CustomerID DESC LIMIT 1";
//
//        ResultSet resultSet = con.createStatement().executeQuery(sql);
//
//        if (resultSet.next()) {
//            return splitCustomerId(resultSet.getString(1));
//        }
//        return splitCustomerId(null);
//    }
//
//    private static String splitCustomerId(String currentId) {
//        if(currentId != null) {
//            String[] strings = currentId.split("C000");
//            int id = Integer.parseInt(strings[1]);
//            id++;
//            return "C000" + id;
//        }
//        return "C0001";
//    }
//
//    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("DELETE FROM Customer WHERE CustomerID = ?",id);
//    }
//
//    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO Customer(CustomerID,CustTitle,CustName,NIC,DOB,age,Gender,contact,email, address)" +
//                   "VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
//        return CrudUtil.execute(
//                sql,
//                customer.getCustomerID(),
//                customer.getCustTitle(),
//                customer.getCustName(),
//                customer.getNIC(),
//                customer.getDOB(),
//                customer.getAge(),
//                customer.getGender(),
//                customer.getContact(),
//                customer.getEmail(),
//                customer.getAddress());
//    }
//
//    public static void update(Customer customer) throws SQLException, ClassNotFoundException {
////
//        String sql = "UPDATE Customer SET CustTitle = ?,  CustName = ?, NIC = ?, DOB = ?, age = ?, Gender = ?, contact = ?, email = ?, address = ? WHERE CustomerID = ?" ;
//
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1, customer.getCustTitle());
//        pstm.setString(2, customer.getCustName());
//        pstm.setString(3, customer.getNIC());
//        pstm.setString(4,customer.getDOB());
//        pstm.setInt(5,customer.getAge());
//        pstm.setString(6,customer.getGender());
//        pstm.setString(7,customer.getContact());
//        pstm.setString(8,customer.getEmail());
//        pstm.setString(9,customer.getAddress());
//        pstm.setString(10,customer.getCustomerID());
//
//        boolean isUpdated = pstm.executeUpdate() > 0;
//        if (isUpdated) {
//            new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
//        }
//    }
//}