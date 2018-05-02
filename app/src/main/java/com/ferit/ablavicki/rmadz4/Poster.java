package com.ferit.ablavicki.rmadz4;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "enclosure", strict = false)
public class Poster {
    @Attribute private String url;

    public String geturl() {
        return url;
    }
}

