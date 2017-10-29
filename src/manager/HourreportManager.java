package manager;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.Hourreport;
import entity.Project;
import services.Reply;

public class HourreportManager {

	private final EntityManager entityManager;

	public HourreportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}
	
	/**
	 * This function get parameter id from user-interface 
	 * and find hour report in data base.
	 * @param id
	 * @return
	 */
	public Hourreport getHourreport(Integer id) {
		return entityManager.find(Hourreport.class, id);
	}

	/**
	 * This function create new  hour report. 
	 * @param employee
	 * @param project
	 * @param startdate
	 * @param enddate
	 * @param description
	 * @return Reply.
	 */
	public Hourreport createHourreport(int userId, int project, String date, String starttime, String endtime, String description) {
		
		String sql = "select * from projectmanager.employee e "
				+"inner join projectmanager.users u on e.user = u.id "
				+" where u.id ="+userId;
		
		Employee emp = (Employee)entityManager.createNativeQuery(sql, Employee.class).getSingleResult();
		
		String startdate = date +" "+ starttime;
		String enddate = date +" "+ endtime;
		
		System.out.println(startdate);
		Project pjt = ManagerHelper.getProjectManager().getProject(project);
		
		Hourreport hourreport = new Hourreport(emp, pjt, startdate, enddate, description);
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(hourreport);
			entityManager.getTransaction().commit();
			
			return hourreport;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * This function delete hour report by id from database.
	 * @param id
	 * @return Reply.
	 */
	public Reply deleteHourreport(int id) {
		Hourreport hourreport = getHourreport(id);
		try {

			entityManager.getTransaction().begin();
			entityManager.remove(hourreport);
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
	 * This function gives hour report by  yearAndMonth, employee and customer.
	 * @param yearAndMonth
	 * @param employee
	 * @param project
	 * @param customer
	 * @return List of Hour report.
	 */
	public List<Hourreport> getHourReportByUser(String yearAndMonth, int  employee, int project, int customer) {
			String sql = "SELECT h.id, e.firstname , p.projectname,substring(h.startdate,1,10) as 'date', "+
							" date_format(h.startdate,'%H:%i') Startdate, "+
							" date_format(h.enddate,'%H:%i') Enddate, h.description "+
							" FROM projectmanager.hourreport h "+ 
							" inner join projectmanager.employee e on e.id = h.employee "+
							" inner  join projectmanager.project p on p.id = h.project "+
							" inner join customer c on c.id = p.customer "+
							" where month(h.enddate) = month('"+yearAndMonth+"') and year(h.enddate) = year('"+yearAndMonth+"') " ;
										
					if(employee != 0){
						sql += " and h.employee = " + employee   ;
					}
					
					if( project != 0){
						sql +=" and  h.project =  " + project ;
					}
					
					if(customer != 0){
						sql += " and p.customer =  " + customer ;
					}
			
					sql += " order by h.startdate desc";
					
					
					System.out.println("sql --->  "+sql);
					System.out.println("----->Get hour report manager");
		return (List) entityManager.createNativeQuery(sql, Hourreport.class).getResultList();

		}
						
	/**
	 * This function gives hour report to specific customer from database . 
	 * @param project
	 * @return List of Hour report.
	 */
	public List<Hourreport> getHourReportOfCustomer (int userId ,String yearAndMonth, int project ){
		String sql =" SELECT h.id,e.firstname , p.projectname, substring(h.startdate,1,10) as 'date', "+
							" date_format(h.startdate,'%H:%i') startdate, "+
							" date_format(h.enddate,'%H:%i') enddate, h.description "+
							" FROM projectmanager.hourreport h "+
                            " inner join projectmanager.employee e on e.id = h.employee "+
							 " inner  join projectmanager.project p on p.id = h.project  "+
                             " inner join projectmanager.customer c on c.id = p.customer "+
                             " inner join projectmanager.users u on u.id = c.user "+
							" where month(h.enddate) = month('"+yearAndMonth+"') and year(h.enddate) = year('"+yearAndMonth+"') "+
                           " and c.user ="+userId;
							if( project != 0){
								sql +=" and  h.project =  " + project ;
							}
		return(List)entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}

	/**
	 * This function gives all hour report from database.
	 * @return  List of Hour report.
	 */
	public List<Hourreport> getAllHourReport() {
		String sql = " SELECT h.id , h.employee , h.project , substring(h.startdate,1,10) as 'date', "+
				" date_format(h.startdate,'%H:%i') Startdate, "+
				" date_format(h.enddate,'%H:%i') Enddate , h.description "+
		 "	FROM projectmanager.hourreport h; ";
		return (List) entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}
	
	/**
	 * This function gives hour report list 
	 * @param userId
	 * @param yearAndMonth
	 * @param project
	 * @return list of HourReport.
	 */
	public List<Hourreport> getHourReportOfEmployee(int userId ,String yearAndMonth, int project ){
		System.out.println("project ----> " + project);
		String sql = "select h.id,e.firstname , p.projectname, substring(h.startdate,1,10) as 'date', "+
				" date_format(h.startdate,'%H:%i') Startdate, "+
				" date_format(h.enddate,'%H:%i') Enddate, h.description  from projectmanager.hourreport h"+
		" inner join projectmanager.employee e on e.id = h.employee"+
		" inner join projectmanager.project p on p.id = h.project"+
		" inner join projectmanager.users u on u.id = e.user"+
		" where month(h.enddate) = month('"+yearAndMonth+"') and year(h.enddate) = year('"+yearAndMonth+"') "+
		" and e.user = '"+userId+"'";
	
					if( project != 0){
						sql +=" and  h.project =  " + project ;
					}
				sql += " order by h.startdate desc";
			return entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}
	/**
	 * This function gives seven days back of hour report from database to specific employee.
	 * @param id
	 * @return list of Hourreport .
	 */
	public List<Hourreport> getSevenDaysBackOfEmployee(int id){
		String sql = "select h.id, e.firstname , p.projectname, substring(h.startdate,1,10) as 'date', "+
				" date_format(h.startdate,'%H:%i') Startdate, "+
				" date_format(h.enddate,'%H:%i') Enddate, h.description "+
			" from projectmanager.hourreport h"+
			" inner join projectmanager.employee e on e.id = h.employee"+
			" inner join projectmanager.project p on p.id = h.project"+
			" inner join projectmanager.users u on u.id = e.user"+
			" WHERE h.enddate >= now() - INTERVAL 8 day "+
		    "  AND h.enddate <= current_user() " +
		    "  AND u.id = '"+id+"' order by h.startdate desc";
		return entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}
	
	/**
	 * This function gives month back of hour report from database to specific employee.
	 * @param userId
	 * @return list of Hourreport .
	 */
	public List<Hourreport> getMonthBackOfEmployee(int userId){
		String sql = " select h.id,e.firstname,p.projectname,substring(h.startdate,1,10) as 'date', "+
			" date_format(h.startdate,'%H:%i') Startdate, "+
			" date_format(h.enddate,'%H:%i') Enddate, h.description "+
				" from projectmanager.hourreport h inner join projectmanager.employee e on e.id = h.employee "+ 
				  " inner join projectmanager.project p on p.id = h.project "+
				  " inner join projectmanager.users u on u.id = e.user "+ 
				  " WHERE h.enddate >= now() - INTERVAL 30 day "+  
			       " AND h.enddate <= current_user() "+ 
			      " AND e.user = "+userId+" order by h.startdate desc" ;
		return (List)entityManager.createNativeQuery(sql, Hourreport.class).getResultList();
	}
	
	/**
	 * This function gives month back of hour report from database to specific customer.
	 * @param userId
	 * @return list of Hourreport.
	 */
	public List<Hourreport> getMonthBackOfCustomer( int userId) {
		String sql = 
					" SELECT h.id,e.firstname , p.projectname,substring(h.startdate,1,10) as 'date', "+
							" date_format(h.startdate,'%H:%i') Startdate,"+
							 "date_format(h.enddate,'%H:%i') Enddate ,h.description "+ 
					" FROM projectmanager.hourreport h "+ 
                     " inner join projectmanager.employee e on e.id = h.employee "+
					 " inner  join projectmanager.project p on p.id = h.project  "+
                     " inner join projectmanager.customer c on c.id = p.customer "+
                     " inner join projectmanager.users u on u.id = c.user "+
                     " WHERE h.enddate >= now() - INTERVAL 30 day  "+
					 " AND h.enddate <= current_user() "+
					 "  AND c.user ="+userId+" order by h.startdate desc";
		return (List)entityManager.createNativeQuery(sql, Hourreport.class).getResultList();

	}
}
