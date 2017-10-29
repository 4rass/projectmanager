package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.EmployeeProject;
import manager.ManagerHelper;

@Path("/employeeProject")
public class EmployeeProjectService {

	/**
	 * This function get parameters from user-interface and 
	 * send them to database and  create and return the employeeProject with id.
	 * @param employee
	 * @param project
	 * @return EmployeeProject.
	 */
	@GET
	@Path("createEmployeeProject")
	public EmployeeProject createEmployeeProject (@QueryParam("employee")int employee, @QueryParam("project")int project){
		System.out.println("creat createEmployeeProject ---> !SERVICE!");
		return (EmployeeProject)ManagerHelper.getEmployeeProjectManager().createEmployeeProject(employee, project);
	}
	@GET
	@Path("getListOfgetListOfProjectsAndEmployeesForCustomers")
	public List<EmployeeProject> getListOfgetListOfProjectsAndEmployeesForCustomers (@QueryParam("id")int id){
		return ManagerHelper.getEmployeeProjectManager().getListOfProjectsAndEmployeesForCustomers(id);
	}
	
	
	@GET
	@Path("getListOfProjectsAssocoateToCustomer")
	public List<EmployeeProject>getListOfProjectsAssocoateToCustomer(@QueryParam("id")int id){
		return ManagerHelper.getEmployeeProjectManager().getListOfProjectsAssocoateToCustomer(id);
	}
	/**
	 * This function get parameter from user-interface
	 * and send it to function that send them to data base 
	 * and return list that linked to specific customer.
	 * @param id
	 * @return EmployeeProject.
	 */
	@GET
	@Path("getListOfProjects")
	public List<EmployeeProject>  getListOfProjects( @QueryParam("id")int id){
		return (List)ManagerHelper.getEmployeeProjectManager().getListOfProjects(id);
	}
	
	@GET
	@Path("getListOfProjectsAndEmployees")
	public List<EmployeeProject>  getListOfProjectsAndEmployees( @QueryParam("id")int id){
		return (List)ManagerHelper.getEmployeeProjectManager().getListOfProjectsAndEmployees(id);
	}
}

