package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.repository.SanPhamRepository;
import com.datnsd09.Datnsd09.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;


    @Override
    public List<SanPham> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
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

    @Override
    public Integer genMaTuDong() {
        String maStr = "";
        try {
            if (sanPhamRepository.index() != null) {
                maStr = sanPhamRepository.index().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }

    @Override
    public List<SanPham> getAllDangHoatDong() {
        return sanPhamRepository.fillAllDangHoatDong();
    }

    @Override
    public List<SanPham> getAllNgungHoatDong() {
        return sanPhamRepository.fillAllNgungHoatDong();
    }

    @Override
    public boolean checkTenTrung(String ten) {

        for (SanPham sp : sanPhamRepository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {

        for (SanPham sp : sanPhamRepository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                if (!sp.getMa().equalsIgnoreCase(ma)) {
                    return false;
                }
            }
        }
        return true;
    }
}
