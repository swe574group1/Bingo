package controllers;

import java.util.ArrayList;
import java.util.List;

import models.BadgeImage;
import models.BadgeType;
import models.Handshake;
import models.Handshake.Status;
import models.User;

public class Swe extends BaseController
{   
	static String imageurl=null;

    public static void badges()
	{	
    	List Imagelist = new ArrayList();

		Badge badge=new Badge();
		BadgeImage image = null;
		BadgeType badges=badge.getbadgelist();
		if(badges.toString()=="NEW_BEE"){
			image=new BadgeImage();
			image.setNewbee();
			Imagelist.add(image);
				
		}
		
	

		//we should render the list of image
	
    	render(badges,image);
	}
    
    public static void aboutbadge() {
  		render();
      }
    

    public static void register() {
		render();
    }

    public static void about() {
	render();
    }

    public static void faq() {
	render();
    }

    public static void contact() {
	render();
    }
    
    public static void termsOfService() {
	render();
    }

    public static void showUserPhoto(Long userId) {
	User user = User.findById(userId);
	renderBinary(user.photo.get());
    }

}
