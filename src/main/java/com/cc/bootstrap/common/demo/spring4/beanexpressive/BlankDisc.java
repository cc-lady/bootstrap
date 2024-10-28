package com.cc.bootstrap.common.demo.spring4.beanexpressive;

/**
 * @Description: BlankDisc
 * @author: ChenChen
 * @date: 2024-03-01 16:26
 */
public class BlankDisc {
    private String title;
    private String artist;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
