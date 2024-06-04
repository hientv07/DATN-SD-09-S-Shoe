package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "loai_de")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoaiDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String tenld;

    private Date ngayTao;

    private Date ngaySua;

    private Integer trangThai;
}
