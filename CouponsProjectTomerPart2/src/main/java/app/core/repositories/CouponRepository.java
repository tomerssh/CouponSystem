package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	@Query(value = "from Coupon where company_id=companyId")
	public List<Coupon> findAllByCompanyId(int companyId);

	@Query(value = "from Coupon where company_id=companyId AND category=category")
	public List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

	@Query(value = "from Coupon where company_id=companyId AND price=maxPrice")
	public List<Coupon> findAllByCompanyIdAndMaxPrice(int companyId, double maxPrice);
}
