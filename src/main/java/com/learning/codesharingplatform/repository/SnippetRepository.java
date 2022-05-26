package com.learning.codesharingplatform.repository;

import com.learning.codesharingplatform.model.Snippet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    List<Snippet> findFirst10ByOrderByIdDesc();

}
