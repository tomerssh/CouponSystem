package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;

@RestController
@RequestMapping("/rest/admin")
@CrossOrigin
public class AdminController extends ClientController {

	private AdminService service;

	@Autowired
	public AdminController(AdminService service) {
		this.service = service;
	}

	@Override
//	@RequestMapping("/login")
	public boolean login(String email, String password) {
		try {
			return this.service.login(email, password);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/company")
	public void addCompany(@RequestBody Company company) {
		try {
			this.service.addCompany(company);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("/company")
	public void updateCompany(@RequestBody Company company) {
		try {
			this.service.updateCompany(company);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/company/{companyId}")
	public void deleteCompany(@PathVariable int companyId) {
		try {
			this.service.deleteCompany(companyId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/company")
	public List<Company> getAllCompanies() {
		try {
			return this.service.getAllCompanies();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/company/{companyId}")
	public Company getOneCompany(@PathVariable int companyId) {
		try {
			return this.service.getCompany(companyId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping("/customer")
	public void addCustomer(@RequestBody Customer customer) {
		try {
			this.service.addCustomer(customer);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("/customer")
	public void updateCustomer(@RequestBody Customer customer) {
		try {
			this.service.updateCustomer(customer);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/customer/{customerId}")
	public void deleteCustomer(@PathVariable int customerId) {
		try {
			this.service.deleteCustomer(customerId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		try {
			return this.service.getAllCustomers();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/customer/{customerId}")
	public Customer getOneCustomer(@PathVariable int customerId) {
		try {
			return this.service.getCustomer(customerId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
