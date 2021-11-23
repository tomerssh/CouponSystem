package app.core.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CustomerCouponId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "coupon_id")
	private Integer couponId;

}
