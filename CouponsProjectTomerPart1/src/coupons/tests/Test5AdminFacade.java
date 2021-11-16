package coupons.tests;

import coupons.core.beans.Customer;
import coupons.core.facade.AdminFacade;

public class Test5AdminFacade {

	public static void main(String[] args) {
		try {
			AdminFacade admin = new AdminFacade();
			// login test
//	    System.out.println(admin.login("admin@admin.com", "admin"));

			// add test
//	    Company company = new Company("aaa", "aaa@mail", "aaapass");
//	    admin.addCompany(company);

			// update test
//	    admin.updateCompany(company);

			// delete test
//	    admin.deleteCompany(7);
			// get all companies test
//	    List<Company> companies = admin.getAllCompanies();
//	    System.out.println(companies);

			// get company test
//	    Company company = admin.getCompany(12);
//	    System.out.println(company);

			// add customer test
//	    Customer customer = new Customer("aa", "bb", "ppp@mail", "aabbpass");
//	    admin.addCustomer(customer);

			// update customer test
//	    Customer customer = new Customer(3, "bb", "cc", "bbcc@mail", "bbccpass");
//	    admin.updateCustomer(customer);

			// delete customer test
//	    admin.deleteCustomer(5);
			// get all customers test
//	    List<Customer> customers = admin.getAllCustomers();
//	    System.out.println(customers);

			// get customer test
			Customer customer = admin.getCustomer(3);
			System.out.println(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
