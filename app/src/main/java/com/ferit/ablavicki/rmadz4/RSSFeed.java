package com.ferit.ablavicki.rmadz4;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class RSSFeed {

    @Element private Channel channel;

    public Channel getChannel() {
        return channel;
    }
}

/*
<rss version="2.0">
    <channel>
        <title>bug.hr</title>
        <description>Sve objave na bug.hr</description>
        <language>hr</language>
        <copyright>Copyright 2003-2018 Bug d.o.o. &amp; dizzy.hr</copyright>
        <image>
            <url>http://www.bug.hr/Assets/images/rss.png</url>
            <link>http://www.bug.hr</link>
            <title>bug.hr</title>
        </image>
        <ttl>30</ttl>
        <link>http://www.bug.hr</link>
        <item>
            <guid isPermaLink="true">http://www.bug.hr/kucista/deepcool-predstavio-new-ark-90-
            electro-limited-edition-kuciste-4076</guid>
            <link>http://www.bug.hr/kucista/deepcool-predstavio-new-ark-90-electro-limited-edition-
            kuciste-4076</link>
            <category>Kućišta</category>
            <title>DeepCool predstavio New Ark 90 Electro, Limited Edition kućište</title>
            <description>DeepCool je predstavio novo E-ATX kućište – New Ark 90 u Electro Limited
            Edition izdanju koje stiže s već ugrađenim vodenim hlađenjem</description>
            <pubDate>Wed, 02 May 2018 16:00:00 Z</pubDate>
            <enclosure length="12345" type="image/jpeg" url="http://www.bug.hr/img/deepcool-
            predstavio-new-ark-90-electro-limited-edition-kuciste_t5ylSG.jpg" />
        </item>
        <item>
        .
        .
        .
    </channel>
</rss>
 */

