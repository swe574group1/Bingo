package models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.data.validation.MaxSize;
import play.db.jpa.Model;

import com.freebase.api.Freebase;
import com.freebase.json.JSON;

import static com.freebase.json.JSON.o;
import static com.freebase.json.JSON.a;

@Entity
public class Tag extends Model {

    @ManyToOne
    @Required
    public Offer offer;

    @ManyToOne
    @Required
    public Request request;
    
    /**
     * Required in order to differentiate tags and for
     * further calls on the Freebase API.
     */
    @Required
    @Unique
    @MaxSize(40)
    public String freebaseId;
    
    /**
     * Name of the tag. It is the value that
     * end-users see.
     */
    public String name;

    /**
     * This field seems to be useless; but there should
     * be a discussion before deleting it.
     * 
     * @deprecated
     */
    public Boolean is_offer;
    
    /**
     * This field seems to be useless; but there should
     * be a discussion before deleting it.
     * 
     * @deprecated
     */
    public Boolean is_request;
    
    public Tag(Offer offer, String freebaseId, String name) {
    	this.offer = offer;
    	this.freebaseId = freebaseId;
    	this.name = name;
	}

    public Tag(Request request, String freebaseId, String name) {
		this.request = request;
		this.freebaseId = freebaseId;
		this.name = name;
    }
    
    @Override
    public String toString() {
    	return name;
    }
    
    /**
     * Given a keyword; connects to Freebase, queries
     * for possible topics and returns them as a
     * JSON string.
     * 
     * @param keyword	query string
     * @return			the JSON formatted results
     * @see				String
     * @see				Freebase
     * @see				JSON
     * @since			0.2
     */
    public static String fetch(String keyword) {
    	// create the Freebase connection.
    	Freebase freebase = Freebase.getFreebase();
    	
    	// run the query agains Freebase.
    	JSON response = freebase.search(keyword);
    	
    	// return the result.
    	return response.get("result").toJSONString();
    }

}
