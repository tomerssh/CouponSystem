package app.core.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class CustomerCouponAssociation {

	@EmbeddedId
	private CustomerCouponId customerCouponId;
	@ManyToOne
	@MapsId("customerId")
	private Customer customer;
	@ManyToOne
	@MapsId("couponId")
	private Coupon coupon;

}
