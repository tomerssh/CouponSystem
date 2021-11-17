package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entities.Company;
import app.core.exceptions.CouponSystemException;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	/**
	 * Check if the company exists in the data storage.
	 * 
	 * @param email    The company's email.
	 * @param password The company's password.
	 * @return true if the company exists in the data storage.
	 * @throws CouponSystemException
	 */
	boolean existsByEmailAndPassword(String email, String password) throws CouponSystemException;

	/**
	 * Try to find a company with this name and password
	 * 
	 * @param email    The company email
	 * @param password The company password
	 * @return optional of company
	 * @throws CouponSystemException
	 */
	Optional<Company> findByEmailAndPassword(String email, String password) throws CouponSystemException;

	/**
	 * Try to find a company with this name and email
	 * 
	 * @param email    The company email
	 * @param password The company password
	 * @return true if company exists
	 * @throws CouponSystemException
	 */
	boolean existsByNameOrEmail(String email, String password) throws CouponSystemException;

	/**
	 * Try to find a company with this name and password
	 * 
	 * @param name     The company name
	 * @param password The company password
	 * @return optional of company
	 * @throws CouponSystemException
	 */
	Optional<Company> findByNameAndPassword(String name, String password) throws CouponSystemException;

//	/**
//	 * Find if the company has another coupon with this name
//	 * 
//	 * @param companyId The company id
//	 * @param coupon    The coupon to check
//	 * @return true if a duplicate was found
//	 * @throws CouponSystemException If a database access error occurred
//	 */
//	boolean existsCouponByDuplicateCoupon(int companyId, Coupon coupon) throws CouponSystemException;

//	/**
//	 * Return a list of all coupons of the company with given id
//	 * 
//	 * @param companyId The company id
//	 * @return A list of all coupons
//	 * @throws CouponSystemException If a database access error occurred
//	 */
//	List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException;

//	/**
//	 * Return a list of all coupons of the company with given category
//	 * 
//	 * @param companyId The company id
//	 * @param category  The coupon category
//	 * @return A list of all coupons of given category
//	 * @throws CouponSystemException If a database access error occurred
//	 */
//	public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException;

//	/**
//	 * Return a list of all coupons of the company with given maximum price
//	 * 
//	 * @param companyId The company id
//	 * @param maxPrice  The maximum price
//	 * @return A list of all coupons of given category
//	 * @throws CouponSystemException If a database access error occurred
//	 */
//	List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException;
}
