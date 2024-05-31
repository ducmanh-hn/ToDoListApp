package com.todolist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private int id;
    private String content;

    @SerializedName("status")
    private boolean check;

    public Task() {
    }

    public Task(int id, String content, boolean check) {
        this.id = id;
        this.content = content;
        this.check = check;
    }

    public Task(String content, boolean check) {
        this.content = content;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", check=" + check +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getContent(), task.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }
}
