package coupons.tests;

import coupons.core.beans.Customer;
import coupons.core.dao.CustomerDao;
import coupons.core.dao.CustomerDaoDb;

public class Test3CustomerDao {
	public static void main(String[] args) {
		try {
			CustomerDao customerDao = new CustomerDaoDb();
			// add test
			Customer customer = new Customer("aaa", "bbb", "aabb@mail", "aabbpass");
			int customerId = customerDao.addCustomer(customer);
			System.out.println("customer added with id " + customerId);
			// find test
//	    System.out.println(customerDao.isCustomerExists("hocuspocus@mail", "hocuspocuspass"));
			// update test
//	    customerDao.updateCustomer(customer);
			// get test
//	    System.out.println(customerDao.getCustomer(2));
			// delete test
//	    customerDao.deleteCustomer(2);
			// get all companies test
//	    System.out.println(customerDao.getCustomers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
