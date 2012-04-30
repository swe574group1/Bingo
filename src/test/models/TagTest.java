package models;

import org.junit.*;

import play.test.UnitTest;

public class TagTest extends UnitTest {
	
	@Test
	public void freebaseQueriesShouldWork() {
		assertNotNull(Tag.fetch("Joe Satriani"));
	}

}
