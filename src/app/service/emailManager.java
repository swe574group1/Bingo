package service;
import play.*;
import play.mvc.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.io.IOUtils;

import models.EmailTemplate;
import models.User;

import models.User;
/*
 * Author AliReza
 * send email using google server
 * just set user name and massage 
 * 
 */
 
public class emailManager  {
	
	public void sendEmail(User user,String subject,String msg) throws EmailException, IOException{
    EmailTemplate et=new EmailTemplate();
    et.setMsg(msg);
    String Temp=et.getTemp();
	HtmlEmail email = new HtmlEmail();	
//	SimpleEmail email = new SimpleEmail();
 	email.setFrom("bingowebmail@gmail.com");
 	email.setHostName("smtp.googlemail.com");
	email.setSubject(subject);
 	email.setAuthenticator(new DefaultAuthenticator("bingowebmail@gmail.com", "barselon"));
 	email.setSSL(true);
	email.addTo(user.email);
	email.setSubject("subject");
	email.setHtmlMsg(Temp);
	email.send(); 
	}
 
}

