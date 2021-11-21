package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;

/**
 * Manages company interaction with the app
 * 
 * @see ClientService
 */
@Service
@Transactional
@Scope("prototype")
public class CompanyService extends ClientService {
	// the id the the logged in company
	private int companyId;
	// reference to the current company
	private Company company;

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
		this.company = companyRepo.findByEmailAndPassword(email, password)
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

	/**
	 * Return a list of all coupons of the company with given id
	 * 
	 * @param companyId The company id
	 * @return A list of all coupons
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException {
		return couponRepo.findAllByCompanyId(companyId);

//		List<Integer> ids = new ArrayList<>();
//		ids.add(companyId);
//		List<Coupon> coupons = couponRepo.findAllById(ids);
//		ids = null;
//		return coupons;
	}

	/**
	 * Return a list of all coupons of the company with given category
	 * 
	 * @param companyId The company id
	 * @param category  The coupon category
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException {
		return couponRepo.findAllByCompanyIdAndCategory(companyId, category);
	}

	/**
	 * Return a list of all coupons of the company with given maximum price
	 * 
	 * @param companyId The company id
	 * @param maxPrice  The maximum price
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException {
		return couponRepo.findAllByCompanyIdAndMaxPrice(companyId, maxPrice);
	}

}
