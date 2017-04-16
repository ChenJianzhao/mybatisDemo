package org.demo.model;

/**
 * Created by jzchen on 2017/4/16.
 */
public class Comment extends CoreBase {

    public String title;

    public String content;

    public String date;

    public User author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " | " + content + " | " + date;
    }
}
