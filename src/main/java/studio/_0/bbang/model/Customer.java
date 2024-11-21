package studio._0.bbang.model;

import jakarta.persistence.*;

import java.awt.*;
import java.time.LocalDateTime;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nickname;

    private Boolean isKakao;

    private String phone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;
}
