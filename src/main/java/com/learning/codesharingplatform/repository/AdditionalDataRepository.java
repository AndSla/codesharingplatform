package com.learning.codesharingplatform.repository;

import com.learning.codesharingplatform.model.AdditionalData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdditionalDataRepository extends CrudRepository<AdditionalData, UUID> {
}
