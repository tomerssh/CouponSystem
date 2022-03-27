package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.exceptions.CouponSystemException;
import app.core.services.ClientService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import app.core.services.login.LoginManager;
import app.core.services.login.LoginManager.ClientType;
import app.core.utils.JwtUtil;
import app.core.utils.JwtUtil.ClientDetails;

@RestController
@RequestMapping
@CrossOrigin
public class LoginController {
	private LoginManager loginManager;
	private JwtUtil jwtUtil;

	@Autowired
	public LoginController(LoginManager loginManager, JwtUtil jwtUtil) {
		this.loginManager = loginManager;
		this.jwtUtil = jwtUtil;
	}

	@PutMapping("/login")
	public String login(@RequestBody String email, String password, ClientType clientType) {
		try {
			ClientService service = this.loginManager.login(email, password, clientType);
			int id = extractId(service);
			return jwtUtil.generateToken(new ClientDetails(id, email, clientType));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	private int extractId(ClientService service) {
		if (service instanceof CompanyService) {
			CompanyService companyService = (CompanyService) service;
			try {
				return companyService.getCompanyDetails().getId();
			} catch (CouponSystemException e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			}
		} else if (service instanceof CustomerService) {
			CustomerService customerService = (CustomerService) service;
			try {
				return customerService.getCustomerDetails().getId();
			} catch (CouponSystemException e) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
			}
		}
		// in case service is an instance of AdminService return 0, if the login
		// credentials are wrong or the client type is null
		// loginManager.login will throw an exception
		else {
			return 0;
		}
	}

}
