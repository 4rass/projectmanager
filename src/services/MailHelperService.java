package services;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.MailHelper;

@Path("/MailHelper")
public class MailHelperService {

	/**
	 * this function send the request to a function that
	 *  send error  mail from user-interface  to manager . 
	 */
	@GET
	@Path("sendMail")
	public String sendMail(@QueryParam("subject")String subject,@QueryParam("massage")String massage){
		try {
			MailHelper.sendMail("lior2012.al@gmail.com", subject, massage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return Reply.OK_STR;
		
	}
}
