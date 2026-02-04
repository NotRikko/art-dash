package com.artDash.artDash.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtPiece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_profile_id", nullable = false)
    @JsonBackReference
    private ArtistProfile artistProfile;

    @OneToMany(mappedBy = "artPiece",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JsonManagedReference
    @OrderColumn(name = "display_order")
    private List<ArtImage> artImages = new ArrayList<>();

    private String title;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addImage(ArtImage img) {
        if (img == null) return;
        if (!artImages.contains(img)) {
            artImages.add(img);
            img.setArtPiece(this);
        }
    }

    public void removeImage(ArtImage img) {
        if (img == null) return;
        if (artImages.remove(img)) {
            img.setArtPiece(null);
        }
    }
}

