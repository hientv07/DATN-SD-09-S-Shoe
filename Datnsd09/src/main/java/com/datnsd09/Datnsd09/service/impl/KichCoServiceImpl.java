package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.repository.KichCoRepository;
import com.datnsd09.Datnsd09.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    private KichCoRepository kichCoRepository;

    @Override
    public List<KichCo> getAll() {
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
}
