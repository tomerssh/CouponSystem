package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

/**
 * Manages customer interaction with the app
 * 
 * @see ClientService
 */
@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {
	// the id the the logged in customer
	private int customerId;
	// reference to the current customer
	private Customer customer;

	@Autowired
	public CustomerService(CustomerRepository customerRepo, CouponRepository couponRepo) {
		super.customerRepo = customerRepo;
		super.couponRepo = couponRepo;
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		this.customer = customerRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new CouponServiceException("invalid username or password"));
		this.customerId = customer.getId();
		return true;
	}

	public void addCouponPurchase(Coupon coupon) throws CouponSystemException {
		coupon = couponRepo.getById(coupon.getId());
		if (coupon.getAmount() > 0) {
			coupon.setAmount(coupon.getAmount() - 1);
			this.customer.getCoupons().add(coupon);
			couponRepo.addCouponPurchase(this.customerId, coupon.getId());
		} else {
			throw new CouponServiceException("no coupons left");
		}
	}

	public void deleteCouponPurchase(int couponId) throws CouponSystemException {
		couponRepo.deleteCouponPurchase(this.customerId, couponId);
	}

	public boolean wasCouponPurchased(int couponId) throws CouponSystemException {
		List<Integer> couponIdList = couponRepo.wasPurchased(this.customerId, couponId);
		if (couponIdList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Return a list of all coupons of the company with given id
	 * 
	 * @param companyId The company id
	 * @return A list of all coupons
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCustomerCouponsById(int customerId) throws CouponSystemException {
		List<Integer> ids = couponRepo.findCouponIdsByCustomerId(this.customerId);
		return couponRepo.findAllById(ids);
	}

	/**
	 * Return a list of all coupons of the customer with given category
	 * 
	 * @param companyId The customer id
	 * @param category  The coupon category
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCustomerCouponsByIdAndCategory(int customerId, Category category)
			throws CouponSystemException {
		return couponRepo.findAllByCompanyIdAndCategory(this.customerId, category);
	}

	/**
	 * Return a list of all coupons of the customer with given max price
	 * 
	 * @param companyId The customer id
	 * @param maxPrice  The coupons max price
	 * @return A list of all coupons with given max price
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCustomerCouponsByIdAndMaxPrice(int customerId, double maxPrice)
			throws CouponSystemException {
		return couponRepo.findAllByCompanyIdAndMaxPrice(this.customerId, maxPrice);
	}

	/**
	 * Get the logged in customer's details
	 * 
	 * @return The logged in customer
	 * @throws CouponSystemException
	 */
	public Customer getCustomerDetails() throws CouponSystemException {
		return this.customer;
	}

}
