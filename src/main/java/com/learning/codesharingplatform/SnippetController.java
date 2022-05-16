package com.learning.codesharingplatform;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SnippetController {
    private final Snippet snippet = new Snippet();

    {
        snippet.setCode("public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}");
    }

    @GetMapping("/api/code")
    @ResponseBody
    public Snippet getCodeJson() {
        return snippet;
    }

    @GetMapping(value = "/code", produces = "text/html")
    public String getCodeHtml(Model model) {
        model.addAttribute("date", snippet.getDate());
        model.addAttribute("code", snippet.getCode());
        return "getSnippet";
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> postSnippet(@RequestBody Snippet snippet) {
        this.snippet.setCode(snippet.getCode());
        return ResponseEntity
                .ok()
                .body(new Empty());
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String getCodeFormHtml(Model model) {
        return "createSnippet";
    }

}
