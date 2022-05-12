package com.learning.codesharingplatform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Snippet {
    private String code;
    private LocalDateTime date;
    private final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";

    public Snippet() {
    }

    public String getCode() {
        return code;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMATTER)
    public LocalDateTime getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
        setDate(LocalDateTime.now());
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonIgnore
    public String getHtmlCode() {
        return "<html><head>" +
                "<title>Code</title>" +
                "</head><body>" +
                "<span id=\"load_date\">" +
                date.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)) +
                "</span>" +
                "<pre id=\"code_snippet\">" +
                code +
                "</pre>" +
                "</body></html>";
    }

    @JsonIgnore
    public String getHtmlForm() {
        return "<html><head>" +
                "<title>Create</title>" +
                "</head><body>" +
                "<textarea id=\"code_snippet\">// write your code here</textarea><br>" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>" +
                "</body></html>";
    }

}
