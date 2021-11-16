package coupons.tests;

import java.time.LocalDate;
import java.util.Scanner;

import coupons.core.beans.Company;
import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.beans.Customer;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponSystemException;
import coupons.core.facade.AdminFacade;
import coupons.core.facade.ClientFacade;
import coupons.core.facade.CompanyFacade;
import coupons.core.facade.CustomerFacade;
import coupons.core.facade.login.LoginManager;
import coupons.core.facade.login.LoginManager.ClientType;
import coupons.core.job.DailyJob;

public class Test {
	public static void testAll() {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("START TEST\n");
			// drop existing database
			Schema.drop();
			// build new database
			Schema.build();
			jobTest(in);
			// NOTE: commenting one of the facade test methods might break the test because
			// it's hardcoded to use results from previous tests
			adminTest(in);
			companyTest(in);
			customerTest(in);
			poolTest(in);
			System.out.println("END TEST");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void jobTest(Scanner in) {
		// start daily job test
		System.out.print("test daily job? (y/n)");
		if (in.nextLine().equalsIgnoreCase("Y")) {
			System.out.println();
			System.out.println("--------------------");
			System.out.println("starting daily job...");
			DailyJob.startJob();
			System.out.println("daily job started");
			System.out.println("--------------------\n");
		}
	}

	private static void adminTest(Scanner in) throws CouponSystemException {
		// admin tests
		int adminCompanyId = 0;
		int adminCompanyId2 = 0;
		int adminCustomerId = 0;
		int adminCustomerId2 = 0;
		System.out.print("==========ADMIN TESTS==========\n");
		// login manager test
		System.out.println("--------------------");
		System.out.println("login manager test");
		ClientFacade adminClientFacade = LoginManager.getInstance().login("admin@admin.com", "admin",
				ClientType.ADMINISTRATOR);
		if (adminClientFacade instanceof AdminFacade) {
			AdminFacade adminFacade = (AdminFacade) adminClientFacade;
			System.out.println("logged in");
			System.out.println("--------------------\n");
			System.out.print("test add company and customer? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// add company and customer test
				System.out.println("--------------------");
				System.out.println("add company and customer test");
				adminCompanyId = adminFacade.addCompany(new Company("company", "company@mail.com", "companypass"));
				adminCustomerId = adminFacade
						.addCustomer(new Customer("coupon", "buyer", "couponbuyer@mail.com", "couponbuyerpass"));
				adminCompanyId2 = adminFacade.addCompany(new Company("company2", "company2@mail.com", "company2pass"));
				adminCustomerId2 = adminFacade
						.addCustomer(new Customer("coupon2", "buyer2", "couponbuyer2@mail.com", "couponbuyer2pass"));
				System.out.println("added\n--------------------\n");
			}
			System.out.print("test print all companies and customers? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// print all companies and customers test
				System.out.println("--------------------");
				System.out.println("print all companies and customers test");
				System.out.println("all companies: " + adminFacade.getAllCompanies());
				System.out.println("all customers: " + adminFacade.getAllCustomers());
				System.out.println("--------------------\n");
			}
			System.out.print("test print company and customer (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// print company and customer test
				System.out.println("--------------------");
				System.out.println("print company and customer test");
				System.out.println(adminFacade.getCompany(adminCompanyId));
				System.out.println(adminFacade.getCustomer(adminCustomerId));
				System.out.println("--------------------\n");
			}
			System.out.print("test update company and customer test? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// update company and customer test
				System.out.println("--------------------");
				System.out.println("update company and customer test");
				adminFacade.updateCompany(new Company("company", "updatedcompany@mail.com", "updatedcompanypass"));
				adminFacade.updateCustomer(new Customer(adminCustomerId, "updatedcoupon", "updatedbuyer",
						"updatedcouponbuyer@mail.com", "updatedcouponbuyerpass"));
				System.out.println(adminFacade.getCompany(adminCompanyId));
				System.out.println(adminFacade.getCustomer(adminCustomerId));
				System.out.println("--------------------\n");
			}
			System.out.print("test delete company and customer test? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// delete company and customer test
				System.out.println("--------------------");
				System.out.println("delete company and customer test");
				adminFacade.deleteCompany(adminCompanyId2);
				adminFacade.deleteCustomer(adminCustomerId2);
				System.out.println("deleted company2 and customer coupon2");
				System.out.println("--------------------");
			}
		}
		System.out.println("==============================\n");
	}

	private static void companyTest(Scanner in) throws CouponSystemException {
		// company tests
		int lampCouponId = 0;
		int jeansCouponId = 0;
		LocalDate startDate = null;
		LocalDate endDate = null;
		System.out.print("==========COMPANY TESTS==========\n");
		// login manager test
		System.out.println("--------------------");
		System.out.println("login manager test");
		ClientFacade companyClientFacade = LoginManager.getInstance().login("updatedcompany@mail.com",
				"updatedcompanypass", ClientType.COMPANY);
		if (companyClientFacade instanceof CompanyFacade) {
			CompanyFacade companyFacade = (CompanyFacade) companyClientFacade;
			System.out.println("logged in");
			System.out.println("--------------------\n");
			System.out.print("test get company details? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// get company details test
				System.out.println("--------------------");
				System.out.println("company details");
				System.out.println(companyFacade.getCompanyDetails());
				System.out.println("--------------------\n");
			}
			System.out.print("test add coupons? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				// add coupons test
				System.out.println("--------------------");
				System.out.println("add coupons test");
				startDate = LocalDate.of(2021, 9, 24);
				endDate = LocalDate.of(2022, 9, 24);
				lampCouponId = companyFacade.addCoupon(new Coupon(Category.HOME, "lamp", "coupon for a lamp", startDate,
						endDate, 10, 100, "image of a lamp"));
				jeansCouponId = companyFacade.addCoupon(new Coupon(Category.CLOTHING, "jeans", "coupon for jeans",
						startDate, endDate, 10, 50, "image of jeans"));
				System.out.println("added coupons for lamp and jeans");
				System.out.println("--------------------\n");
			}
			System.out.print("test get company coupons? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// get all company coupons test
				System.out.println("--------------------");
				System.out.println("all company coupons");
				System.out.println(companyFacade.getCompanyCoupons());
				System.out.println("--------------------\n");
				// get company coupons by category test
				System.out.println("--------------------");
				System.out.println("coupons with home category only");
				System.out.println(companyFacade.getCompanyCoupons(Category.HOME));
				System.out.println("--------------------\n");
				// get company coupons by max price
				System.out.println("--------------------");
				System.out.println("coupons with max price 50");
				System.out.println(companyFacade.getCompanyCoupons(50));
				System.out.println("--------------------\n");
			}
			System.out.print("test update coupns? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				// update coupon test
				System.out.println("--------------------");
				System.out.println("update coupons");
				Coupon updatedCoupon = new Coupon(Category.CLOTHING, "jeans", "coupon for jeans", startDate, endDate,
						10, 70, "image of jeans");
				companyFacade.updateCoupon(jeansCouponId, updatedCoupon);
				System.out.println("updated jeans coupon to price of 70");
				System.out.println(companyFacade.getCompanyCoupons(Category.CLOTHING));
				System.out.println("--------------------\n");
			}
			System.out.print("test delete coupons? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				// delete coupons test
				System.out.println("--------------------");
				System.out.println("delete coupons");
				companyFacade.deleteCoupon(jeansCouponId);
				System.out.println("removed jeans coupon");
				System.out.println(companyFacade.getCompanyCoupons(Category.CLOTHING));
				System.out.println("--------------------");
			}
		}
		System.out.println("==============================\n");
	}

	private static void customerTest(Scanner in) throws CouponSystemException {
		// customer tests
		System.out.print("==========CUSTOMER TESTS==========\n");
		// login manager test
		System.out.println("--------------------");
		System.out.println("login manager test");
		ClientFacade customerClientFacade = LoginManager.getInstance().login("updatedcouponbuyer@mail.com",
				"updatedcouponbuyerpass", ClientType.CUSTOMER);
		if (customerClientFacade instanceof CustomerFacade) {
			CustomerFacade customerFacade = (CustomerFacade) customerClientFacade;
			System.out.println("logged in");
			System.out.println("--------------------\n");
			System.out.print("test get customer details? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				System.out.println();
				// get customer details test
				System.out.println("--------------------");
				System.out.println("customer details");
				System.out.println(customerFacade.getCustomerDetails());
				System.out.println("--------------------\n");
			}
			System.out.print("test purchase coupon? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				// purchase coupon test
				System.out.println("--------------------");
				System.out.println("purchase test");
				customerFacade.purchaseCoupon(new Coupon(1, "lamp"));
				System.out.println("purchased lamp and jeans coupons");
				System.out.println("--------------------\n");
			}
			System.out.print("test get customer coupons? (y/n)");
			if (in.nextLine().equalsIgnoreCase("Y")) {
				// get customer coupons test
				System.out.println("--------------------");
				System.out.println("get all coupons");
				System.out.println(customerFacade.getCustomerCoupons());
				System.out.println("--------------------\n");
				// get customer coupons by category test
				System.out.println("--------------------");
				System.out.println("get coupons by home category");
				System.out.println(customerFacade.getCustomerCoupons(Category.HOME));
				System.out.println("--------------------\n");
				// get customer coupons by max price
				System.out.println("--------------------");
				System.out.println("get coupons by 70 max price");
				System.out.println(customerFacade.getCustomerCoupons(70));
				System.out.println("--------------------");
			}
		}
		System.out.println("==============================\n");
	}

	private static void poolTest(Scanner in) throws CouponSystemException {
		// close connection pool test
		System.out.print("test close connection pool? (y/n)");
		if (in.nextLine().equalsIgnoreCase("Y")) {
			System.out.println("--------------------");
			System.out.println("stopping daily job...");
			DailyJob.stopJob();
			System.out.println("daily job stopped");
			System.out.println("closing connection pool...");
			ConnectionPool.getInstance().closeAllConnections();
			System.out.println("closed all connections in ConnectionPool");
			System.out.println("--------------------\n");
		}
	}
}
