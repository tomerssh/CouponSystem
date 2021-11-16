package coupons.core.exceptions;

public class CouponDaoException extends CouponSystemException {

	private static final long serialVersionUID = 1L;

	public CouponDaoException() {
		super();
	}

	public CouponDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponDaoException(String message) {
		super(message);
	}

	public CouponDaoException(Throwable cause) {
		super(cause);
	}

}
