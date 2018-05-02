package com.ferit.ablavicki.rmadz4;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class News {

    @Element private String category;
    @Element private String title;
    @Element private String description;
    @Element private String link;
    @Element(name = "pubDate") private String date;
    @Element(name = "enclosure" , required = false) private Poster poster;

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public Poster getPoster() {
        return poster;
    }
}
