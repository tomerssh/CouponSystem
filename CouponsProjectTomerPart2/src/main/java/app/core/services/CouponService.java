package app.core.services;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CouponRepository;

/**
 * Service for coupons
 */
@Service
@Transactional
@Scope("prototype")
public class CouponService {
	private CouponRepository couponRepo;

	@Autowired
	public CouponService(CouponRepository couponRepo) {
		this.couponRepo = couponRepo;
	}

	/**
	 * Remove all expired coupons and purchase history
	 * 
	 * @throws CouponSystemException If a database access error occurred
	 * @return The count of deleted coupons
	 */
	public int cleanExpiredCoupons() throws CouponSystemException {
		return couponRepo.deleteAllExpired(LocalDate.now());
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
	 * Add a coupon to the data storage for the company with this id.
	 * 
	 * @param coupon The customer to be added.
	 * @return The coupon's id.
	 * @throws CouponSystemException
	 */
	public int addCoupon(Coupon coupon) throws CouponSystemException {
		coupon = couponRepo.save(coupon);
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
		} else {
			throw new CouponServiceException("coupon with id " + couponId + " not found");
		}
	}

}
