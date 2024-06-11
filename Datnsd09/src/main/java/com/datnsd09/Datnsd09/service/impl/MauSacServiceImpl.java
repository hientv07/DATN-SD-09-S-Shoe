package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {

        return mauSacRepository.findAll();
    }

    @Override
    public Boolean deleteById(Long ma) {
        Optional<MauSac> optional = mauSacRepository.findById(ma);
        optional.ifPresent(mauSac -> mauSacRepository.delete(mauSac));
        return true;
    }
//        Optional<MauSac> optional = mauSacRepository.findById(ma);
//        return optional.map(o->{
//            mauSacRepository.delete(o);
//            return o;
//        }).orElse();

    @Override
    public MauSac add(MauSac mauSac) {

        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(MauSac mauSac, Long ma) {
        Optional<MauSac> optional = mauSacRepository.findById(ma);
        return optional.map(o-> {
            o.setMaMau(mauSac.getMaMau());
            o.setTen(mauSac.getTen());
            o.setNgayTao(mauSac.getNgayTao());
            o.setNgaySua(mauSac.getNgaySua());
            o.setTrangThai(mauSac.getTrangThai());
            return mauSacRepository.save(o);
        }).orElse(null);

    }


    @Override
    public MauSac getId(Long id) {
        return
                mauSacRepository.findById(id).get();
    }
}
