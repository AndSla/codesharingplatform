package com.learning.codesharingplatform.controller;

import com.learning.codesharingplatform.model.Snippet;
import com.learning.codesharingplatform.repository.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class SnippetController {

    @Autowired
    private SnippetRepository repository;

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public Snippet getCodeJson(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeHtml(@PathVariable UUID id, Model model) {
        Snippet snippet = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("date", snippet.getDate());
        model.addAttribute("code", snippet.getCode());
        return "getSnippet";
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public Map<String, String> postSnippet(@RequestBody Snippet snippet) {
        Snippet newSnippet = new Snippet();
        newSnippet.setCode(snippet.getCode());
        newSnippet.setTime(snippet.getTime());
        newSnippet.setViews(snippet.getViews());
        repository.save(newSnippet);
        Map<String, String> response = new HashMap<>();
        response.put("id", String.valueOf(newSnippet.getId()));
        return response;
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public String getCodeFormHtml(Model model) {
        return "createSnippet";
    }

    @GetMapping("api/code/latest")
    @ResponseBody
    public List<Snippet> getLatest() {
        return repository.find10LatestSnippets();
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public String getLatestHtml(Model model) {
        List<Snippet> latestSnippets = repository.find10LatestSnippets();
        model.addAttribute("snippets", latestSnippets);
        return "getLatest";
    }

}
