package com.datnsd09.Datnsd09.service.impl;


import com.datnsd09.Datnsd09.entity.ThuongHieu;
import com.datnsd09.Datnsd09.repository.ThuongHieuRepository;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public List<ThuongHieu> getAllDangHoatDong() {

        return thuongHieuRepository.fillAllDangHoatDong();
    }

    @Override
    public List<ThuongHieu> getAllDungHoatDong() {

        return thuongHieuRepository.fillAllNgungHoatDong();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ThuongHieu getById(Long id) {
        return thuongHieuRepository.findById(id).get();
    }

    @Override
    public boolean checkTenTrung(String ten) {
        for (ThuongHieu thuongHieu : thuongHieuRepository.findAll()) {
            if (thuongHieu.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, String ten) {
        for (ThuongHieu thuongHieu : thuongHieuRepository.findAll()) {
            if (thuongHieu.getTen().equalsIgnoreCase(ten)) {
                if (!thuongHieu.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ThuongHieu add(ThuongHieu thuongHieu) {
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public ThuongHieu update(ThuongHieu thuongHieu) {
        return thuongHieuRepository.save(thuongHieu);
    }



}
