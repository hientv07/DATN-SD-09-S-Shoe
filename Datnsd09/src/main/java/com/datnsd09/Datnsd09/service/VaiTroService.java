package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.VaiTro;

import java.util.List;

public interface VaiTroService {
    List<VaiTro> getAll();

    VaiTro getById(Long id);

    VaiTro add(VaiTro vaiTro);

    VaiTro update(VaiTro vaiTro);
}
