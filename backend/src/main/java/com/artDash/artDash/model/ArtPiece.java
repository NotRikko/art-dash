package com.artDash.artDash.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;


public class ArtPiece {
    private Long id;
    private Long artistId;
    private String title;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus status;

}

