package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.repository.KichCoRepository;
import com.datnsd09.Datnsd09.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    private KichCoRepository kichCoRepository;

    @Override
    public List<KichCo> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return kichCoRepository.findAll();
    }

    @Override
    public KichCo getById(Long id) {
        return kichCoRepository.findById(id).get();
    }

    @Override
    public KichCo add(KichCo kichCo) {
        return kichCoRepository.save(kichCo);
    }

    @Override
    public KichCo update(KichCo kichCo) {
        return kichCoRepository.save(kichCo);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<KichCo> getAllDangHoatDong() {
        return kichCoRepository.fillAllDangHoatDong();
    }

    @Override
    public List<KichCo> getAllNgungHoatDong() {
        return kichCoRepository.fillAllNgungHoatDong();
    }

    @Override
    public boolean checkTenTrung(Integer ten) {
        if (ten == null) {
            return false; // ko cần check trùng lặp khi tên là null
        }
        for (KichCo kc : kichCoRepository.findAll()) {
            if (kc.getTen().equals(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, Integer ten) {
        if (ten == null) {
            return false; // ko cần check trùng lặp khi tên là null
        }
        for (KichCo kc : kichCoRepository.findAll()) {
            if (kc.getTen().equals(ten)) {
                if (!kc.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }


}
