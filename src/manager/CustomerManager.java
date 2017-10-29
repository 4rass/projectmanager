package manager;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Project;
import entity.Users;
import services.Reply;

public class CustomerManager {

	/**
	 * entity manager 
	 */
	private final EntityManager entityManager;

	
	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);

	}

	/**
	 * This function update the customer.
	 * 
	 * @param id
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @return Reply.
	 */
	public Reply updateCustomer(int id, String companyname, String companynumber, String contactname, String email,
			String phone) {

		Customer customer = new Customer(id, companyname, companynumber, contactname, email, phone);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
			entityManager.getTransaction().commit();
			System.out.println("in transaction customer--->");
			return new Reply();
		} catch (Exception ex) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(ex.getMessage());
			return r;
		}
	}

	/**
	 * This function create new customer.
	 * 
	 * @param id
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @param username
	 * @param pass
	 * @return New Customer with user name and password and type with id from database.
	 */
	public Customer createCustomer(String companyname, String companynumber, String contactname, String email,
			String phone, String username, String pass) {

		Users users = new Users();
		users.setUser(username);
		users.setPass(pass);
		users.setType("customer");

		users = ManagerHelper.getUsersManager().createUser(users);

		System.out.println(users);

		if (users != null) {
			Customer customer = new Customer(companyname, companynumber, contactname, email, phone, users);

			try {
				entityManager.getTransaction().begin();
				entityManager.persist(customer);
				entityManager.getTransaction().commit();
				return customer;
			} catch (Exception ex) {
				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * This function delete customer by id from database.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteCustomer(int id) {
		Customer customer = getCustomer(id);

		try {
			entityManager.getTransaction().begin();
			entityManager.remove(customer.getUser());
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception ex) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(ex.getMessage());
			return r;
		}
	}

	/**
	 * This function find customer by id from database.
	 * @param id
	 * @return Customer. 
	 */
	public Customer getCustomer(Integer id) {
		return entityManager.find(Customer.class, id);
	}

	/**
	 * This function find customer by name from database.
	 * @param name
	 * @return List of customers. 
	 */
	public List<Customer> getByName(String name) {
		String sql = "select * from customer where companyname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Customer.class).getResultList();
	}

	/**
	 * This function gives the customers with 2 or more projects from database.
	 * @return List of customers.
	 */
	public List<Customer> getBigCustomers() {
		String sql = "SELECT c.id,c.companyname,c.companynumber,c.contactname,c.email,c.phone,COUNT(p.customer) AS 'projectcount' FROM projectmanager.customer c ";
		String sql2 = " INNER JOIN projectmanager.project p ON c.id = p.customer WHERE(SELECT  COUNT(p.customer) FROM projectmanager.project)GROUP BY c.id HAVING COUNT(p.customer) >= 2";
		return (List) entityManager.createNativeQuery(sql + sql2, Customer.class).getResultList();
	}

	/**
	 * This function gets customers with status active or not active.
	 * @return List of customers.
	 */
	public List<Customer> getCustomerByStatus() {
		String sql1 = "SELECT p.id, p.projectname,c.companyname,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerphone, "+
						" p.startdate,p.enddate,TRUE AS 'isActive' FROM projectmanager.project p INNER JOIN "+
						" projectmanager.customer c ON c.id = p.customer "+
						" WHERE (CURRENT_DATE() BETWEEN p.startdate AND p.enddate) > 0";
		return (List)entityManager.createNativeQuery(sql1, Customer.class).getResultList();
	}
	
	/**
	 * This function gives all customers from database.
	 * @return List of customers.
	 */
	public List<Customer> getAllCustomers() {
		String sql = "SELECT c.id, c.companyname , c.companynumber , c.contactname, c.email , c.phone , c.user  "+
					" FROM projectmanager.customer c";
		return (List)entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}
	
}
