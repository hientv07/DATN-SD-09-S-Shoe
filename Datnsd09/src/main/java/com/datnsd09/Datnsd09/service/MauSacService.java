package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.MauSac;

import java.util.List;


public interface MauSacService {

    List<MauSac>getAll();

    List<MauSac> getAllDangHoatDong();

    List<MauSac> getAllDungHoatDong();

    void deleteById(Long ma);


    MauSac add(MauSac mauSac);

    MauSac update(MauSac mauSac);

    MauSac getById(Long id);
    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(Long id, String ten);


}
