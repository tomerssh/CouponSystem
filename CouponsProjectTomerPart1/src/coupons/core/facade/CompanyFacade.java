package coupons.core.facade;

import java.sql.SQLException;
import java.util.List;

import coupons.core.beans.Company;
import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.dao.CompanyDaoDb;
import coupons.core.dao.CouponDaoDb;
import coupons.core.exceptions.CouponDaoException;
import coupons.core.exceptions.CouponFacadeException;
import coupons.core.exceptions.CouponSystemException;

/**
 * Manages company interaction with the app
 * 
 * @see ClientFacade
 */
public class CompanyFacade extends ClientFacade {
	// the id the the logged in company
	private int companyId;

	public CompanyFacade() {
		super.companyDao = new CompanyDaoDb();
		super.couponDao = new CouponDaoDb();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		if (!companyDao.isCompanyExists(email, password)) {
			throw new CouponFacadeException("invalid username or password");
		}
		companyId = companyDao.getCompanyId(email, password);
		return true;
	}

	/**
	 * Add a coupon to this company
	 * 
	 * @param coupon The coupon to add
	 * @return
	 * @throws CouponSystemException If an exception was caught while adding coupon
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	public int addCoupon(Coupon coupon) throws CouponSystemException {
		try {
			if (companyDao.findDuplicateCoupon(this.companyId, coupon)) {
				throw new CouponFacadeException("duplicate coupon");
			}
			return couponDao.addCoupon(this.companyId, coupon);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Update a coupon of this company
	 * 
	 * @param couponId The id of the coupon to update
	 * @throws CouponSystemException If the coupon does not exist or a database
	 *                               access error occurred
	 * 
	 */
	public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
		try {
			couponDao.updateCoupon(couponId, coupon);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Delete a coupon with given id
	 * 
	 * @param couponId The coupon id
	 * @throws CouponSystemException If a database access error occurred
	 */
	public void deleteCoupon(int couponId) throws CouponSystemException {
		try {
			couponDao.deleteCoupon(couponId);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Get a list of all coupons of this company
	 * 
	 * @return A list of all coupons of this company
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons() throws CouponSystemException {
		return companyDao.getCompanyCoupons(companyId);
	}

	/**
	 * Get a list of all coupons of this company of given category
	 * 
	 * @param category The given category
	 * @return A list of all company coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
		return companyDao.getCompanyCoupons(companyId, category);
	}

	/**
	 * Get a list of all coupons of this company with given maximum price
	 * 
	 * @param maxPrice The maximum price
	 * @return A list of all company coupons with given maximum price
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
		return companyDao.getCompanyCoupons(companyId, maxPrice);
	}

	/**
	 * Get company details
	 * 
	 * @return A company object containing the company details
	 * @throws CouponSystemException If a database access error occurred
	 */
	public Company getCompanyDetails() throws CouponSystemException {
		return companyDao.getCompany(companyId);
	}
}
