package app.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = CouponServiceException.class)
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

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			this.companyId = opt.get().getId();
			this.company = opt.get();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Add a coupon to the data storage for this company
	 * 
	 * @param coupon The customer to be added.
	 * @return The coupon's id.
	 * @throws CouponSystemException
	 */
	public int addCoupon(Coupon coupon) throws CouponSystemException {
		coupon = couponRepo.save(coupon);
		this.company.getCoupons().add(coupon);
		coupon.setCompany(this.company);
		return coupon.getId();
	}

	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Optional<Coupon> opt = couponRepo.findById(coupon.getId());
		if (opt.isPresent()) {
			Coupon couponInDb = opt.get();
			couponInDb.setCategory(coupon.getCategory());
			couponInDb.setTitle(coupon.getTitle());
			couponInDb.setDescription(coupon.getDescription());
			couponInDb.setStartDate(coupon.getStartDate());
			couponInDb.setEndDate(coupon.getEndDate());
			couponInDb.setAmount(coupon.getAmount());
			couponInDb.setPrice(coupon.getPrice());
			couponInDb.setImage(coupon.getImage());
		} else {
			throw new CouponServiceException("coupon with id " + coupon.getId() + " not found");
		}
	}

	public void deleteCoupon(int couponId) throws CouponSystemException {
		Optional<Coupon> opt = couponRepo.findById(couponId);
		if (opt.isPresent()) {
			couponRepo.delete(opt.get());
			this.company.getCoupons().remove(opt.get());
		} else {
			throw new CouponServiceException("coupon with id " + couponId + " not found");
		}
	}

	/**
	 * Takes a coupon id and returns the amount value
	 * 
	 * @param couponId The coupon id
	 * @return The amount value
	 * @throws CouponSystemException
	 */
	public int getAmount(int couponId) throws CouponSystemException {
		return couponRepo.getAmount(couponId);
	}

	/**
	 * Find if the company has another coupon with this name
	 * 
	 * @param coupon The coupon to check
	 * @return true if a duplicate was found
	 * @throws CouponSystemException If a database access error occurred
	 */
	public boolean findDuplicateCoupon(int couponId) throws CouponSystemException {
		return couponRepo.existsById(couponId);
	}

	/**
	 * Return a list of all coupons of this company
	 * 
	 * @return A list of all coupons
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons() throws CouponSystemException {
		return couponRepo.findAllByCompanyId(this.companyId);
	}

	/**
	 * Return a list of all coupons of the company with given category
	 * 
	 * @param category The coupon category
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
		return couponRepo.findAllByCompanyIdAndCategory(this.companyId, category);
	}

	/**
	 * Return a list of all coupons of the company with given maximum price
	 * 
	 * @param maxPrice The maximum price
	 * @return A list of all coupons of given category
	 * @throws CouponSystemException If a database access error occurred
	 */
	public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
		return couponRepo.findAllByCompanyIdAndPrice(this.companyId, maxPrice);
	}

	/**
	 * Get company details
	 * 
	 * @return A company object containing the company details
	 */
	public Company getCompanyDetails() {
		return this.company;
	}

}
