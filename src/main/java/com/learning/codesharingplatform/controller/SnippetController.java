package com.learning.codesharingplatform.controller;

import com.learning.codesharingplatform.exception.SnippetNotFoundException;
import com.learning.codesharingplatform.model.AdditionalData;
import com.learning.codesharingplatform.model.Snippet;
import com.learning.codesharingplatform.repository.AdditionalDataRepository;
import com.learning.codesharingplatform.repository.SnippetRepository;
import com.learning.codesharingplatform.restriction.Restriction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class SnippetController {

    @Autowired
    private SnippetRepository repository;

    @Autowired
    private AdditionalDataRepository dataRepository;

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public Snippet getCodeJson(@PathVariable UUID id) {
        Snippet snippet = repository.findById(id).orElseThrow(SnippetNotFoundException::new);

        snippet = snippetAfterRestrictionCheck(snippet);

        if (snippet != null) {
            return snippet;
        } else {
            throw new SnippetNotFoundException();
        }

    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeHtml(@PathVariable UUID id, Model model) {
        Snippet snippet = repository.findById(id).orElseThrow(SnippetNotFoundException::new);

        snippet = snippetAfterRestrictionCheck(snippet);

        if (snippet != null) {
            model.addAttribute("date", snippet.getDate());
            model.addAttribute("code", snippet.getCode());
            model.addAttribute("time", snippet.getTime());
            model.addAttribute("views", snippet.getViews());
        } else {
            throw new SnippetNotFoundException();
        }

        return "getSnippet";

    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public Map<String, String> postSnippet(@RequestBody Snippet snippet) {
        Snippet newSnippet = new Snippet();
        newSnippet.setCode(snippet.getCode());
        newSnippet.setTime(snippet.getTime());
        newSnippet.setViews(snippet.getViews());

        Restriction restriction = setRestriction(newSnippet);

        AdditionalData data = new AdditionalData();
        data.setSnippet(newSnippet);
        if (restriction == Restriction.TIME || restriction == Restriction.BOTH) {
            data.setExpirationDate(newSnippet.getDate().plusSeconds(newSnippet.getTime()));
        }
        data.setRestriction(restriction);
        dataRepository.save(data);

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

    Restriction setRestriction(Snippet snippet) {

        if (snippet.getTime() > 0 && snippet.getViews() > 0) {
            return Restriction.BOTH;
        } else if (snippet.getTime() > 0 && snippet.getViews() <= 0) {
            return Restriction.TIME;
        } else if ((snippet.getTime() <= 0 && snippet.getViews() > 0)) {
            return Restriction.VIEW;
        }

        return Restriction.NONE;
    }

    Snippet snippetAfterRestrictionCheck(Snippet snippet) {

        Restriction restriction = snippet.getAdditionalData().getRestriction();
        boolean breakOut = true;
        boolean timeNotExpired = true;
        boolean viewsAvailable = true;
        boolean updateRepository = true;

        switch (restriction) {
            case BOTH:
                breakOut = false;
            case TIME:
                LocalDateTime viewDate = LocalDateTime.now();
                LocalDateTime expirationDate = snippet.getAdditionalData().getExpirationDate();
                long time = ChronoUnit.SECONDS.between(viewDate, expirationDate);
                snippet.setTime(time);
                timeNotExpired = time >= 0;
                if (breakOut) {
                    break;
                }
            case VIEW:
                int views = snippet.getViews() - 1;
                snippet.setViews(views);
                viewsAvailable = views >= 0;
                if (breakOut) {
                    break;
                }
                break;
            case NONE:
                updateRepository = false;

        }

        if (updateRepository) {

            if (timeNotExpired && viewsAvailable) {
                repository.save(snippet);
                return snippet;
            } else {
                repository.delete(snippet);
                return null;
            }

        } else {
            return snippet;
        }

    }

}
