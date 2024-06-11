package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.MauSac;

import java.util.List;


public interface MauSacService {

    List<MauSac>getAll();

    Boolean deleteById(Long ma);
//    void delete(Long id);

    MauSac add(MauSac mauSac);

    MauSac update(MauSac mauSac, Long ma);

    MauSac getId(Long id);

//    List<MauSac> getAllDangHoatDong();
//
//    List<MauSac> getAllNgungHoatDong();
}
