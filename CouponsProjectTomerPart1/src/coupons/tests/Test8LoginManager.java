package coupons.tests;

import coupons.core.exceptions.CouponSystemException;
import coupons.core.facade.ClientFacade;
import coupons.core.facade.login.LoginManager;
import coupons.core.facade.login.LoginManager.ClientType;

public class Test8LoginManager {
	public static void main(String[] args) {
		try {
			ClientFacade clientFacade = LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.ADMINISTRATOR);
			System.out.println(clientFacade);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
}
