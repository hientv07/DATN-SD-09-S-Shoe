package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.HinhAnhSanPham;
import com.datnsd09.Datnsd09.entity.SanPham;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HinhAnhSanPhamSerivce {

    void saveImage(List<MultipartFile> files, SanPham sanPham);

    void saveWhenEditingImage(List<MultipartFile> files, Long id);

    List<HinhAnhSanPham> listHinhAnh(Long id);

    void deleteByID(Long id);

}
