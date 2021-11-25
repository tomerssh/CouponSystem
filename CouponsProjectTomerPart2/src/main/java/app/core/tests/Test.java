package app.core.tests;

import java.time.LocalDate;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import app.core.services.login.LoginManager;
import app.core.services.login.LoginManager.ClientType;

@Component
public class Test implements CommandLineRunner, ApplicationContextAware {
	private ApplicationContext ctx;
	private LoginManager lm;
	private AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {
		try {
			adminTests(lm);
			companyTests(lm);
			customerTests(lm);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
		this.lm = ctx.getBean(LoginManager.class);
	}

	public void adminTests(LoginManager lm) throws CouponSystemException {
		System.out.println("==================== ADMIN TESTS");
		adminService = (AdminService) lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		adminCompanyTests();
		adminCustomerTests();
		System.out.println("====================");
	}

	private void adminCompanyTests() throws CouponSystemException {
		System.out.println("--- addCompany");
		System.out.println(adminService.addCompany(new Company("aaa", "aaa@mail", "1234")));
		System.out.println(adminService.addCompany(new Company("company", "company@mail", "1234")));
		System.out.println();

		System.out.println("--- getAllCompanies");
		System.out.println(adminService.getAllCompanies());
		System.out.println();

		System.out.println("--- updateCompany with id 1");
		adminService.updateCompany(new Company(1, "bbb", "bbb@mail", "5678"));
		System.out.println("--- updated company with id 1");
		System.out.println();

		System.out.println("--- getCompany with id 1");
		System.out.println(adminService.getCompany(1));
		System.out.println();

		System.out.println("--- deleteCompany with id 1");
		adminService.deleteCompany(1);
		System.out.println("--- deleted company with id 1");
	}

	private void adminCustomerTests() throws CouponSystemException {
		System.out.println("--- addCustomer");
		System.out.println(adminService.addCustomer(new Customer("aaa", "bbb", "aaa@mail", "1234")));
		System.out.println(adminService.addCustomer(new Customer("customer", "customer", "customer@mail", "1234")));
		System.out.println();

		System.out.println("--- getAllCustomers");
		System.out.println(adminService.getAllCustomers());
		System.out.println();

		System.out.println("--- updateCustomer with id 1");
		adminService.updateCustomer(new Customer(1, "ccc", "ddd", "newcustomer@mail", "5678"));
		System.out.println("updated customer with id 1");
		System.out.println();

		System.out.println("--- getCustomer with id 1");
		System.out.println(adminService.getCustomer(1));
		System.out.println();

		System.out.println("--- deleteCustomer for customer with id 1");
		adminService.deleteCustomer(1);
		System.out.println("deleted customer with id 1");
	}

	public void companyTests(LoginManager lm) throws CouponSystemException {
		System.out.println("==================== COMPANY TESTS");
		companyService = (CompanyService) lm.login("company@mail", "1234", ClientType.COMPANY);

		System.out.println("--- addCoupon");
		System.out.println(companyService.addCoupon(new Coupon(Category.CAMPING, "tent", "tent description",
				LocalDate.of(2021, 1, 2), LocalDate.of(2022, 1, 2), 5, 100, "tent image")));
		System.out.println(companyService.addCoupon(new Coupon(Category.CLOTHING, "tshirt", "tshirt description",
				LocalDate.of(2021, 1, 2), LocalDate.of(2022, 1, 2), 10, 50, "tshirt image")));
		System.out.println();

		System.out.println("--- getCompanyCoupons");
		System.out.println(companyService.getCompanyCoupons());
		System.out.println();

		System.out.println("--- getCompanyCoupons by max price 50");
		System.out.println(companyService.getCompanyCoupons(50));
		System.out.println();

		System.out.println("--- updateCoupon for coupon with id 1");
		companyService.updateCoupon(new Coupon(1, Category.CAMPING, "updated tent", "updated tent description",
				LocalDate.of(2021, 1, 2), LocalDate.of(2022, 1, 2), 5, 100, "updated tent image"));
		System.out.println("updated coupon with id 1");
		System.out.println();

		System.out.println("--- getCompanyCoupons by camping category");
		System.out.println(companyService.getCompanyCoupons(Category.CAMPING));
		System.out.println();

		System.out.println("--- findDuplicateCoupon for coupon with id 1");
		System.out.println(companyService.findDuplicateCoupon(1));
		System.out.println();

		System.out.println("--- deleteCoupon for coupon with id 1");
		companyService.deleteCoupon(1);
		System.out.println("--- deleted coupon with id 1");
		System.out.println();

		System.out.println("--- getAmount for coupon with id 2");
		System.out.println(companyService.getAmount(2));
		System.out.println("====================");
	}

	public void customerTests(LoginManager lm) throws CouponSystemException {
		System.out.println("==================== CUSTOMER TESTS");
		customerService = (CustomerService) lm.login("customer@mail", "1234", ClientType.CUSTOMER);

		System.out.println("--- getCustomerDetails");
		System.out.println(customerService.getCustomerDetails());
		System.out.println();

		System.out.println("--- addCouponPurchase for coupon with id 2");
		customerService.addCouponPurchase(new Coupon(2));
		System.out.println("added coupon purchase");
		System.out.println();

		System.out.println("--- wasCouponPurchased for coupon with id 2");
		System.out.println(customerService.wasCouponPurchased(2));
		System.out.println();

		System.out.println("--- getCustomerCouponsById for coupon id 2");
		System.out.println(customerService.getCustomerCouponsById(2));
		System.out.println();

		System.out.println("--- getCustomerCouponsByIdAndCategory for coupon id 2 and category clothing");
		System.out.println(customerService.getCustomerCouponsByIdAndCategory(2, Category.CLOTHING));
		System.out.println();

		System.out.println("--- getCustomerCouponsByIdAndMaxPrice for coupon id 2 and max price 20");
		System.out.println(customerService.getCustomerCouponsByIdAndMaxPrice(2, 20));
		System.out.println();

		System.out.println("--- deleteCouponPurchase for coupon with id 2");
		customerService.deleteCouponPurchase(2);
		System.out.println("--- deleted coupon purchase for coupon with id 2");
		System.out.println("====================");
	}

}
