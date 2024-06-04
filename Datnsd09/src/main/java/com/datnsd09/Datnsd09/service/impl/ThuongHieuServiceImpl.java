package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.ThuongHieu;
import com.datnsd09.Datnsd09.repository.ThuongHieuRepository;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu getById(Long id) {
        return thuongHieuRepository.findById(id).get();
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
