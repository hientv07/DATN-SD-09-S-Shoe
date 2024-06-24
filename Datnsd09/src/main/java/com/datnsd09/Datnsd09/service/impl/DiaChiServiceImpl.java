package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.DiaChi;
import com.datnsd09.Datnsd09.repository.DiaChiRepository;
import com.datnsd09.Datnsd09.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;

    @Override
    public List<DiaChi> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return diaChiRepository.findAll(sort);
    }

    @Override
    public List<DiaChi> getAllByTaiKhoan(Long id) {
        return diaChiRepository.getAllByIdTaiKhoan(id);

    }


    @Override
    public void deleteById(Long id) {

        diaChiRepository.deleteById(id);

    }

    @Override
    public DiaChi save(DiaChi diaChi) {

        return diaChiRepository.save(diaChi);

    }

    @Override
    public boolean checkTenTrung(String ten,Long idTaiKhoan) {

        for (DiaChi sp : diaChiRepository.getAllByIdTaiKhoan(idTaiKhoan)) {
            if (sp.getDiaChiCuThe().equalsIgnoreCase(ten) && sp.getKhachHang().getId().equals(idTaiKhoan) ) {
                return false;
            }
        }
        return  true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, String ten,Long idTaiKhoan) {

        for (DiaChi sp :diaChiRepository.getAllByIdTaiKhoan(Long.valueOf(3) )) {
            if (sp.getDiaChiCuThe().equalsIgnoreCase(ten) && sp.getKhachHang().getId().equals(idTaiKhoan)) {
                if (!sp.getKhachHang().getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public DiaChi update(DiaChi diaChi) {

        return diaChiRepository.save(diaChi);

    }

    @Override
    public DiaChi getById(Long id) {

        return diaChiRepository.findById(id).get();

    }

    @Override
    public List<DiaChi> getAllTrangThai(Integer trangThai) {
        return diaChiRepository.fillAllByTrangThai(trangThai);
    }

}