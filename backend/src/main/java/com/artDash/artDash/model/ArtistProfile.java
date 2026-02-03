package com.artDash.artDash.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class ArtistProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User userId;

    private String displayName;
    private String location;
    private String profileBannerPicUrl;

    @ElementCollection
    private List<String> socialLinks;
}
