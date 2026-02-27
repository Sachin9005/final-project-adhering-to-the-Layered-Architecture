package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.CustomerDAO;
import lk.ijse.carrentn.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer cus) throws SQLException {
        return CrudUtil.execute("INSERT INTO Customer (name, email, phone_number, nic_or_passport_number, address ) VALUES (?,?,?,?,?)", cus.getName(), cus.getEmail(), cus.getPhone_number(), cus.getNic_or_passport_number(), cus.getAddress());
    }

    public boolean update(Customer cus) throws SQLException {
        return CrudUtil.execute("UPDATE Customer SET name = ?, email = ? , phone_number = ?, nic_or_passport_number = ? , address = ? WHERE customer_id  = ?", cus.getName(), cus.getEmail(), cus.getPhone_number(), cus.getNic_or_passport_number(), cus.getAddress(), cus.getCustomer_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental WHERE customer_id = ?", id);
        return CrudUtil.execute("DELETE FROM Customer WHERE customer_id = ?", id);
    }

    public List<Customer> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer ORDER BY customer_id DESC");
        ArrayList<Customer> customerList = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("nic_or_passport_number"),
                    rs.getString("address"));

            customerList.add(customer);
        }

        return customerList;
    }

    public Customer search(String id) throws SQLException {
        Customer cus = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE customer_id = ?", id);

        if (result.next()) {
            int cusID = result.getInt("customer_id");
            String cusName = result.getString("name");
            String cusEmail = result.getString("email");
            String cusPhoneNumber = result.getString("phone_number");
            String cusNicOrPassportNumber = result.getString("nic_or_passport_number");
            String cusAddress = result.getString("address");

            cus = new Customer(cusID, cusName, cusEmail, cusPhoneNumber, cusNicOrPassportNumber, cusAddress);
        }
        return cus;
    }

    public String searchId(String name) {
        String id = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT customer_id FROM Customer WHERE name = ?", name);

            if (result.next()) {
                int customerId = result.getInt("customer_id");
                id = String.valueOf(customerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<String> getCustomersNotPaidLast() {
        ArrayList<String> cuatomerNameList = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute("SELECT c.name FROM Rental r JOIN Customer c ON r.customer_id = c.customer_id LEFT JOIN last_Payment lp ON r.rental_id = lp.rental_id WHERE lp.last_payment_id IS NULL");
            if (rs.next()) {
                cuatomerNameList.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cuatomerNameList;
    }


}
