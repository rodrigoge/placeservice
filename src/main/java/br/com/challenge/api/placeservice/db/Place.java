package br.com.challenge.api.placeservice.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "place")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    @Size(max = 255, message = "Name mustn't be null")
    private String name;

    @Column
    @NonNull
    @Size(max = 255, message = "Slug mustn't be null")
    private String slug;

    @Column
    @NonNull
    @Size(max = 255, message = "City mustn't be null")
    private String city;

    @Column
    @NonNull
    @Size(max = 255, message = "State mustn't be null")
    private String state;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
