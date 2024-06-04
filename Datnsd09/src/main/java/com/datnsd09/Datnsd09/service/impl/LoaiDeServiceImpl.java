package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.repository.LoaiDeRepository;
import com.datnsd09.Datnsd09.service.LoaiDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiDeServiceImpl implements LoaiDeService {
    @Autowired
    private LoaiDeRepository loaiDeRepository;


    @Override
    public List<LoaiDe> getAll() {
        return loaiDeRepository.findAll();
    }

    @Override
    public LoaiDe getById(Long id) {
        return loaiDeRepository.findById(id).get();
    }

    @Override
    public LoaiDe add(LoaiDe loaiDe) {
        return loaiDeRepository.save(loaiDe);
    }

    @Override
    public LoaiDe update(LoaiDe loaiDe) {
        return loaiDeRepository.save(loaiDe);
    }

//    @Override
//    public void deleteById(Long id) {
//
//    }
}
