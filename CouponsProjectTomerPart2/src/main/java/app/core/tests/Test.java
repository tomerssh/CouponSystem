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
import app.core.services.CouponService;
import app.core.services.CustomerService;
import app.core.services.login.LoginManager;
import app.core.services.login.LoginManager.ClientType;

@Component
public class Test implements CommandLineRunner, ApplicationContextAware {
	private ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		try {
			LoginManager lm = ctx.getBean(LoginManager.class);
			adminTests(lm);
			companyTests(lm);
			customerTests(lm);
			couponTests();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	private void adminTests(LoginManager lm) throws CouponSystemException {
		AdminService adminService = (AdminService) lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		adminService.addCompany(new Company("aaa", "aaa@mail", "1234"));
		adminService.addCustomer(new Customer("aaa", "bbb", "customer@mail", "1234"));
	}

	private void companyTests(LoginManager lm) throws CouponSystemException {
		CompanyService companyService = (CompanyService) lm.login("aaa@mail", "1234", ClientType.COMPANY);
	}

	private void customerTests(LoginManager lm) throws CouponSystemException {
		CustomerService customerService = (CustomerService) lm.login("customer@mail", "1234", ClientType.CUSTOMER);
	}

	private void couponTests() throws CouponSystemException {
		CouponService couponService = ctx.getBean(CouponService.class);
		couponService.addCoupon(new Coupon(Category.CAMPING, "tent", "tent description", LocalDate.now(),
				LocalDate.of(2019, 1, 2), 5, 100, "tent image"));
	}

}
