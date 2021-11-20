package app.core.services.login;

import javax.transaction.Transactional;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

/**
 * Singleton - Login system
 */
@Service
@Transactional
public class LoginManager implements ApplicationContextAware {
	public enum ClientType {
		ADMINISTRATOR, COMPANY, CUSTOMER
	}

	private ApplicationContext ctx;
	private AdminService adminService;
	private CompanyService companyService;
	private CustomerService customerService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
		this.adminService = ctx.getBean(AdminService.class);
		this.companyService = ctx.getBean(CompanyService.class);
		this.customerService = ctx.getBean(CustomerService.class);
	}

	/**
	 * Attempt to login a client to the system
	 * 
	 * @param email      The client email
	 * @param password   The client password
	 * @param clientType The client type
	 * @return A client facade
	 * @throws CouponSystemException If the client does not exist
	 */
	public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
		switch (clientType) {
		case ADMINISTRATOR:
			if (adminService.login(email, password)) {
				return ctx.getBean(AdminService.class);
			} else {
				throw new CouponServiceException("cannot login, invalid admin credentials");
			}
		case COMPANY:
			if (companyService.login(email, password)) {
				return ctx.getBean(CompanyService.class);
			} else {
				throw new CouponServiceException("cannot login, invalid company credentials");
			}
		case CUSTOMER:
			if (customerService.login(email, password)) {
				return ctx.getBean(CustomerService.class);
			} else {
				throw new CouponServiceException("cannot login, invalid customer credentials");
			}
		default:
			throw new CouponServiceException("cannot login, invalid ClientType");
		}
	}
}
