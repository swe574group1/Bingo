package models;

import java.util.List;

public enum BadgeType {
	//NEW_BEE("New Bee"), BUSY_BEE("Busy Bee"), WORKING_BEE("Working Bee"), BUMBLE_BEE("Bumble Bee");
	NEW_BEE("New Bee"), Auto_biographer ("Autobiographer "), Comentator ("Comentator "), Serviceman ("Serviceman "),Fivester  ("Fivester  "), Guru  ("Guru "), Social ("Social"),Rater ("Rater   "), Populist   ("Populist");

	public List<Object[]> typeO;
	
	public String type;

	private BadgeType(String type) {
		this.type = type;
	}


	
	

}
