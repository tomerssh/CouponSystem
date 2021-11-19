package app.core.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;

/**
 * Manages company interaction with the app
 * 
 * @see ClientFacade
 */
@Service
@Transactional
@Scope("prototype")
public class CompanyService extends ClientService {
	// the id the the logged in company
	private int companyId;

	@Autowired
	public CompanyService(CompanyRepository companyRepo, CouponRepository couponRepo) {
		super.companyRepo = companyRepo;
		super.couponRepo = couponRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Company company = companyRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new CouponServiceException("invalid username or password"));
		this.companyId = company.getId();
		return true;
	}

	/**
	 * Find if the company has another coupon with this name
	 * 
	 * @param coupon The coupon to check
	 * @return true if a duplicate was found
	 * @throws CouponSystemException If a database access error occurred
	 */
	public boolean findDuplicateCoupon(Coupon coupon) throws CouponSystemException {
		return couponRepo.existsById(coupon.getId());
	}

}
