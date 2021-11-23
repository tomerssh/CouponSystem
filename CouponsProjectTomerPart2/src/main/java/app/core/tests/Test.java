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
		adminService.addCompany(new Company("aaa", "aaa@mail", "1234"));
		adminService.addCompany(new Company("company", "company@mail", "1234"));
		adminService.getAllCompanies();
		adminService.updateCompany(new Company(1, "bbb", "bbb@mail", "5678"));
		adminService.getCompany(1);
		adminService.deleteCompany(1);
	}

	private void adminCustomerTests() throws CouponSystemException {
		adminService.addCustomer(new Customer("aaa", "bbb", "aaa@mail", "1234"));
		adminService.addCustomer(new Customer("customer", "customer", "customer@mail", "1234"));
		adminService.getAllCustomers();
		adminService.updateCustomer(new Customer(1, "ccc", "ddd", "newcustomer@mail", "5678"));
		adminService.getCustomer(1);
		adminService.deleteCustomer(1);
	}

	public void companyTests(LoginManager lm) throws CouponSystemException {
		System.out.println("==================== COMPANY TESTS");
		companyService = (CompanyService) lm.login("company@mail", "1234", ClientType.COMPANY);
		companyService.addCoupon(new Coupon(Category.CAMPING, "tent", "tent description", LocalDate.of(2021, 1, 2),
				LocalDate.of(2022, 1, 2), 5, 100, "tent image"));
		companyService.addCoupon(new Coupon(Category.CLOTHING, "tshirt", "tshirt description", LocalDate.of(2021, 1, 2),
				LocalDate.of(2022, 1, 2), 10, 50, "tshirt image"));
		companyService.getCompanyCoupons();
		companyService.getCompanyCoupons(50);
		companyService.updateCoupon(new Coupon(1, Category.CAMPING, "updated tent", "updated tent description",
				LocalDate.of(2021, 1, 2), LocalDate.of(2022, 1, 2), 5, 100, "updated tent image"));
		companyService.getCompanyCoupons(Category.CAMPING);
		companyService.findDuplicateCoupon(1);
		companyService.deleteCoupon(1);
		System.out.println(companyService.getAmount(2));
		System.out.println("====================");
	}

	public void customerTests(LoginManager lm) throws CouponSystemException {
		System.out.println("==================== CUSTOMER TESTS");
		customerService = (CustomerService) lm.login("customer@mail", "1234", ClientType.CUSTOMER);
		customerService.getCustomerDetails();
		customerService.addCouponPurchase(new Coupon(2));
		customerService.wasCouponPurchased(2);
		customerService.getCustomerCouponsById(2);
		customerService.getCustomerCouponsByIdAndCategory(2, Category.CLOTHING);
		customerService.getCustomerCouponsByIdAndMaxPrice(2, 20);
		customerService.deleteCouponPurchase(2);
		System.out.println("====================");
	}

}
