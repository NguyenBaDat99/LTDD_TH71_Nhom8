package com.example.dictionary.DictionaryRequest;

public class ListItem {

    String title;
    String subtitle;
    int img;
    String content;

    public String getContent() {
        return content;
    }

    public ListItem (String title, String subtitle, int img, String content){
        this.title = title;
        this.subtitle = subtitle;
        this.img = img;
        this.content = content;
    }





    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getImg() {
        return img;
    }
}
