package models;

public class CreditType {
	public double requesterSocialPoint;
	public double offererSocialPoint;
	
	public int forRequester() {
		return (int) requesterSocialPoint;
	}
	
	public int forOfferer() {
		return (int) offererSocialPoint;
	}
}
