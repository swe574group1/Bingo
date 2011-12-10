package models;

import play.db.jpa.Model;

public class Comment extends Model
{
    public String text;
    public User user;
    public Offer offer;
    public Request request;
}
