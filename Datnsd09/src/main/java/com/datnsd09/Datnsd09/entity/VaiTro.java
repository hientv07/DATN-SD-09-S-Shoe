package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vai_tro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vai_tro")
    private Long id;

    @Column(name = "ten_vai_tro", length = 100)
    private String tenVaiTro;
}
