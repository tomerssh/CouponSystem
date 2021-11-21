package app.core.tasks;

import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.core.exceptions.CouponSystemException;
import app.core.services.CouponService;

@Transactional
@Service
public class CouponExpirationDailyJob {
	private CouponService couponService;

	@Autowired
	public CouponExpirationDailyJob(CouponService couponService) {
		this.couponService = couponService;
	}

	@Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 24)
	public void start() {
		try {
			couponService.cleanExpiredCoupons();
		} catch (CouponSystemException e) {
			System.out.println("CouponExpirationDailyJob runtime error" + e.getMessage());
		}
	}

}
