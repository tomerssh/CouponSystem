package coupons.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponDaoException;
import coupons.core.exceptions.CouponSystemException;

/**
 * Database implementation of the CustomerDao interface.
 */
public class CouponDaoDb implements CouponDao {
	/**
	 * {@inheritDoc}
	 * 
	 * @throws CouponSystemException If a database access error occurred
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	@Override
	public int addCoupon(int companyId, Coupon coupon) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "insert into coupon values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			con.setAutoCommit(false);
			// add the coupon
			pstmt.setInt(1, companyId);
			pstmt.setString(2, coupon.getCategory().name());
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, Date.valueOf(coupon.getStartDate()));
			pstmt.setDate(6, Date.valueOf(coupon.getEndDate()));
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			pstmt.executeUpdate();
			con.commit();
			// get the auto generated id for this coupon
			ResultSet rsKey = pstmt.getGeneratedKeys();
			rsKey.next();
			int id = rsKey.getInt(1);
			return id;
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not addCoupon", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException If a database access error occurred while rolling back
	 *                      changes
	 */
	@Override
	public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "update coupon set category=?, title=?, description=?, start_date=?, end_date=?, amount=?, price=?, image=? where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setString(1, coupon.getCategory().name());
			pstmt.setString(2, coupon.getTitle());
			pstmt.setString(3, coupon.getDescription());
			pstmt.setDate(4, Date.valueOf(coupon.getStartDate()));
			pstmt.setDate(5, Date.valueOf(coupon.getEndDate()));
			pstmt.setInt(6, coupon.getAmount());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			pstmt.setInt(9, couponId);
			pstmt.executeUpdate();
			con.commit();
//	    System.out.println("DEBUG updated coupon with id " + couponId);
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not updateCoupon", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void cleanExpiredCoupons() throws CouponSystemException {
		try {
			List<Integer> expiredCoupons;
			expiredCoupons = getExpiredCoupons();
			Iterator<Integer> it = expiredCoupons.iterator();
			while (it.hasNext()) {
				Integer expiredCouponId = it.next();
				deleteCoupon(expiredCouponId);
			}
		} catch (Exception e) {
			throw new CouponDaoException("could not cleanExpiredCoupons", e);
		}

	}

	/**
	 * Return a list of all expired coupons id
	 * 
	 * @return A list of all expired coupons id
	 * @throws CouponSystemException If a database access error occurred
	 */
	private List<Integer> getExpiredCoupons() throws CouponSystemException {
		List<Integer> expiredCouponsIdList = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select id from coupon where end_date <= ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				expiredCouponsIdList.add(rs.getInt(1));
			}
			return expiredCouponsIdList;
		} catch (Exception e) {
			throw new CouponDaoException("could not getExpiredCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException If a database access error occurred while rolling back
	 *                      changes
	 */
	@Override
	public synchronized void deleteCoupon(int couponId) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "delete from coupon where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			deleteCouponHistory(con, couponId);
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
			con.commit();
//	    System.out.println("DEBUG deleted coupon with id " + couponId);
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not deleteCoupon", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * Get a coupon id and delete the coupon purchase history
	 * 
	 * @param con      A connection to the database
	 * @param couponId The coupon id
	 * @throws CouponSystemException If a database access error occurred
	 */
	private synchronized void deleteCouponHistory(Connection con, int couponId) throws CouponSystemException {
		String sql = "delete from customer_coupon where coupon_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
//	    System.out.println("DEBUG deleted coupon history for coupon with id " + couponId);
		} catch (Exception e) {
			throw new CouponDaoException("could not deleteCouponHistory", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coupon> getCoupons() throws CouponSystemException {
		List<Coupon> list = new ArrayList<Coupon>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coupon coupon = new Coupon(rs.getInt(1), rs.getInt(2), Category.valueOf(rs.getString(3)),
						rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), (rs.getDate(7).toLocalDate()),
						rs.getInt(8), rs.getDouble(9), rs.getString(10));
				list.add(coupon);
			}
		} catch (Exception e) {
			throw new CouponDaoException("could not getCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException {
		List<Coupon> list = new ArrayList<Coupon>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select coupon_id from customer_coupon where customer_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			// assuming customer has to log in to use this method so not validating argument
//			if (!rs.next()) {
//				throw new CouponSystemException("invalid arguments");
//			}
//			Coupon coupon = getCoupon(rs.getInt(1));
//			list.add(coupon);
			while (rs.next()) {
				Coupon coupon = getCoupon(rs.getInt(1));
				list.add(coupon);
			}
		} catch (Exception e) {
			throw new CouponDaoException("could not getCustomerCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponSystemException {
		List<Coupon> list = new ArrayList<Coupon>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select coupon_id from customer_coupon where customer_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = getCoupon(rs.getInt(1));
				if (coupon.getCategory().name() == category.name()) {
					list.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponDaoException("could not getCustomerCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponSystemException {
		List<Coupon> list = new ArrayList<Coupon>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select coupon_id from customer_coupon where customer_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = getCoupon(rs.getInt(1));
				if (coupon.getPrice() <= maxPrice) {
					list.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponDaoException("could not getCustomerCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Coupon getCoupon(int couponId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Coupon coupon = new Coupon(rs.getInt(1), rs.getInt(2), Category.valueOf(rs.getString(3)), rs.getString(4),
					rs.getString(5), rs.getDate(6).toLocalDate(), (rs.getDate(7).toLocalDate()), rs.getInt(8),
					rs.getDouble(9), rs.getString(10));
			return coupon;
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCoupon", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCouponId(Coupon coupon) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where company_id=? and title=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setString(2, coupon.getTitle());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int couponId = rs.getInt(1);
				return couponId;
			} else {
				throw new CouponDaoException("invalid argument");
			}
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCouponId", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException If a database access error occurred while rolling back
	 *                      changes
	 */
	@Override
	public void addCouponPurchase(int customerId, int couponId) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "insert into customer_coupon values(?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			pstmt.executeUpdate();
			decrementCouponAmount(con, couponId);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not addCouponPurchase", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * Decrements the coupon amount by 1
	 * 
	 * @param con      A connection to the database
	 * @param couponId The coupon id
	 * @throws CouponSystemException If a database access error occurred
	 */
	private void decrementCouponAmount(Connection con, int couponId) throws CouponSystemException {
		String sql = "update coupon set amount = amount - 1 where id=? and amount > 0";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new CouponDaoException("could  not decrementCouponAmount", e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SQLException If a database access error occurred while rolling back
	 *                      changes
	 */
	@Override
	public void deleteCouponPurchase(int customerId, int couponId) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "delete from customer_coupon where customer_id=? and coupon_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not deleteCouponPurchase", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean wasPurchased(int couponId, int customerId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from customer_coupon where customer_id=? and coupon_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			throw new CouponDaoException("could not check if the coupon wasPurchased", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAmount(int couponId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select amount from coupon where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponId);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				throw new CouponDaoException("illegal arguments");
			}
			return rs.getInt(1);
		} catch (Exception e) {
			throw new CouponDaoException("could not getAmount", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExpired(int couponId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select end_date from coupon where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponId);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				throw new CouponDaoException("illegal arguments");
			}
			Date systemDate = new Date(System.currentTimeMillis());
			if (rs.getDate(1).before(systemDate)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new CouponDaoException("could not check if the coupon isExpired", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

}
