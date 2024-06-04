package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.ThuongHieu;

import java.util.List;

public interface ThuongHieuService {
    List<ThuongHieu> getAll();

    ThuongHieu getById(Long id);

    ThuongHieu add(ThuongHieu thuongHieu);

    ThuongHieu update(ThuongHieu thuongHieu);
}
