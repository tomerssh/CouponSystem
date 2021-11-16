package coupons.tests;

import coupons.core.dao.CouponDao;
import coupons.core.dao.CouponDaoDb;

public class Test4CouponDao {
	public static void main(String[] args) {
		try {
			CouponDao couponDao = new CouponDaoDb();
			// add test
//			LocalDate startDate = LocalDate.of(2021, 9, 17);
//			LocalDate endDate = LocalDate.of(2021, 10, 17);
//			Coupon coupon = new Coupon(1, Category.FOOD, "ice cream", "coupon for ice cream", startDate, endDate, 1, 20,
//					"ice cream image");
//			int couponId = couponDao.addCoupon(coupon);
//	    System.out.println("coupon added with id " + couponId);
			// update test
//	    couponDao.updateCoupon(coupon);
			// get test
			System.out.println(couponDao.getCoupon(5));
			// delete test
//	    couponDao.deleteCoupon(6);
			// get all coupons test
//	    System.out.println(couponDao.getCoupons());
			// add coupon purchase test
//			couponDao.addCouponPurchase(1, 39);
			// delete coupon purchase test
//	    couponDao.deleteCouponPurchase(1, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
