package com.learning.codesharingplatform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Snippet {
    private String code;
    private String date;

    public Snippet() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
        setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER)));
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
