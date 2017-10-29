package manager;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.Users;
import services.Reply;

public class UsersManager {
	private final EntityManager entityManager;

	public UsersManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	/**
	 * This function update the user.
	 * @param user
	 */
	public void update(Users user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	/**
	 * This function create a new user .
	 * @param user
	 * @return User.
	 */
	public Users createUser(Users user) {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();

			return user;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * This function delete user.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteUser(int id) {
		Users users = get(id);
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(users);
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
	 * This function get id of user.
	 * @param id
	 * @return Users.
	 */
	public Users get(Integer id) {
		return entityManager.find(Users.class, id);
	}

	/**
	 * This function gives list that the user and pass there from database.
	 * @param user
	 * @param pass
	 * @return Users.
	 */
	public Users getByUser(String user, String pass) {
		try {
			String sql = "select * from projectmanager.users where user like '" + user + "' and pass like '" + pass
					+ "';";
			return (Users) entityManager.createNativeQuery(sql, Users.class).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This function get user name , and send the user name to database , and 
	 * find by the user name the password and send mail to user mail .
	 * @param username
	 * @return Reply .
	 */
	public Reply getForgottedPassword(String username) {

		try {

			String sql = "SELECT * FROM projectmanager.users u  where user = '" + username + "'";
			Users user = (Users) entityManager.createNativeQuery(sql, Users.class).getSingleResult();

			if (user.getType().equals("employee")) {

				String sql2 = "select * from projectmanager.employee e "
						+ " inner join projectmanager.users u on e.user= u.id " + " where u.user ='" + username + "'";

				Employee employee = (Employee) entityManager.createNativeQuery(sql2, Employee.class).getSingleResult();

				try {
					MailHelper.sendMail(employee.getEmail(), "Forgotted Password?->",
							"User Name : " + user.getUser() + "  ,  " + " Password : "+user.getPass());
				} catch (MessagingException e) {
					e.getMessage();
				}
			} else if (user.getType().equals("customer")) {

				String sql3 = "select * from projectmanager.customer c "
						+ " inner join projectmanager.users u on c.user= u.id " + " where u.user ='" + username + "'";

				Customer customer = (Customer) entityManager.createNativeQuery(sql3, Customer.class).getSingleResult();

				try {
					MailHelper.sendMail(customer.getEmail(), "Forgotted Password?->",
							"User Name : " + user.getUser() + "  ,  " + " Password : "+user.getPass());
				} catch (MessagingException e) {
					e.getMessage();
				}

			} else {

				try {
					MailHelper.sendMail("lior2012.al@gmail.com", "Forgotted Password?->",
							user.getUser() + "  ,  " + user.getPass());
				} catch (MessagingException e) {
					e.getMessage();
				}
			}

			return new Reply();

		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
}
