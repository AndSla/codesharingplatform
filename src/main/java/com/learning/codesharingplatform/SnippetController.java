package com.learning.codesharingplatform;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SnippetController {
    private final Snippet snippet = new Snippet();
    private final Map<Integer, Snippet> snippetsDB = new HashMap<>();

    {
        snippet.setCode("public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}");
        snippetsDB.put(snippetsDB.size() + 1, snippet);
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public Snippet getCodeJson(@PathVariable int id) {
        return snippetsDB.get(id);
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeHtml(@PathVariable int id, Model model) {
        model.addAttribute("date", snippetsDB.get(id).getDate());
        model.addAttribute("code", snippetsDB.get(id).getCode());
        return "getSnippet";
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public Map<String,Integer> postSnippet(@RequestBody Snippet snippet) {
        Snippet newSnippet = new Snippet();
        newSnippet.setCode(snippet.getCode());
        int id = snippetsDB.size() + 1;
        snippetsDB.put(id, newSnippet);
        Map<String, Integer> response = new HashMap<>();
        response.put("id", id);
        return response;
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String getCodeFormHtml(Model model) {
        return "createSnippet";
    }

}
