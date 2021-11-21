package app.core.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Customer;
import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

/**
 * Manages customer interaction with the app
 * 
 * @see ClientService
 */
@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {
	// the id the the logged in customer
	private int customerId;
	// reference to the current customer
	private Customer customer;

	@Autowired
	public CustomerService(CustomerRepository customerRepo, CouponRepository couponRepo) {
		super.customerRepo = customerRepo;
		super.couponRepo = couponRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		this.customer = customerRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new CouponServiceException("invalid username or password"));
		this.customerId = customer.getId();
		return true;
	}

	public void addCouponPurchase(int couponId) throws CouponSystemException {
		couponRepo.addCouponPurchase(this.customerId, couponId);
	}

	public void deleteCouponPurchase(int couponId) throws CouponSystemException {
		couponRepo.deleteCouponPurchase(this.customerId, couponId);
	}

	public boolean wasCouponPurchased(int couponId) throws CouponSystemException {
		return couponRepo.wasPurchased(this.customerId, couponId);
	}
}
