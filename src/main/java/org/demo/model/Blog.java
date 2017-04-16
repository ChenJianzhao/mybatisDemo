package org.demo.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by pc on 2017/4/13.
 */
public class Blog extends CoreBase{

    public String title;

    public String content;

    public Timestamp date;

    public User author;

    public List comments;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "title: " + title + ",content: [" + content + "]" + ",Date: " + date
                + "Author :" + author.getUsername();
    }
}
