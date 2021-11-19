package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
