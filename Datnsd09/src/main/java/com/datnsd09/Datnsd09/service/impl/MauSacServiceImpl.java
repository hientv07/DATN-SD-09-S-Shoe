package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {


    }

    @Override
    public MauSac add(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }


    @Override
    public MauSac getId(Long id) {
        return mauSacRepository.findById(id).get();
    }
}
