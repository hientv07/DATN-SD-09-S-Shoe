package com.datnsd09.Datnsd09.service.impl;


import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {

        return mauSacRepository.findAll();
    }

    @Override
    public List<MauSac> getAllDangHoatDong() {
        return mauSacRepository.fillAllDangHoatDong();
    }

    @Override
    public List<MauSac> getAllDungHoatDong() {
        return mauSacRepository.fillAllNgungHoatDong();
    }

    @Override
    public void deleteById(Long ma) {
//        Optional<MauSac> optional = mauSacRepository.findById(ma);
//        optional.ifPresent(mauSac -> mauSacRepository.delete(mauSac));
//        return true;
    }
//        Optional<MauSac> optional = mauSacRepository.findById(ma);
//        return optional.map(o->{
//            mauSacRepository.delete(o);
//            return o;
//        }).orElse();

    @Override
    public MauSac add(MauSac mauSac) {

        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(MauSac mauSac) {

        return mauSacRepository.save(mauSac);
//

    }

    @Override
    public MauSac getById(Long id) {
        return mauSacRepository.findById(id).get();
    }

    @Override
    public boolean checkTenTrung(String ten) {
         for (MauSac mauSac : mauSacRepository.findAll()) {
            if (mauSac.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, String ten) {
        for (MauSac mauSac : mauSacRepository.findAll()) {
            if (mauSac.getTen().equalsIgnoreCase(ten)) {
                if (!mauSac.getId().equals(id)){
                    return false;
                }
            }
        }
        return true;
    }



}
