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
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exceptions.CouponSystemException;
import app.core.services.CompanyService;

@RestController
@RequestMapping("/rest/company")
@CrossOrigin
public class CompanyController extends ClientController {

	private CompanyService service;

	@Autowired
	public CompanyController(CompanyService service) {
		this.service = service;
	}

	@Override
	public boolean login(String email, String password) {
		try {
			return this.service.login(email, password);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping
	public void addCoupon(@RequestBody Coupon coupon) {
		try {
			this.service.addCoupon(coupon);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) {
		try {
			this.service.updateCoupon(coupon);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable int couponId) {
		try {
			this.service.deleteCoupon(couponId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/all")
	public List<Coupon> getCompanyCoupons() {
		try {
			return this.service.getCompanyCoupons();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/category/{category}")
	public List<Coupon> getCompanyCoupons(@PathVariable Category category) {
		try {
			return this.service.getCompanyCoupons(category);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/price/{maxPrice}")
	public List<Coupon> getCompanyCoupons(@PathVariable double maxPrice) {
		try {
			return this.service.getCompanyCoupons(maxPrice);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping
	public Company getCompanyDetails() {
		try {
			return this.service.getCompanyDetails();
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
