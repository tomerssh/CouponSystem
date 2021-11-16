package coupons.core.exceptions;

public class CouponFacadeException extends CouponSystemException {

	private static final long serialVersionUID = 1L;

	public CouponFacadeException() {
		super();
	}

	public CouponFacadeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponFacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponFacadeException(String message) {
		super(message);
	}

	public CouponFacadeException(Throwable cause) {
		super(cause);
	}

}
