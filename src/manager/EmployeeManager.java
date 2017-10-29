package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.Users;
import services.Reply;

public class EmployeeManager {

	/**
	 * entity manager 
	 */
	private final EntityManager entityManager;

	public EmployeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	/**
	 * This function update the employee.
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param phone
	 * @return Reply.
	 */
	public Reply updateEmployee(int id, String firstname, String lastname, String email, String phone) {
		
		Employee employee = new Employee(id,firstname,lastname,email,phone);

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
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
	 * This function create new employee.
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param phone
	 * @param username
	 * @param pass
	 * @return New Employee with user name and password and type with id from database.
	 */
	public Employee createEmployee(String firstname, String lastname, String email, String phone, String username,
			String pass) {

		Users users = new Users();
		users.setUser(username);
		users.setPass(pass);
		users.setType("employee");

		users = ManagerHelper.getUsersManager().createUser(users);

		if (users != null) {
			Employee employee = new Employee(firstname, lastname, email, phone, users);

			try {
				entityManager.getTransaction().begin();
				entityManager.persist(employee);
				entityManager.getTransaction().commit();
				System.out.println("Creating new Employee -- > ");
				return employee;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * This function delete employee by id from data base.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteEmployee(int id) {
		Employee employee = getEmployee(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(employee.getUser());
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
	 * * This function find employee by id from database.
	 * @param id
	 * @return Employee.
	 */
	public Employee getEmployee(Integer id) {
		return entityManager.find(Employee.class, id);
	}
	
	/**
	 * * This function find employee by name from database.
	 * @param name
	 * @return List of employees.
	 */
	public List<Employee> getByName(String name) {
		String sql = "select * from employee where firstname like '";
		return (List) entityManager.createNativeQuery(sql + name + "'", Employee.class).getResultList();
	}
	
	/**
	 * This function gives all employees from database.
	 * @return
	 */
	public List<Employee> getAllEmployees() {
		String sql = "Select * FROM projectmanager.employee ";
		return (List) entityManager.createNativeQuery(sql, Employee.class).getResultList();
	}

}
