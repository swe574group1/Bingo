package service;

import java.util.ArrayList;
import java.util.List;

import models.Handshake;
import models.Offer;
import models.CreditType;
import models.Service;

public class CreditManager {
	private static int BasePoint = 100;
	private static int AttendeePoint = 5;
	private static int numberOfOfferers = 1;
	private static int numberOfRequesters = 1;
	private static double duration = 0; 

	
	//3 Overloads for Handshake, Offer and Request
		//Handshake
    public static CreditType getService(Service s) {
    	// Validate Handshake if handshake is not appropriate for point calculation return error
    			// katılımcı yoksa bu yanlış diyip hata vermeli
    			// duration yoksa hata dönecek    	
    	
    	// These parameters should be get from Handshake after MultipleAttendee implemented
    	//numberOfOfferers = 1;
    	//numberOfRequesters = 1;
    	duration = s.getDuration();
    	
    	CreditType ct = CalculateCredit();
    	return ct;
    }
    
    public static CreditType CalculateCredit(){
    	CreditType ct = new CreditType();
    	
    	if (duration > 0)
    	{
    		if (numberOfOfferers > 0 && numberOfRequesters > 0)
    		{
    			//S = (B + (A * (N-1))) * D
    			double SocialPoint = (BasePoint + (AttendeePoint * (numberOfRequesters - 1))) * duration;
    			ct.offererSocialPoint = SocialPoint / numberOfOfferers;
    			ct.requesterSocialPoint = SocialPoint / numberOfRequesters;
    		}
    	}
	
		return ct;
	}
}
