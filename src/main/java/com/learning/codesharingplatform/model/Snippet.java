package com.learning.codesharingplatform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMATTER)
    private LocalDateTime date = LocalDateTime.now();

    private int time;
    private int views;

    @OneToOne(mappedBy = "snippet", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private AdditionalData additionalData;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }
}
