package coupons.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponSystemException;

/**
 * Optional class to build and or drop the database for the test
 */
public class Schema {

	private static String schema = "create schema `coupons_db_tomer`;";
	private static String setDefaultSchema = "use coupons_db_tomer;";
	private static String companyTable = "create table `company`(\n" + "`id` int auto_increment,\n"
			+ "`name` varchar(100),\n" + "`email` varchar(100),\n" + "`password` varchar(100),\n" + "primary key(id)\n"
			+ ");";
	private static String customerTable = "create table `customer`(\n" + "`id` int auto_increment,\n"
			+ "`first_name` varchar(100),\n" + "`last_name` varchar(100),\n" + "`email` varchar(100),\n"
			+ "`password` varchar(100),\n" + "primary key(id)\n" + ");";
	private static String couponTable = "create table `coupon`(\n" + "`id` int auto_increment,\n"
			+ "`company_id` int,\n"
			+ "`category` enum('FOOD', 'ELECTRONICS', 'HOME', 'CLOTHING', 'CAMPING', 'VACATION'),\n"
			+ "`title` varchar(100),\n" + "`description` varchar(250),\n" + "`start_date` date,\n"
			+ "`end_date` date,\n" + "`amount` int,\n" + "`price` double,\n" + "`image` varchar(100),\n"
			+ "primary key(id),\n" + "foreign key(company_id) references company(id)\n" + ");";
	private static String customerCouponTable = "create table customer_coupon(\n" + "customer_id int,\n"
			+ "coupon_id int,\n" + "primary key(customer_id, coupon_id),\n"
			+ "foreign key(customer_id) references customer(id),\n" + "foreign key(coupon_id) references coupon(id)\n"
			+ ");";
	private static String dropSchema = "drop schema coupons_db_tomer";

	/**
	 * Builds the schema and rolls back changes if an error occurred
	 * 
	 * @throws CouponSystemException If an exception was caught
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	public static void build() throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			System.out.println("--------------------");
			System.out.println("trying to build schmea...");
			Statement stmt = con.createStatement();
			con.setAutoCommit(false);
			stmt.addBatch(schema);
			stmt.addBatch(setDefaultSchema);
			stmt.addBatch(companyTable);
			stmt.addBatch(customerTable);
			stmt.addBatch(couponTable);
			stmt.addBatch(customerCouponTable);
			stmt.executeBatch();
			stmt.close();
			con.commit();
			System.out.println("schema built successfully");
			System.out.println("--------------------\n");
		} catch (Exception e) {
			con.rollback();
			throw new CouponSystemException("failed to build schema", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * Drops the schema and rolls back changes if an error occurred
	 * 
	 * @throws CouponSystemException If an exception was caught
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	public static void drop() throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		try {
			System.out.println("--------------------");
			System.out.println("trying to drop schmea...");
			Statement stmt = con.createStatement();
			con.setAutoCommit(false);
			stmt.executeUpdate(dropSchema);
			stmt.close();
			con.commit();
			System.out.println("schema dropped successfully");
			System.out.println("--------------------\n");
		} catch (Exception e) {
			con.rollback();
			throw new CouponSystemException("failed to drop schema", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
}
