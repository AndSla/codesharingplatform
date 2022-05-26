package com.learning.codesharingplatform.controller;

import com.learning.codesharingplatform.model.Snippet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SnippetController {
    private final Snippet snippet = new Snippet();
    private final Map<Integer, Snippet> snippetsDB = new HashMap<>();

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
    public Map<String, String> postSnippet(@RequestBody Snippet snippet) {
        Snippet newSnippet = new Snippet();
        newSnippet.setCode(snippet.getCode());
        int id = snippetsDB.size() + 1;
        snippetsDB.put(id, newSnippet);
        Map<String, String> response = new HashMap<>();
        response.put("id", String.valueOf(id));
        return response;
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String getCodeFormHtml(Model model) {
        return "createSnippet";
    }

    @GetMapping("api/code/latest")
    @ResponseBody
    public List<Snippet> getLatest() {
        return snippetsDB
                .entrySet()
                .stream()
                .sorted((s1, s2) -> s2.getKey().compareTo(s1.getKey()))
                .limit(10)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String getLatestHtml(Model model) {
        List<Snippet> latestSnippets = snippetsDB
                .entrySet()
                .stream()
                .sorted((s1, s2) -> s2.getKey().compareTo(s1.getKey()))
                .limit(10)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        model.addAttribute("snippets", latestSnippets);
        return "getLatest";
    }

}
