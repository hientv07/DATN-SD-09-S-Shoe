package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.HinhAnhSanPham;
import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.repository.HinhAnhSanPhamRepository;
import com.datnsd09.Datnsd09.service.HinhAnhSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class HinhAnhSanPhamSerivceImpl implements HinhAnhSanPhamSerivce {

    @Autowired
    private HinhAnhSanPhamRepository hinhAnhSanPhamRepository;

    private Date currentDate = new Date();

    @Override
    public void saveImage(List<MultipartFile> files, SanPham sanPham) {
        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty()) {
                try {
                    HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
                    // Lưu tệp vào cơ sở dữ liệu
                    hinhAnh.setUrl(multipartFile.getOriginalFilename());
                    hinhAnh.setNgayTao(currentDate);
                    hinhAnh.setNgaySua(currentDate);
                    hinhAnh.setTrangThai(0);
                    hinhAnh.setUuTien(0);
                    hinhAnh.setSanPham(sanPham);
                    // Thực hiện các tác vụ khác nếu cần thiết
                    hinhAnhSanPhamRepository.save(hinhAnh);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Xử lý lỗi
                }
            }
        }
    }

    @Override
    public void saveWhenEditingImage(List<MultipartFile> files, Long id) {
        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty()) {
                try {
                    HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
                    // Lưu tệp vào cơ sở dữ liệu
                    hinhAnh.setUrl(multipartFile.getOriginalFilename());
                    hinhAnh.setNgayTao(currentDate);
                    hinhAnh.setNgaySua(currentDate);
                    hinhAnh.setTrangThai(0);
                    hinhAnh.setUuTien(0);
                    hinhAnh.setSanPham(SanPham.builder().id(id).build());
                    // Thực hiện các tác vụ khác nếu cần thiết
                    hinhAnhSanPhamRepository.save(hinhAnh);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Xử lý lỗi
                }
            }
        }
    }

    @Override
    public List<HinhAnhSanPham> listHinhAnh(Long id) {
        return hinhAnhSanPhamRepository.fillAllByIdSp(id);
    }

    @Override
    public void deleteByID(Long id) {
        hinhAnhSanPhamRepository.deleteAllByIdSp(id);
    }
}
