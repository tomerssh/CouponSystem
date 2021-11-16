package coupons.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coupons.core.beans.Customer;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponDaoException;
import coupons.core.exceptions.CouponSystemException;

/**
 * Database implementation of the CustomerDao interface.
 */
public class CustomerDaoDb implements CustomerDao {
	/**
	 * {@inheritDoc}
	 * 
	 * @throws CouponSystemException If an error occured while trying to find
	 *                               customer.
	 */
	@Override
	public boolean isCustomerExists(String email, String password) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer where email=? and password=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			// return false if the result set is empty, true if it has entries.
			return rs.next();
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to find customer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws CouponSystemException If an exception was caught while adding
	 *                               customer.
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	@Override
	public int addCustomer(Customer customer) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		isValidCustomerEmail(con, customer);
		String sql = "insert into customer values(0, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			con.setAutoCommit(false);
			// add the customer
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.executeUpdate();
			con.commit();
			// get the auto generated id for this company
			ResultSet rsKey = pstmt.getGeneratedKeys();
			rsKey.next();
			int id = rsKey.getInt(1);
			return id;
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not addCustomer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	private void isValidCustomerEmail(Connection con, Customer customer) throws CouponSystemException {
		String sql = "select * from customer where email=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getEmail());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				throw new CouponDaoException("customer email already exists");
			}
		} catch (SQLException e) {
			throw new CouponDaoException("an error occured while validating customer email", e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException If a database access error occurred while rolling back
	 *                      changes
	 */
	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "update customer set first_name=?, last_name=?, email=?, password=? where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getId());
			pstmt.executeUpdate();
			con.commit();
//	    System.out.println("DEBUG updated customer with id " + customerId);
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not updateCustomer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException If a database access error occurred while rolling back
	 *                      changes
	 */
	@Override
	public void deleteCustomer(int customerId) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "delete from customer where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			deleteCustomerHistory(con, customerId);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
			con.commit();
//	    System.out.println("DEBUG deleted customer with id " + customerId);
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not deleteCustomer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	private void deleteCustomerHistory(Connection con, int customerId) throws SQLException {
		String sql = "delete from customer_coupon where customer_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
//	    System.out.println("DEBUG deleted customer history for customer with id " + customerId);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Customer> getCustomers() throws CouponSystemException {
		List<Customer> list = new ArrayList<Customer>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				list.add(customer);
			}
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCustomers", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCustomerId(String email, String password) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer where email=? and password=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int customerId = rs.getInt(1);
				return customerId;
			} else {
				throw new CouponDaoException("invalid argument");
			}
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCustomerId", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer getCustomer(int customerId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				throw new CouponDaoException("invalid arguments");
			}
			Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5));
			return customer;
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCustomer", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}

}
