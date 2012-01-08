package service;

import java.util.List;

import models.Tag;

public interface Matchable
{
    List<Tag> getTags();
    String getDescription();
}
