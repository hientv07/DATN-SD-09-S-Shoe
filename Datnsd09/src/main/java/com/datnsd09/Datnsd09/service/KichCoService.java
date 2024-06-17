package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.KichCo;

import java.util.List;

public interface KichCoService {

    List<KichCo> getAll();

    KichCo getById(Long id);

    KichCo add(KichCo kichCo);

    KichCo update(KichCo kichCo);

    void deleteById(Long id);

    List<KichCo> getAllDangHoatDong();

    List<KichCo> getAllNgungHoatDong();

    boolean checkTenTrung(Integer ten);

    boolean checkTenTrungSua(Long id, Integer ten);
}
