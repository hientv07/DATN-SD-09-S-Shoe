package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.LoaiDe;

import java.util.List;

public interface LoaiDeService {
    List<LoaiDe> getAll();

    LoaiDe getById(Long id);

    LoaiDe add(LoaiDe loaiDe);

    LoaiDe update(LoaiDe loaiDe);

//    void deleteById(Long id);
}
