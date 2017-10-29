package services;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.Users;
import manager.ManagerHelper;

@Path("/customer")
public class CustomerService {

	/**
	 * This function get parameter id from user-interface and send
	 * it to a function that find customer in data base.
	 * @param id
	 * @return Customer.
	 */
	@GET
	@Path("getId")
	public Customer getCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().getCustomer(id);
	}

	/**
	 * This function get name from user-interface and 
	 * send to a function that find name in data base return list.
	 * @param name
	 * @return list of Customer.
	 */
	@GET
	@Path("getByName")
	public List<Customer> getByName(@QueryParam("companyname") String name) {
		return ManagerHelper.getCustomerManager().getByName(name);
	}

	/**
	 * This function get parameters from user-interface and 
	 * send them to database and  create and return the customer with id.
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @param username
	 * @param pass
	 * @return Customer.
	 */
	@GET
	@Path("createCustomer")
	public Customer createCustomer(@QueryParam("companyname") String companyname,
			@QueryParam("companynumber")String companynumber, @QueryParam("contactname")String contactname,
			@QueryParam("email")String email,@QueryParam("phone") String phone,@QueryParam("username")String username,@QueryParam("pass")String pass){
		System.out.println("Customer created -->");
		return ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber, contactname, email, phone,username,pass);
	}

	/**
	 * This function get parameter id from user-interface and delete customer .
	 * @param id
	 * @return Reply.
	 */
	@GET
	@Path("deleteCustomer")
	public Reply deleteCustomer(@QueryParam ("id")int id){
		System.out.println("Customer deleted -->");
		return (Reply)ManagerHelper.getCustomerManager().deleteCustomer(id);
	}
	
	/**
	 * This function get parameters from user-interface and send them to 
	 * a function that update the customer.
	 * @param id
	 * @param companyname
	 * @param companynumber
	 * @param contactname
	 * @param email
	 * @param phone
	 * @param username
	 * @param pass
	 * @return
	 */
	@GET
	@Path("updateCustomer")
		public Reply updateCustomer(@QueryParam("id")int id,@QueryParam("companyname")String companyname,@QueryParam("companynumber")String companynumber,
				@QueryParam("contactname")String contactname,@QueryParam("email")String email,
				@QueryParam("phone")String phone,@QueryParam("username")String username,@QueryParam("pass")String pass){
		System.out.println("Custoner updated -->");
		return (Reply)ManagerHelper.getCustomerManager().updateCustomer(id,companyname, companynumber, contactname, email, phone);
	}
	
	
	/**
	 * This function get parameter status from user-interface and get if the 
	 * status true active projects else get all customers from database.
	 * @param status
	 * @return list of Customer.
	 */
	@GET
	@Path("getCustomerActive")
	public List<Customer> getCustomerStatus(@QueryParam("status") boolean status) {
		if (status ) {
			return (List)ManagerHelper.getCustomerManager().getCustomerByStatus();
		}else{
			return (List)ManagerHelper.getCustomerManager().getAllCustomers();
		}
	}
	
	/**
	 * That function send to a query that gives back
	 *  list of big customer that has
	 * over 2 projects. 
	 * @return list of Customer.
	 */
	@GET
	@Path("getBigCustomer")
	public List<Customer> getBigCustomer(){
		return (List) ManagerHelper.getCustomerManager().getBigCustomers();
	}

}