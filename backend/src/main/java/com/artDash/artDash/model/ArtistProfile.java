package com.artDash.artDash.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String displayName;
    private String location;
    private String profileBannerPicUrl;

    @OneToMany(mappedBy = "artistProfile",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<ArtPiece> artPieces = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "artist_social_links",
            joinColumns = @JoinColumn(name = "artist_profile_id"))
    @Column(name = "social_link")
    private List<String> socialLinks = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addArtPiece(ArtPiece art) {
        artPieces.add(art);
        art.setArtistProfile(this);
    }

    public void removeArtPiece(ArtPiece art) {
        artPieces.remove(art);
        art.setArtistProfile(null);
    }
}