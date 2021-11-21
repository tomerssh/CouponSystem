package app.core.repositories;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exceptions.CouponSystemException;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	/**
	 * Get a list of all coupons by company id
	 * 
	 * @param companyId
	 * @return A list of all coupons by company id
	 */
	@Query(value = "from Coupon where company_id=:companyId")
	List<Coupon> findAllByCompanyId(int companyId);

	/**
	 * Get a list of all coupons by company id and category
	 * 
	 * @param companyId
	 * @param category
	 * @return A list of all coupons by company id and category
	 */
	@Query(value = "from Coupon where company_id=:companyId AND category=:category")
	List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

	/**
	 * Get a list of all coupons by company id and max price
	 * 
	 * @param companyId
	 * @param maxPrice
	 * @return A list of all coupons by company id and max price
	 */
	@Query(value = "from Coupon where company_id=:companyId AND price=:maxPrice")
	List<Coupon> findAllByCompanyIdAndMaxPrice(int companyId, double maxPrice);

	/**
	 * Removes all expired coupons
	 * 
	 * @param date To check for expired coupons
	 * @return The count of deleted coupons
	 */
	@Transactional
	@Modifying
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Query(value = "delete from Coupon where end_date<=:date")
	int deleteAllExpired(LocalDate date);

	/**
	 * Add a coupon to customer.
	 * 
	 * @param customerId The id of the customer to add the coupon to.
	 * @param couponId   The id of the coupon that was purchased.
	 * @throws CouponSystemException
	 */
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "insert into customer_coupon values(:customerId, :couponId)")
	void addCouponPurchase(int customerId, int couponId) throws CouponSystemException;

	/**
	 * Remove a coupon from customer.
	 * 
	 * @param customerId The id of the customer to remove the coupon from.
	 * @param couponId   The id of the coupon to removed.
	 * @throws CouponSystemException
	 * @throws SQLException
	 */
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from customer_coupon where customer_id=:customerId and coupon_id=:couponId")
	void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException;

	/**
	 * Check if a coupon was purchased
	 * 
	 * @param couponId   The coupon id
	 * @param customerId The customer id
	 * @return true if the coupon was purchased
	 * @throws CouponSystemException
	 */
	@Query(nativeQuery = true, value = "select from customer_coupon where customer_id=:customerId and coupon_id=:couponId")
	boolean wasPurchased(int customerId, int couponId) throws CouponSystemException;

	/**
	 * Takes a coupon id and returns the amount value
	 * 
	 * @param couponId The coupon id
	 * @return The amount value
	 * @throws CouponSystemException
	 */
	@Query(nativeQuery = true, value = "select amount from Coupon where id=:couponId")
	int getAmount(int couponId) throws CouponSystemException;

	/**
	 * Get a coupon id and delete the coupon purchase history
	 * 
	 * @param con      A connection to the database
	 * @param couponId The coupon id
	 * @throws CouponSystemException If a database access error occurred
	 */
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from customer_coupon where coupon_id=:couponId")
	void deleteCouponHistory(int couponId) throws CouponSystemException;

}
