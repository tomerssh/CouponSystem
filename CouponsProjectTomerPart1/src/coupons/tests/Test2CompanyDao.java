package coupons.tests;

import coupons.core.beans.Company;
import coupons.core.dao.CompanyDaoDb;

public class Test2CompanyDao {
	public static void main(String[] args) {
		try {
			CompanyDaoDb companyDao = new CompanyDaoDb();
			// add test
			Company company = new Company("aaa", "aaa@mail", "aaapass");
			int companyId = companyDao.addCompany(company);
//	    System.out.println("company added with id " + companyId);
			// find test
//	    System.out.println(companyDao.isCompanyExists("aaa@mail", "aaapass"));
			// update test
//	    companyDao.updateCompany(company);
			// get test
//	    System.out.println(companyDao.getCompany(4));
			// delete test
//	    companyDao.deleteCompany(8);
			// get all companies test
//	    System.out.println(companyDao.getCompanies());
			// get company id test
//			System.out.println(companyDao.getCompanyId("aaa@mail", "aaapass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
