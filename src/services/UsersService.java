package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Users;
import manager.ManagerHelper;

@Path("/usersService")
public class UsersService {

	/**
	 * This function get parameters from user-interface and 
	 * send them to function that return the User in data base .
	 * @param user
	 * @param pass
	 * @return User.
	 */
	@GET
	@Path("getByUser")
	public Users getByUser(@QueryParam("user") String user, @QueryParam("pass") String pass) {
			return (Users) ManagerHelper.getUsersManager().getByUser(user, pass);
	}
	
	/**
	 * This function get parameter from user-interface and send it
	 * to a function that delete it from data base .
	 * @param id
	 * @return Reply.
	 */
	@GET
	@Path("deleteUser")
	public Reply deleteUser(@QueryParam("id") int id) {
			return ManagerHelper.getUsersManager().deleteUser(id);
	}
	
}
