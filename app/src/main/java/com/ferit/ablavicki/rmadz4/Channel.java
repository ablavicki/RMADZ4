package com.ferit.ablavicki.rmadz4;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class Channel {

    @ElementList(name = "item", inline = true) private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }
}

