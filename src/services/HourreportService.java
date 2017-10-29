package services;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Hourreport;
import manager.ManagerHelper;

@Path("/hourreport")
public class HourreportService {

	/**
	 *  This function get parameter id from user-interface and send
	 * it to a function that find hourreport in data base.
	 * @param id
	 * @return HourReport.
	 */
	@GET
	@Path("getId")
	public Hourreport getHourreport(@QueryParam("id") int id) {
		return ManagerHelper.getHourreportManager().getHourreport(id);
	}

	/**
	 * This function get parameters from user-interface and send 
	 * to function that return list of Hourreport . 
	 * @param yearAndMonth
	 * @param employee
	 * @param project
	 * @param customer
	 * @return Hourreport.
	 */
	@GET
	@Path("getHourReportByUser")// By YeaarAndMonth , employee , project , customer!!!!!!
	public List<Hourreport> getHourReportByUser(@QueryParam("yearAndMonth")String yearAndMonth, @QueryParam("employee")int employee,
			@QueryParam("project")int project, @QueryParam("customer")int customer) {
				
		return ManagerHelper.getHourreportManager().getHourReportByUser(yearAndMonth,employee,project,customer);
		
	}

	/**
	 * This function get parameters from user-interface and 
	 * send them to database and  create and return the Hourreport with id.
	 * @param userId
	 * @param project
	 * @param date
	 * @param starttime
	 * @param endtime
	 * @param description
	 * @return Hourreport.
	 */
	@GET
	@Path("createHourreport")
	public Hourreport createHourreport(@QueryParam("employee")int  userId ,
			@QueryParam("project")  int project, @QueryParam("date") String date ,
			@QueryParam("starttime") String starttime ,@QueryParam("endtime") String endtime,
			@QueryParam("description")String description ) {
		return ManagerHelper.getHourreportManager().createHourreport(userId,project,date,starttime,endtime, description);
	}
	
	/**
	 * This function get parameter id from user-interface and delete hourreport .
	 * @param id
	 * @return Reply.
	 */
	@GET
	@Path("deleteHourrepot")
	public Reply deleteHourrepot(@QueryParam("id") int id) {
		return ManagerHelper.getHourreportManager().deleteHourreport(id);
	}
	

	/**
	 * This function send the request to function that gives all of hour reports.
	 * @return
	 */
	@GET
	@Path("getAllHourReport")
	public List<Hourreport> getAllHourReport() {
		return ManagerHelper.getHourreportManager().getAllHourReport();
	} 
	
	/**
	 * This function get parameters from user-interface ,
	 * send them to function that gives hour report of specific employee. 
	 * @param userId
	 * @param yearAndMonth
	 * @param project
	 * @return list of Hourreport.
	 */
	@GET
	@Path("getHourReportOfEmployee")
	public List<Hourreport> getSevenDaysOfEmployee(@QueryParam("userId")int userId,
			@QueryParam("yearAndMonth")String yearAndMonth,
			@QueryParam("project")int project) {
		return ManagerHelper.getHourreportManager().getHourReportOfEmployee(userId,yearAndMonth,project);
	} 
	
	/**
	 * This function get parameter from user-interface 
	 * and send it to function that return seven day report of specific employee.
	 * @param id
	 * @return list of Hourreport.
	 */
	@GET
	@Path("getSevenDaysBackOfEmployee")
	public List<Hourreport> getSevenDaysBackOfEmployee(@QueryParam ("id")int id) {
		return ManagerHelper.getHourreportManager().getSevenDaysBackOfEmployee(id);
	} 
	
	/**
	 * This function get parameter from user-interface and send it to function 
	 * that return list month back of hour report of a specific employee. 
	 * @param userId
	 * @return list of Hourreport.
	 */
	@GET
	@Path("getMonthBackOfEmployee")
	public List<Hourreport> getMonthBackOfEmployee(@QueryParam ("userId")int userId) {
		return ManagerHelper.getHourreportManager().getMonthBackOfEmployee(userId);
	} 
	
	/**
	 * This function get parameters from user-interface and send them to a function
	 * that return list of hourreport of a specific customer .
	 * @param userId
	 * @param yearAndMonth
	 * @param project
	 * @return list of Hourreport.
	 */
	@GET
	@Path("getHourReportOfCustomer")//Hour report for Customer!
	public List<Hourreport> getHourReportOfCustomer(@QueryParam("userId")int userId,
			@QueryParam("yearAndMonth")String yearAndMonth,
			@QueryParam("project")int project){
		return (List)ManagerHelper.getHourreportManager().getHourReportOfCustomer(userId, yearAndMonth, project);
	}
	
	/**
	 * This function get parameter from user-interface and send them
	 * to function that return month back of a specific customer .
	 * @param userId
	 * @return
	 */
	@GET
	@Path("getMonthBackOfCustomer")
	public List<Hourreport> getMonthBackOfCustomer(@QueryParam ("userId")int userId) {
		return ManagerHelper.getHourreportManager().getMonthBackOfCustomer(userId);
	} 
	
	
}
