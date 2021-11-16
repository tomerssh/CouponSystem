package coupons.tests;

import coupons.core.facade.CustomerFacade;

public class Test7CustomerFacade {

	public static void main(String[] args) {
		try {
			CustomerFacade customer = new CustomerFacade();
			// login test
			System.out.println(customer.login("aabb@mail", "aabbpass"));
			// purchase coupon test
//			Coupon coupon = new Coupon(1, "chips");
//			customer.purchaseCoupon(coupon);
			// get customer coupons test
//			System.out.println(customer.getCustomerCoupons());
			// get customer coupons with category test
//			System.out.println(customer.getCustomerCoupons(Category.CLOTHING));
			// get customer coupons with maximum price
//			System.out.println(customer.getCustomerCoupons(10));
			// get customer details test
			System.out.println(customer.getCustomerDetails());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
