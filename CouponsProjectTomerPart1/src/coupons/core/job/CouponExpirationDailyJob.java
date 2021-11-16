package coupons.core.job;

import coupons.core.dao.CouponDao;
import coupons.core.dao.CouponDaoDb;
import coupons.core.exceptions.CouponSystemException;

/**
 * Runnable to clean expired coupons once
 */
public class CouponExpirationDailyJob implements Runnable {
	private CouponDao couponDao;

	public CouponExpirationDailyJob() {
		this.couponDao = new CouponDaoDb();
	}

	@Override
	public synchronized void run() {
		try {
			couponDao.cleanExpiredCoupons();
		} catch (CouponSystemException e) {
			System.out.println("CouponExpirationDailyJob runtime error" + e.getMessage());
		}
	}
}
