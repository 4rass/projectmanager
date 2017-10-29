package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Employee;
import manager.ManagerHelper;

@Path("/employee")
public class EmloyeeServices {
	
	/**
	* This function get parameter id from user-interface and send
	 * it to a function that find employee in data base.
	 * @param id
	 * @return Employee.
	 */
	@GET 
	@Path("getId")
	public Employee getEmploee(@QueryParam("id")int id ){
		return ManagerHelper.getEmployeeManager().getEmployee(id);
	}
	/**
	* This function get name from user-interface and 
	 * send to a function that find name in data base return list.
	 * @param name
	 * @return list of Employee.
	 */
	@GET
	@Path("getByName")
	public List <Employee> getByName(@QueryParam("firstname")String name ){
		return ManagerHelper.getEmployeeManager().getByName(name);
	}

	/**
	 * This function get all employees
	 *  from data base and return list.
	 * @return list of Customer.
	 */
	@GET 
	@Path("getAllEmployees")
	public List <Employee> getAllEmployees(){
		return ManagerHelper.getEmployeeManager().getAllEmployees();
	}
	
	/**
	 * This function get parameters from user-interface and 
	 * send them to database and  create and return the employee with id.
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param phone
	 * @param username
	 * @param pass
	 * @return Employee.
	 */
	@GET
	@Path("creatNewEmployee")
	public Employee creatNewEmployee(@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,
			@QueryParam("email")String email,
					@QueryParam("phone")String phone,
					@QueryParam("username")String username,
					@QueryParam("pass")String pass){
		System.out.println("-->create employee");
		return ManagerHelper.getEmployeeManager().createEmployee(firstname, lastname, email, phone, username,pass);
	}
	
	/**
	 * This function get parameter id from user-interface and delete 
	 * the employee from data base .
	 * @param id
	 * @return Reply.
	 */
	@GET
	@Path("deleteEmployee") 
	public Reply deleteEmployee(@QueryParam("id")int id){
		System.out.println("-->delete employee");
		return ManagerHelper.getEmployeeManager().deleteEmployee(id);
	}
	
	/**
	 * This function get parameters from user-interface
	 *  and send them to database 
	 * that update the employee
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param phone
	 * @return Reply.
	 */
	@GET
	@Path("updateEmployee")
	public Reply updateEmployee(@QueryParam("id")int id,@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,@QueryParam("email")String email,
					@QueryParam("phone")String phone){
		System.out.println("-->update employee");
		return ManagerHelper.getEmployeeManager().updateEmployee( id,firstname, lastname, email, phone);
	}
}
