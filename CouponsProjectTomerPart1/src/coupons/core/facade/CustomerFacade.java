package coupons.core.facade;

import java.sql.SQLException;
import java.util.List;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.beans.Customer;
import coupons.core.dao.CouponDaoDb;
import coupons.core.dao.CustomerDaoDb;
import coupons.core.exceptions.CouponDaoException;
import coupons.core.exceptions.CouponFacadeException;
import coupons.core.exceptions.CouponSystemException;

/**
 * Manages customer interaction with the app
 * 
 * @see ClientFacade
 */
public class CustomerFacade extends ClientFacade {
	// the id the the logged in customer
	private int customerId;

	public CustomerFacade() {
		super.customerDao = new CustomerDaoDb();
		super.couponDao = new CouponDaoDb();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (!customerDao.isCustomerExists(email, password)) {
			throw new CouponFacadeException("invalid username or password");
		}
		customerId = customerDao.getCustomerId(email, password);
		return true;
	}

	/**
	 * Purchase a coupon for the logged in customer
	 * 
	 * @param coupon The coupon to purchase
	 * @throws CouponSystemException If an exception was caught
	 * @throws SQLException
	 */
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		int couponId = couponDao.getCouponId(coupon);
		if (couponDao.wasPurchased(couponId, customerId)) {
			throw new CouponFacadeException("coupon was already purchased");
		}
		if (couponDao.getAmount(couponId) == 0) {
			throw new CouponFacadeException("no coupons avaliable for purchase");
		}
		if (couponDao.isExpired(couponId)) {
			throw new CouponFacadeException("coupon expired");
		}

		try {
			couponDao.addCouponPurchase(customerId, couponId);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
//		System.out.println("DEBUG purchased coupon with id " + couponId + " for customer with id " + customerId);
	}

	/**
	 * Return a list of all the coupons this customer purchased
	 * 
	 * @return A list of customer coupons
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCustomerCoupons() throws CouponSystemException {
		return couponDao.getCustomerCoupons(customerId);
	}

	/**
	 * Return a list of all the coupons this customer purchased with this category
	 * 
	 * @param category The coupon category
	 * @return A list of customer coupons
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCustomerCoupons(Category category) throws CouponSystemException {
		return couponDao.getCustomerCoupons(customerId, category);
	}

	/**
	 * Return a list of all the coupons this customer purchased with this maximum
	 * price
	 * 
	 * @param maxPrice The maximum price
	 * @return A list of customer coupons
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemException {
		return couponDao.getCustomerCoupons(customerId, maxPrice);
	}

	/**
	 * Get the logged in customer's details
	 * 
	 * @return The logged in customer
	 * @throws CouponSystemException
	 */
	public Customer getCustomerDetails() throws CouponSystemException {
		Customer customer = customerDao.getCustomer(customerId);
		customer.setCoupons(couponDao.getCustomerCoupons(customerId));
		return customer;
	}

}
