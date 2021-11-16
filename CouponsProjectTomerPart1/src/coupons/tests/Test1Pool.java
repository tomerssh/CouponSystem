package coupons.tests;

import java.sql.Connection;

import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponSystemException;

public class Test1Pool {
    public static void main(String[] args) {
	try {
	    ConnectionPool pool = ConnectionPool.getInstance();
	    Connection con = pool.getConnection();
	    pool.restoreConnection(con);
	    pool.closeAllConnections();
	} catch (CouponSystemException e) {
	    e.printStackTrace();
	}
    }
}
