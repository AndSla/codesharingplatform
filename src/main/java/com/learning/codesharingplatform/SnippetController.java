package com.learning.codesharingplatform;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnippetController {
    private final Snippet snippet = new Snippet();

    {
        snippet.setCode("public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n");
    }

    @GetMapping("/api/code")
    public Snippet getCodeJson() {
        return snippet;
    }

    @GetMapping(value = "/code", produces = "text/html")
    public ResponseEntity<String> getCodeHtml() {
        return ResponseEntity
                .ok()
                .body(snippet.getHtmlCode());
    }


}
