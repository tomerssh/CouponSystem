package coupons.core.dao;

import java.sql.SQLException;
import java.util.List;

import coupons.core.beans.Company;
import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.exceptions.CouponSystemException;

public interface CompanyDao {
	/**
	 * Check if the company exists in the data storage.
	 * 
	 * @param email    The company's email.
	 * @param password The company's password.
	 * @return true if the company exists in the data storage.
	 * @throws CouponSystemException
	 */
	boolean isCompanyExists(String email, String password) throws CouponSystemException;

	/**
	 * Add a company to the data storage.
	 * 
	 * @param company The company to be added.
	 * @return The company's id.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	int addCompany(Company company) throws CouponSystemException, SQLException;

	/**
	 * Update a given company's details.
	 * 
	 * @param company The new company.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void updateCompany(Company company) throws CouponSystemException, SQLException;

	/**
	 * Remove a company from the data storage using id.
	 * 
	 * @param companyId The company's id.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void deleteCompany(int companyId) throws CouponSystemException, SQLException;

	/**
	 * Return all companies in the data storage.
	 * 
	 * @return A list of all companies.
	 * @throws CouponSystemException
	 */
	List<Company> getCompanies() throws CouponSystemException;

	/**
	 * Get company id and return a company.
	 * 
	 * @param companyId The company id
	 * @return A company
	 * @throws CouponSystemException
	 */
	Company getCompany(int companyId) throws CouponSystemException;

	/**
	 * Get company email and password and return a company id
	 * 
	 * @param email    The company email
	 * @param password The company password
	 * @return The company id
	 * @throws CouponSystemException If an argument is invalid or a database access
	 *                               error occurred
	 */
	int getCompanyId(String email, String password) throws CouponSystemException;

	/**
	 * Find if the company has another coupon with this name
	 * 
	 * @param companyId The company id
	 * @param coupon    The coupon to check
	 * @return true if a duplicate was found
	 * @throws CouponSystemException If a database access error occurred
	 */
	public boolean findDuplicateCoupon(int companyId, Coupon coupon) throws CouponSystemException;

	/**
	 * Return a list of all coupons of the company with given id
	 * 
	 * @param companyId The company id
	 * @return A list of all coupons
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException;

	/**
	 * Return a list of all coupons of the company with given category
	 * 
	 * @param companyId The company id
	 * @param category  The coupon category
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException;

	/**
	 * Return a list of all coupons of the company with given maximum price
	 * 
	 * @param companyId The company id
	 * @param maxPrice  The maximum price
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException;
}
