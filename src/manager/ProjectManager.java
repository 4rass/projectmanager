	package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Project;
import services.Reply;

public class ProjectManager {

	/**
	 * entity manager 
	 */
	private final EntityManager entityManager;

	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	/**
	 * This function find project by id from database .
	 * @param id
	 * @return Project.
	 */
	public Project getProject(Integer id) {
		return entityManager.find(Project.class, id);
	}
	
	/**
	 * This function update the project.
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
	public Reply updateProject(int id,String projectname, Integer customer,String 	customerprojectmanager ,String projectmanageremail,String projectmanagerphone,String startdate,String enddate) {
		
		Customer customer1 = ManagerHelper.getCustomerManager().getCustomer(customer);
		
		Project project = new Project(id,projectname,customer1,customerprojectmanager,projectmanageremail,projectmanagerphone,startdate,enddate);
		try{
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
		System.out.println("Project ---> updated");
		return new Reply();
	
		} catch (Exception e){
			Reply r  = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	/**
	 * This function create new project .
	 * @param projectname
	 * @param customer
	 * @param customerprojectmanager
	 * @param projectmanageremail
	 * @param projectmanagerphone
	 * @param startdate
	 * @param enddate
	 * @return New Project with customer and with id from database.
	 */
	public Project createProject(String projectname, Integer customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerphone, String startdate, String enddate) {

		Customer customer1 = ManagerHelper.getCustomerManager().getCustomer(customer);
		
		Project project = new Project(projectname, customer1, customerprojectmanager, projectmanageremail,
				projectmanagerphone, startdate, enddate);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(project);
			entityManager.getTransaction().commit();

			return project;
		} catch (Exception ex) {
			return null;

		}
	}

	/**
	 * This function delete Project by id from database.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteProject(int id) {

		Project project = getProject(id);

		try {
			entityManager.getTransaction().begin();
			entityManager.remove(project);
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
	 * This function find project by name from database.
	 * @param name
	 * @return List of Project.
	 */
	public List<Project> getByName(String name) {
		String sql = "select * from projectmanager.project where projectname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Project.class).getResultList();
	}

	/**
	 * This function gives all projects from database. 
	 * @return List of Project.
	 */
	public List<Project> getAllProjects() {
		String sql = "SELECT p.id, p.projectname, p.customer, p.customerprojectmanager, p.projectmanageremail, "+
					" p.projectmanagerphone,substring(p.startdate,1,10) as 'startdate', substring(p.enddate,1,10) as 'enddate' "+
					" FROM projectmanager.project p;";
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

	/**
	 * This function gives the active projects by customer user id.
	 * @param customer
	 * @return List of Project.
	 */
	public List<Project> getActiveProjectByCustomer(int customer){
		String sql = "SELECT p.id, p.projectname , p.customer, p.customerprojectmanager , p.projectmanageremail , p.projectmanagerphone "+
				" ,substring(p.startdate,1,10) as 'startdate' , substring(p.enddate,1,10) as 'enddate' "+
			" from projectmanager.project p"+
			" inner join projectmanager.customer c on c.id = p.customer"+
			" inner join projectmanager.users u on u.id = c.user"+
			" where p.enddate >= current_date()-1" +
            " and u.id = '"+customer+"'";
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	
	/**
	 * This function get the projects that will be end in 14 days from current date.
	 * @return
	 */
	public List<Project> getEndingProjects() {
		String sql = "SELECT p.id, p.projectname, p.customer, p.customerprojectmanager, p.projectmanageremail,p.projectmanagerphone, "+
					" substring(p.enddate,1,10) as 'enddate',substring(p.startdate,1,10) as 'startdate' FROM projectmanager.project p WHERE p.enddate  BETWEEN now() AND date_add(now(),interval +30 day)";
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	
	/**
	 * This function get projects of specific customer .
	 * @param userId
	 * @return list of Projects.
	 */
	public List<Project> getProjectsByCustomer(int userId) {
		String sql = "SELECT p.id,p.projectname "+
					 " FROM projectmanager.project p "+
					" inner join projectmanager.customer c on c.id = p.customer "+
					" inner join projectmanager.users u on u.id = c.user "+
					" where u.id ="+userId;
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
}
