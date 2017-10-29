package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.PropsHelper;

import manager.ManagerHelper;

@Path("/systemSettings")
public class SystemSettingsService {

	/**
	 * This function send the request to function that gives hours 
	 * that manager set .
	 * @return String .
	 */
	@GET
	@Path("getSystemHours")											//function that read the hour and gives u
	public String getHours() {
		String hours = PropsHelper.get("hours");
		return hours;
	}
	
	
	/**
	 * This function get parameter from user-interface and send them 
	 * to a function that send back password to mail .
	 * @param username
	 * @return Reply.
	 */
	@GET
	@Path("getForgottedPassword")
	public Reply getForgottedPassword(@QueryParam("username")String username) {
		return ManagerHelper.getUsersManager().getForgottedPassword(username);
	}	
			
	/**
	 * This function get parameters from user-interface and 
	 * send them to function that set the hours that employee 
	 * can report .		
	 * @param beginHour
	 * @param endHour
	 * @return String.
	 */
	@GET
	@Path("setSystemHours")
	public String setHours(@QueryParam("beginHour")String beginHour,@QueryParam("endHour")String endHour) {
		String timeOfWork = beginHour+","+endHour;
		System.out.println(timeOfWork);
		PropsHelper.set("hours",timeOfWork);
		return Reply.OK_STR;
	}	
			
	@GET
	@Path("setSystemDays")
	public String setDays(@QueryParam("sunday")String sunday,
			@QueryParam("monday")String monday,
			@QueryParam("tuesday")String tuesday,
			@QueryParam("wednesday")String wednesday,
			@QueryParam("thursday")String thursday,
			@QueryParam("friday")String fridaye,
			@QueryParam("saturday")String saturday ) {
		
		PropsHelper.set("days"," "+sunday+" , "+monday+" , "+tuesday+" , "+wednesday+" , "+thursday+" , "+fridaye+" , "+saturday+" ");
		return Reply.OK_STR;
	}	
	
	
	@GET
	@Path("getSystemDays")											//function that read the hour and gives u
	public String getDays() {
		String days = PropsHelper.get("days");
		return days;
	}
}
