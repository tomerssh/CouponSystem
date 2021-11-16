package coupons.tests;

import java.time.LocalDate;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.facade.CompanyFacade;

public class Test6CompanyFacade {
	public static void main(String[] args) {
		try {
			CompanyFacade company = new CompanyFacade();
			// login test
			System.out.println(company.login("updatedcompany@mail.com", "updatedcompanypass"));
			// add coupon test
			LocalDate startDate = LocalDate.of(2021, 11, 1);
			LocalDate endDate = LocalDate.of(2021, 8, 1);
			Coupon coupon = new Coupon(1, Category.FOOD, "chips", "coupon for chips", startDate, endDate, 0, 10,
					"chips image");
			company.addCoupon(coupon);
			// update coupon test
//	    company.updateCoupon(coupon);
			// delete coupon test
//	    company.deleteCoupon(39);
			// get company coupons test
//	    System.out.println(company.getCompanyCoupons());
			// get company coupons with category test
//	    System.out.println(company.getCompanyCoupons(Category.CAMPING));
			// get company coupons with max price test
//	    System.out.println(company.getCompanyCoupons(10));
			// get company details test
//			System.out.println(company.getCompanyDetails());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
