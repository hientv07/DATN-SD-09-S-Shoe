package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.HinhAnhSanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPham,Long> {

    @Query(value = "select * from hinh_anh_san_pham where san_pham_id = :id",nativeQuery = true)
    List<HinhAnhSanPham> fillAllByIdSp(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hinh_anh_san_pham WHERE san_pham_id = :id",nativeQuery = true)
    void deleteAllByIdSp(@Param("id")Long id);
}
