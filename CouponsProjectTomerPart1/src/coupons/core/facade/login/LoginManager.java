package coupons.core.facade.login;

import coupons.core.exceptions.CouponSystemException;
import coupons.core.facade.AdminFacade;
import coupons.core.facade.ClientFacade;
import coupons.core.facade.CompanyFacade;
import coupons.core.facade.CustomerFacade;

/**
 * Singleton - Login system
 */
public class LoginManager {
	public enum ClientType {
		ADMINISTRATOR, COMPANY, CUSTOMER
	}

	private static LoginManager instance;

	private LoginManager() {
	}

	public static LoginManager getInstance() {
		if (instance == null) {
			instance = new LoginManager();
		}
		return instance;
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
	public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {
		switch (clientType) {
		case ADMINISTRATOR:
			AdminFacade adminFacade = new AdminFacade();
			if (adminFacade.login(email, password)) {
				return adminFacade;
			} else {
				return null;
			}
		case COMPANY:
			CompanyFacade companyFacade = new CompanyFacade();
			if (companyFacade.login(email, password)) {
				return companyFacade;
			} else {
				return null;
			}
		case CUSTOMER:
			CustomerFacade customerFacade = new CustomerFacade();
			if (customerFacade.login(email, password)) {
				return customerFacade;
			} else {
				return null;
			}
		default:
			return null;
		}
	}
}
