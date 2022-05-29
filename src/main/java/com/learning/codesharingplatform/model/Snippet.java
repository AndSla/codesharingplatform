package com.learning.codesharingplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Snippet {

    @JsonIgnore
    @Transient
    private final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";

    @Id
    @JsonIgnore
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();
    private String code;
    private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
    private int time;
    private int views;

    public Snippet() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
