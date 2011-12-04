package service;
import java.util.ArrayList;
import java.util.List;

import models.Tag;


public class Utils
{
    private static final String TAG_SEPARATOR = "[ ,]";

    public static List<String> parseTags(String tags)
    {
        List<String> tagsList = new ArrayList<String>();
        if (tags == null) {
            return tagsList;
        }

        String[] tagsArr = tags.split(TAG_SEPARATOR);
        for (String tagString : tagsArr) {
            tagsList.add(tagString.trim().toLowerCase());
        }
        return tagsList;
    }
}
