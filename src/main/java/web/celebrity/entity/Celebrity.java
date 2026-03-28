package web.celebrity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "celebrities")
@Getter
@Setter
@NoArgsConstructor
public class Celebrity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연예인 이름
    @Column(nullable = false, unique = true)
    private String name;

    private LocalDateTime createdAt;
}