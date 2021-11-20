package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import app.core.entities.Company;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.login.LoginManager;
import app.core.services.login.LoginManager.ClientType;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		LoginManager lm = ctx.getBean(LoginManager.class);
		try {
			AdminService adminService = (AdminService) lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
			adminService.addCompany(new Company("aaa", "aaa@mail", "1234"));
			CompanyService companyService = (CompanyService) lm.login("aaa@mail", "1234", ClientType.COMPANY);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
