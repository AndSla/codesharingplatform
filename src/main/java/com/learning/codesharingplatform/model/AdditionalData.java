package com.learning.codesharingplatform.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AdditionalData {

    @Id
    @Column(name = "snippet_id")
    @Type(type = "uuid-char")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "snippet_id")
    private Snippet snippet;

    private LocalDateTime expirationDate;

    public AdditionalData() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
