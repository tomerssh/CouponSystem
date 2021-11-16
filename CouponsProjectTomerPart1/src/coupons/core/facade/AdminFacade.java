package coupons.core.facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import coupons.core.beans.Company;
import coupons.core.beans.Customer;
import coupons.core.dao.CompanyDaoDb;
import coupons.core.dao.CustomerDaoDb;
import coupons.core.exceptions.CouponDaoException;
import coupons.core.exceptions.CouponFacadeException;
import coupons.core.exceptions.CouponSystemException;

/**
 * Manages administrator interaction with the app.
 * 
 * @see ClientFacade
 */
public class AdminFacade extends ClientFacade {
	String adminPassword;

	{
		// loads default password from default configuration file
		Properties defaultProperties = new Properties();
		File defaultPropertiesFile = new File("files/default.app.properties");
		try (FileInputStream defaultFin = new FileInputStream(defaultPropertiesFile)) {
			defaultProperties.load(defaultFin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// loads password from configuration file
		Properties properties = new Properties(defaultProperties);
		File propertiesFile = new File("files/app.properties");
		try (FileInputStream fin = new FileInputStream(propertiesFile)) {
			properties.load(fin);
			adminPassword = properties.getProperty("admin.password");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public AdminFacade() {
		super.companyDao = new CompanyDaoDb();
		super.customerDao = new CustomerDaoDb();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		String adminEmail = "admin@admin.com";
		String adminPassword = this.adminPassword;

		if (adminEmail.equals(email) && adminPassword.equals(password)) {
			return true;
		} else {
			throw new CouponFacadeException("invalid username or password");
		}
	}

	/**
	 * Add a company to the system
	 * 
	 * @param company The company to be added
	 * @throws CouponSystemException If an exception was caught while adding company
	 */
	public int addCompany(Company company) throws CouponSystemException {
		try {
			if (companyDao.isCompanyExists(company.getEmail(), company.getPassword())) {
				throw new CouponFacadeException("A company with this email or password already exists");
			} else {
				return companyDao.addCompany(company);
			}
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Update an existing company
	 * 
	 * @param company The company to update and the new details
	 * @throws CouponSystemException If an exception was caught while updating
	 *                               company
	 */
	public void updateCompany(Company company) throws CouponSystemException {
		try {
			companyDao.updateCompany(company);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Remove an existing company
	 * 
	 * @param companyId The company's id
	 * @throws CouponSystemException If an exception was caught while deleting
	 *                               company
	 */
	public void deleteCompany(int companyId) throws CouponSystemException {
		try {
			companyDao.deleteCompany(companyId);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Return a list of all companies.
	 * 
	 * @return A list of all companies
	 * @throws CouponSystemException If an exception was caught while getting all
	 *                               companies
	 */
	public List<Company> getAllCompanies() throws CouponSystemException {
		return companyDao.getCompanies();
	}

	/**
	 * Return a company using id
	 * 
	 * @param companyId The company's id
	 * @return A company object
	 * @throws CouponSystemException If an exception was caught while getting
	 *                               company
	 */
	public Company getCompany(int companyId) throws CouponSystemException {
		return companyDao.getCompany(companyId);
	}

	/**
	 * Add a new customer to the system
	 * 
	 * @param customer The new customer to be added
	 * @throws CouponSystemException If an exception was caught while adding
	 *                               customer
	 */
	public int addCustomer(Customer customer) throws CouponSystemException {
		if (customerDao.isCustomerExists(customer.getEmail(), customer.getPassword())) {
			throw new CouponFacadeException("A customer with this email or password already exists");
		} else {
			try {
				return customerDao.addCustomer(customer);
			} catch (CouponDaoException e) {
				throw new CouponFacadeException(e);
			} catch (SQLException e) {
				throw new CouponFacadeException("failed to rollback changes", e);
			}
		}
	}

	/**
	 * Update an existing customer
	 * 
	 * @param customer The customer to update with new details
	 * @throws CouponSystemException If an exception was caught while updating
	 *                               customer
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException {
		try {
			customerDao.updateCustomer(customer);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Removes a customer from the system
	 * 
	 * @param customerId The customer id
	 * @throws CouponSystemException If an exception was caught while removing
	 *                               customer
	 */
	public void deleteCustomer(int customerId) throws CouponSystemException {
		try {
			customerDao.deleteCustomer(customerId);
		} catch (CouponDaoException e) {
			throw new CouponFacadeException(e);
		} catch (SQLException e) {
			throw new CouponFacadeException("failed to rollback changes", e);
		}
	}

	/**
	 * Return a list of all customers
	 * 
	 * @return A list of all customers
	 * @throws CouponSystemException If an exception was caught while trying to get
	 *                               customers
	 */
	public List<Customer> getAllCustomers() throws CouponSystemException {
		return customerDao.getCustomers();
	}

	/**
	 * Return a customer using id
	 * 
	 * @param customerId The customer id
	 * @return A customer object
	 * @throws CouponSystemException If an exception was caught while getting
	 *                               customer
	 */
	public Customer getCustomer(int customerId) throws CouponSystemException {
		return customerDao.getCustomer(customerId);
	}
}
