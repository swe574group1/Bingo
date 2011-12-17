package models;

public enum BadgeType {
	NEW_BEE("New Bee"), BUSY_BEE("Busy Bee"), WORKING_BEE("Working Bee"), BUMBLE_BEE("Bumble Bee");

	public String type;

	private BadgeType(String type) {
		this.type = type;
	}

}
