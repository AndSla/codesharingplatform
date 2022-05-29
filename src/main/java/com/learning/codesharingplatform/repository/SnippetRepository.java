package com.learning.codesharingplatform.repository;

import com.learning.codesharingplatform.model.Snippet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    @Query(
            value = "SELECT * FROM SNIPPET WHERE TIME = 0 AND VIEWS = 0 ORDER BY DATE DESC LIMIT 10",
            nativeQuery = true)
    List<Snippet> find10LatestSnippets();

}

