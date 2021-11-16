package coupons.core.dao;

import java.sql.SQLException;
import java.util.List;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.exceptions.CouponSystemException;

public interface CouponDao {
	/**
	 * Add a coupon to the data storage for the company with this id.
	 * 
	 * @param companyId the company id
	 * @param coupon    The customer to be added.
	 * @return The coupon's id.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	int addCoupon(int companyId, Coupon coupon) throws CouponSystemException, SQLException;

	/**
	 * Update a given coupon's details.
	 * 
	 * @param couponId The coupon id.
	 * @param coupon   The new coupon details.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException, SQLException;

	/**
	 * Remove a coupon from the data storage using id.
	 * 
	 * @param couponId The coupon's id.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void deleteCoupon(int couponId) throws CouponSystemException, SQLException;

	/**
	 * Return all coupons in the data storage.
	 * 
	 * @return A list of all coupons.
	 * @throws CouponSystemException
	 */
	List<Coupon> getCoupons() throws CouponSystemException;

	/**
	 * Return a list of coupons this customer purchased
	 * 
	 * @param customerId The customer id
	 * @return A list of coupons
	 * @throws CouponSystemException
	 */
	List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException;

	/**
	 * Return a list of coupons this customer purchased with this coupon category
	 * 
	 * @param customerId The customer id
	 * @param category   The coupon category
	 * @return A list of coupons
	 * @throws CouponSystemException
	 */

	List<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponSystemException;

	/**
	 * Return a list of coupons this customer purchased with this maximum price
	 * 
	 * @param customerId The customer id
	 * @param maxPrice   The maximum price
	 * @return A list of coupons
	 * @throws CouponSystemException
	 */
	List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponSystemException;

	/**
	 * Get a coupon and return the id
	 * 
	 * @param coupon The coupon to get
	 * @return A coupon id
	 * @throws CouponSystemException If an argument is invalid or a database access
	 *                               error occurred
	 */
	int getCouponId(Coupon coupon) throws CouponSystemException;

	/**
	 * Remove all expired coupons and purchase history
	 * 
	 * @throws CouponSystemException If a database access error occurred
	 */
	void cleanExpiredCoupons() throws CouponSystemException;

	/**
	 * Get coupon id and return a coupon.
	 * 
	 * @param couponId The coupon id
	 * @return A coupon
	 * @throws CouponSystemException
	 */
	Coupon getCoupon(int couponId) throws CouponSystemException;

	/**
	 * Add a coupon to customer.
	 * 
	 * @param customerId The id of the customer to add the coupon to.
	 * @param couponId   The id of the coupon that was purchased.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void addCouponPurchase(int customerId, int couponId) throws CouponSystemException, SQLException;

	/**
	 * Remove a coupon from customer.
	 * 
	 * @param customerId The id of the customer to remove the coupon from.
	 * @param couponId   The id of the coupon to removed.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException, SQLException;

	/**
	 * Check if a coupon was purchased
	 * 
	 * @param couponId   The coupon id
	 * @param customerId The customer id
	 * @return true if the coupon was purchased
	 * @throws CouponSystemException
	 */
	boolean wasPurchased(int couponId, int customerId) throws CouponSystemException;

	/**
	 * Takes a coupon id and returns the amount value
	 * 
	 * @param couponId The coupon id
	 * @return The amount value
	 * @throws CouponSystemException
	 */
	int getAmount(int couponId) throws CouponSystemException;

	/**
	 * Checks if this coupon expired
	 * 
	 * @param couponId The coupon id
	 * @return true if the coupon expired
	 * @throws CouponSystemException
	 */
	boolean isExpired(int couponId) throws CouponSystemException;
}
