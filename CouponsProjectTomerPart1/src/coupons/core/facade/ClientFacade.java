package coupons.core.facade;

import coupons.core.dao.CompanyDao;
import coupons.core.dao.CouponDao;
import coupons.core.dao.CustomerDao;
import coupons.core.exceptions.CouponSystemException;

/**
 * Manages client interaction with the app.
 */
public abstract class ClientFacade {
	protected CompanyDao companyDao;
	protected CustomerDao customerDao;
	protected CouponDao couponDao;

	/**
	 * Login the client to the system.
	 * 
	 * @param email    The client's email
	 * @param password The client's password
	 * @return true if the client entered correct credentials
	 * @throws CouponSystemException If the client does not exist
	 */
	public abstract boolean login(String email, String password) throws CouponSystemException;
}
