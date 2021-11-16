package coupons.core.dao;

import java.sql.SQLException;
import java.util.List;

import coupons.core.beans.Customer;
import coupons.core.exceptions.CouponSystemException;

public interface CustomerDao {
	/**
	 * Check if the customer exists in the data storage.
	 * 
	 * @param email    The customer's email.
	 * @param password The customer's password.
	 * @return true if the customer exists in the data storage.
	 * @throws CouponSystemException
	 */
	boolean isCustomerExists(String email, String password) throws CouponSystemException;

	/**
	 * Add a customer to the data storage.
	 * 
	 * @param customer The customer to be added.
	 * @return The customer's id.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	int addCustomer(Customer customer) throws CouponSystemException, SQLException;

	/**
	 * Update a given customer's details.
	 * 
	 * @param customer The new customer.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void updateCustomer(Customer customer) throws CouponSystemException, SQLException;

	/**
	 * Remove a customer from the data storage using id.
	 * 
	 * @param customerId The customer's id.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void deleteCustomer(int customerId) throws CouponSystemException, SQLException;

	/**
	 * Return all customers in the data storage.
	 * 
	 * @return A list of all customers.
	 * @throws CouponSystemException
	 */
	List<Customer> getCustomers() throws CouponSystemException;

	/**
	 * Get customer email and password and return a customer id
	 * 
	 * @param email    The customer email
	 * @param password The customer password
	 * @return The customer id
	 * @throws CouponSystemException If an argument is invalid or a database access
	 *                               error occurred
	 */
	int getCustomerId(String email, String password) throws CouponSystemException;

	/**
	 * Get customer id and return a customer.
	 * 
	 * @param customerId The customer id
	 * @return A customer
	 * @throws CouponSystemException
	 */
	Customer getCustomer(int customerId) throws CouponSystemException;
}
