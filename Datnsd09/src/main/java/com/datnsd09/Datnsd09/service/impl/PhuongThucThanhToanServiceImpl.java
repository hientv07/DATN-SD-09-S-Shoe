package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.PhuongThucThanhToan;
import com.datnsd09.Datnsd09.repository.PhuongThucThanhToanRepository;
import com.datnsd09.Datnsd09.service.PhuongThucThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhuongThucThanhToanServiceImpl implements PhuongThucThanhToanService {
    @Autowired
    PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    public List<PhuongThucThanhToan> findAll() {
        return phuongThucThanhToanRepository.findAll();
    }
}
