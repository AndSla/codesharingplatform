package com.learning.codesharingplatform;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Snippet {
    private String code;

    public Snippet() {
    }

    public String getCode() {
        return code;
    }

    @JsonIgnore
    public String getHtmlCode() {
        return "<html>\n" +
                "<head>\n" +
                "<title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<pre>\n" +
                code +
                "</pre>\n" +
                "</body>\n" +
                "</html>";
    }

    public void setCode(String code) {
        this.code = code;
    }

}
