package coupons.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coupons.core.beans.Company;
import coupons.core.beans.Coupon;
import coupons.core.beans.Coupon.Category;
import coupons.core.db.ConnectionPool;
import coupons.core.exceptions.CouponDaoException;
import coupons.core.exceptions.CouponSystemException;

/**
 * Database implementation of the CompanyDao interface.
 */
public class CompanyDaoDb implements CompanyDao {
	/**
	 * {@inheritDoc}
	 * 
	 * @throws CouponSystemException If an error occurred while trying to find
	 *                               company.
	 */
	@Override
	public boolean isCompanyExists(String email, String password) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from company where email=? and password=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			// return false if the result set is empty, true if it has entries.
			return rs.next();
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to find company", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws CouponSystemException If an exception was caught while adding
	 *                               company.
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	@Override
	public int addCompany(Company company) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "insert into company values(0, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			con.setAutoCommit(false);
			// add the company
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.executeUpdate();
			con.commit();
			// get the auto generated id for this company
			ResultSet rsKey = pstmt.getGeneratedKeys();
			rsKey.next();
			int id = rsKey.getInt(1);
			return id;
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not addCompany", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws CouponSystemException If an exception was caught while updating
	 *                               company
	 * @throws SQLException          If a database access error occurred while
	 *                               rolling back changes
	 */
	@Override
	public void updateCompany(Company company) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "update company set email=?, password=? where name=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setString(1, company.getEmail());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getName());
			pstmt.executeUpdate();
			con.commit();
//	    System.out.println("DEBUG updated company with id " + companyId);
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not updateCompany", e);
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
	public void deleteCompany(int companyId) throws CouponSystemException, SQLException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "delete from company where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setInt(1, companyId);
			deleteCompanyCoupons(con, companyId);
			pstmt.executeUpdate();
			con.commit();
//	    System.out.println("DEBUG deleted company with id " + companyId);
		} catch (Exception e) {
			con.rollback();
			throw new CouponDaoException("could not deleteCompany", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * Removes a company's coupons from the data storage
	 * 
	 * @param con       A connection to the database
	 * @param companyId The id of the company
	 * @throws CouponSystemException If an exception was caught while removing
	 *                               coupons
	 */
	private void deleteCompanyCoupons(Connection con, int companyId) throws CouponSystemException {
		// use a subquery to delete all company coupons for company with id companyId
		String sql = "delete from customer_coupon where coupon_id in (select id from coupon where company_id=?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, companyId);
			pstmt.executeUpdate();
			// delete the company's coupons from the coupon table
			String deleteCouponsSql = "delete from coupon where company_id=?";
			try (PreparedStatement deleteCoupons = con.prepareStatement(deleteCouponsSql)) {
				deleteCoupons.setInt(1, companyId);
				deleteCoupons.executeUpdate();
			}
//	    System.out.println("DEBUG deleted company coupons for company with id " + companyId);
		} catch (Exception e) {
			throw new CouponDaoException("could not deleteCompanyCoupons", e);
		}
	}

//	old implementation using a loop on result set

//	private void deleteCompanyCoupons(Connection con, int companyId) throws CouponSystemException {
//		// get a result set showing this company's coupons id
//		String sql = "select id from coupon where company_id=?";
//		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
//			pstmt.setInt(1, companyId);
//			ResultSet rs = pstmt.executeQuery();
//			// iterate over result set and delete the coupons from the customer_coupon table
//			String deleteCouponsHistorySql = "delete from customer_coupon where coupon_id=?";
//			while (rs.next()) {
//				PreparedStatement deleteCouponsHistory = con.prepareStatement(deleteCouponsHistorySql);
//				int couponId = rs.getInt(1);
//				deleteCouponsHistory.setInt(1, couponId);
//				deleteCouponsHistory.executeUpdate();
//				deleteCouponsHistory.close();
//			}
//			// delete the company's coupons from the coupon table
//			String deleteCouponsSql = "delete from coupon where company_id=?";
//			try (PreparedStatement deleteCoupons = con.prepareStatement(deleteCouponsSql)) {
//				deleteCoupons.setInt(1, companyId);
//				deleteCoupons.executeUpdate();
//			}
////	    System.out.println("DEBUG deleted company coupons for company with id " + companyId);
//		} catch (Exception e) {
//			throw new CouponSystemException("could not deleteCompanyCoupons", e);
//		}
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Company> getCompanies() throws CouponSystemException {
		List<Company> companies = new ArrayList<Company>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from company";
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Company company = new Company(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				companies.add(company);
			}
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCompanies", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return companies;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Company getCompany(int companyId) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from company where id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Company company = new Company(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			return company;
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCompany", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCompanyId(String email, String password) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from company where email=? and password=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int companyId = rs.getInt(1);
				return companyId;
			} else {
				throw new CouponDaoException("invalid argument");
			}
		} catch (Exception e) {
			throw new CouponDaoException("an error occured while trying to getCompanyId", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean findDuplicateCoupon(int companyId, Coupon coupon) throws CouponSystemException {
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where company_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String couponTitle = rs.getString(4);
				if (couponTitle.equals(coupon.getTitle())) {
//		    System.out.println("DEBUG found duplicate coupon for company with id " + companyId);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new CouponDaoException("could not findDuplicateCoupon", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException {
		List<Coupon> companyCoupons = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where company_id=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon(rs.getInt(1), rs.getInt(2), Category.valueOf(rs.getString(3)),
						rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate(),
						rs.getInt(8), rs.getDouble(9), rs.getString(10));
				companyCoupons.add(coupon);
			}
			return companyCoupons;
		} catch (Exception e) {
			throw new CouponDaoException("could not getCompanyCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException {
		List<Coupon> companyCoupons = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where company_id=? and category=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, companyId);
			pstmt.setString(2, category.name());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon(rs.getInt(1), rs.getInt(2), Category.valueOf(rs.getString(3)),
						rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate(),
						rs.getInt(8), rs.getDouble(9), rs.getString(10));
				companyCoupons.add(coupon);
			}
			return companyCoupons;
		} catch (Exception e) {
			throw new CouponDaoException("could not getCompanyCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException {
		List<Coupon> companyCoupons = new ArrayList<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupon where company_id=? and price<=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, companyId);
			pstmt.setDouble(2, maxPrice);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon(rs.getInt(1), rs.getInt(2), Category.valueOf(rs.getString(3)),
						rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate(),
						rs.getInt(8), rs.getDouble(9), rs.getString(10));
				companyCoupons.add(coupon);
			}
			return companyCoupons;
		} catch (Exception e) {
			throw new CouponDaoException("could not getCompanyCoupons", e);
		} finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
}
