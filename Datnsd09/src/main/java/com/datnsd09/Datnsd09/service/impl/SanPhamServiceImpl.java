package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.repository.SanPhamRepository;
import com.datnsd09.Datnsd09.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;


    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getById(Long id) {
        return sanPhamRepository.findById(id).get();
    }

    @Override
    public SanPham add(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void delete(Long id) {
        sanPhamRepository.deleteById(id);
    }
}
