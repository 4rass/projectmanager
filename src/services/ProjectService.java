package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.Project;
import manager.ManagerHelper;

@Path("/project")
public class ProjectService {

	/**
	* This function get parameter id from user-interface and send
	 * it to a function that find project in data base.
	 * @param id
	 * @return 
	 */
	@GET
	@Path("getId")
	public Project getProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().getProject(id);
	}

	/**
	 * This function get name from user-interface and 
	 * send to a function that find name of project  in data base return list.
	 * @param name
	 * @return
	 */
	@GET
	@Path("getByName")
	public List<Project> getByName(@QueryParam("projectname") String name) {
		return ManagerHelper.getProjectManager().getByName(name);
	}
	
	/**
	 * This function get parameter from user-interface and send it 
	 * to a function that get list of projects of a specific customer .
	 * @param userId
	 * @return list of Project.
	 */
	@GET
	@Path("getProjectsByCustomer")
	public List<Project> getProjectsByCustomer(@QueryParam("userId") int userId) {
		return ManagerHelper.getProjectManager().getProjectsByCustomer(userId);
	}
	
	/**
	 * This function send the request to a function that return list
	 * of all project from data base .  
	 * @return
	 */
	@GET
	@Path("getAllProjects")
	public List<Project> getAllProjects() {
		return ManagerHelper.getProjectManager().getAllProjects();
	}
	
	/**
	 * That function take parameters from user-interface and send them 
	 * to function that create and return new project with id from data base .  
	 * @param projectname
	 * @param customer
	 * @param customerprojectmanager
	 * @param projectmanageremail
	 * @param projectmanagerphone
	 * @param startdate
	 * @param enddate
	 * @return Project.
	 */
	@GET
	@Path("createProject")
	public Project createProject(@QueryParam("projectname")String projectname,@QueryParam("customer")int customer,
			@QueryParam("customerprojectmanager")String customerprojectmanager,
			@QueryParam("projectmanageremail")String projectmanageremail,
			@QueryParam("projectmanagerphone")String projectmanagerphone,
			@QueryParam("startdate")String startdate, @QueryParam("enddate")String enddate){
		System.out.println("Project created !!! -->");
		
		
		return ManagerHelper.getProjectManager().createProject(projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerphone,startdate,enddate);
	}
	
	/**
	 * This function get parameter id from user-interface and delete 
	 * the project from data base .
	 * @param id
	 * @return Reply.
	 */
	@GET
	@Path("deleteProject")
	public Reply deleteProject(@QueryParam("id")int id) {
		return ManagerHelper.getProjectManager().deleteProject(id);
	}
	/**
	 * This function send the request to a function that return 
	 * list of projects that will end in 30 days.
	 * @return list of Project.
	 */
	@GET
	@Path("getEndingProjects")
	public List<Project> getEndingProjects() {
		return ManagerHelper.getProjectManager().getEndingProjects();
	}
	
	/**
	 * This function get parameters from user-interface and send them to 
	 * a function that update the project.
	 * @param id
	 * @param projectname
	 * @param customer
	 * @param customerprojectmanager
	 * @param projectmanageremail
	 * @param projectmanagerphone
	 * @param startdate
	 * @param enddate
	 * @return Reply.
	 */
	@GET
	@Path("updateProject")
	public Reply updateProject(@QueryParam("id")int id,@QueryParam("projectname")String projectname,@QueryParam("customer")int customer,
			@QueryParam("customerprojectmanager")String customerprojectmanager,
			@QueryParam("projectmanageremail")String projectmanageremail,
			@QueryParam("projectmanagerphone")String projectmanagerphone,
			@QueryParam("startdate")String startdate, @QueryParam("enddate")String enddate){
		System.out.println("Project updated !!! -->");
		return ManagerHelper.getProjectManager().updateProject(id,projectname, customer, customerprojectmanager, projectmanageremail, projectmanagerphone,startdate,enddate);
	}
	/**
	 * This function get parameter from user-interface and send them to 
	 * function that gives list of project of a specific customer.
	 * @param customer
	 * @return list of Project.
	 */
	@GET
	@Path("getActiveProjectByCustomer")
	public List<Project> getActiveProjectByCustomer(@QueryParam("customer")int customer) {
		return ManagerHelper.getProjectManager().getActiveProjectByCustomer(customer);
	}
	
}
