package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.repository.LoaiDeRepository;
import com.datnsd09.Datnsd09.service.LoaiDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiDeServiceImpl implements LoaiDeService {
    @Autowired
    LoaiDeRepository loaiDeRepository;

    @Override
    public List<LoaiDe> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return loaiDeRepository.findAll(sort);

    }

    @Override
    public List<LoaiDe> getAllDangHoatDong() {

        return loaiDeRepository.fillAllDangHoatDong();

    }

    @Override
    public List<LoaiDe> getAllNgungHoatDong() {

        return loaiDeRepository.fillAllNgungHoatDong();

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public LoaiDe save(LoaiDe loaiDe) {
        return loaiDeRepository.save(loaiDe);
    }





    @Override
    public boolean checkTenTrung(String ten) {
        for (LoaiDe de : loaiDeRepository.findAll()) {
            if (de.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, String ten) {
        for (LoaiDe de : loaiDeRepository.findAll()) {
            if (de.getTen().equalsIgnoreCase(ten)) {
                if (!de.getId().equals(id)){
                    return false;
                }
            }
        }
        return true;
    }






    @Override
    public LoaiDe update(LoaiDe loaiDe) {

        return loaiDeRepository.save(loaiDe);

    }

    @Override
    public LoaiDe getById(Long id) {

        return loaiDeRepository.findById(id).get();

    }
}
