package app.core.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.exceptions.CouponServiceException;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CustomerRepository;

/**
 * Manages administrator interaction with the app.
 * 
 * @see ClientFacade
 */
@Service
@Transactional
@Scope("prototype")
public class AdminService extends ClientService {

	@Value("${admin.password:admin}")
	String adminPassword;

	@Autowired
	public AdminService(CompanyRepository companyRepo, CustomerRepository customerRepo) {
		super.companyRepo = companyRepo;
		super.customerRepo = customerRepo;
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
			throw new CouponServiceException("invalid username or password");
		}
	}

	/**
	 * Add a company to the data storage.
	 * 
	 * @param company The company to be added.
	 * @return The company's id.
	 * @throws CouponSystemException
	 */
	public int addCompany(Company company) throws CouponSystemException {
		// check if a company with this name or email already exists
		if (companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
			company = companyRepo.save(company);
			return company.getId();
		} else {
			throw new CouponServiceException("company with this name or email " + company.getName() + " : "
					+ company.getEmail() + " already exists");
		}
	}

	/**
	 * Update a given company's details.
	 * 
	 * @param company The new company.
	 * @throws CouponSystemException
	 */
	public void updateCompany(Company company) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(company.getId());
		if (opt.isPresent()) {
			Company other = opt.get();
			other.setEmail(company.getEmail());
			other.setPassword(company.getPassword());
			other.setCoupons(company.getCoupons());
		} else {
			throw new CouponServiceException("company with id " + company.getId() + " not found");
		}
	}

	/**
	 * Remove a company from the data storage using id.
	 * 
	 * @param companyId The company's id.
	 * @throws CouponSystemException
	 */
	public void deleteCompany(int companyId) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			companyRepo.delete(opt.get());
		} else {
			throw new CouponServiceException("company with id " + companyId + " not found");
		}
	}

}
