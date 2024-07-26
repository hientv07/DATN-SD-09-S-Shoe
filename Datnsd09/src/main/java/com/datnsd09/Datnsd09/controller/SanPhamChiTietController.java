package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.config.ExportFileCTSP;
import com.datnsd09.Datnsd09.config.FileExcelCTSP;
import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.UserInfoUserDetails;
import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.repository.KichCoRepository;
import com.datnsd09.Datnsd09.repository.LoaiDeRepository;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.repository.SanPhamRepository;
import com.datnsd09.Datnsd09.repository.ThuongHieuRepository;
import com.datnsd09.Datnsd09.service.HinhAnhSanPhamSerivce;
import com.datnsd09.Datnsd09.service.KichCoService;
import com.datnsd09.Datnsd09.service.LoaiDeService;
import com.datnsd09.Datnsd09.service.MauSacService;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import com.datnsd09.Datnsd09.service.SanPhamService;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/san-pham-chi-tiet")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private LoaiDeService loaiDeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private LoaiDeRepository loaiDeRepository;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping()
    public String getAll(Model model) {

        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listCTSP", sanPhamChiTietService.getAllCTSPOneSanPham());
        getString(model);
        model.addAttribute("sanPham", new SanPham());
        return "/admin/san-pham-chi-tiet/san-pham-chi-tiet";
    }

    private Model getString(Model model) {
        model.addAttribute("listSanPham", sanPhamService.getAllDangHoatDong());
        model.addAttribute("listThuongHieu", thuongHieuService.getAllDangHoatDong());
        model.addAttribute("listKichCo", kichCoService.getAllDangHoatDong());
        model.addAttribute("listMauSac", mauSacService.getAllDangHoatDong());
        model.addAttribute("listLoaiDe", loaiDeService.getAllDangHoatDong());

        return model;
    }

    @GetMapping("/dang-hoat-dong")
    public String dangHoatDong(Model model) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listCTSP", sanPhamChiTietService.getAllDangHoatDong());
        model.addAttribute("sanPham", new SanPham());
        getString(model);
        return "/admin/san-pham-chi-tiet/san-pham-chi-tiet";
    }

    @GetMapping("/ngung-hoat-dong")
    public String ngungHoatDong(Model model) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listCTSP", sanPhamChiTietService.getAllNgungHoatDong());
        model.addAttribute("sanPham", new SanPham());
        getString(model);
        return "/admin/san-pham-chi-tiet/san-pham-chi-tiet";
    }


    @PostMapping("/add-san-pham")
    public String addSanPham(@Valid @ModelAttribute("sanPham") SanPham sanPham,
                             BindingResult result, Model model,
                             @RequestParam("fileImage") List<MultipartFile> multipartFiles,
                             RedirectAttributes redirectAttributes) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("checkTab", "true");
            model.addAttribute("checkModal", "true");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listCTSP", sanPhamChiTietService.getAllCTSPOneSanPham());
            getString(model);
            return "/admin/san-pham-chi-tiet/san-pham-chi-tiet";
        }
        else if (!sanPhamService.checkTenTrung(sanPham.getTen())) {
            model.addAttribute("checkTab", "true");
            model.addAttribute("checkModal", "true");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listCTSP", sanPhamChiTietService.getAllCTSPOneSanPham());
            getString(model);
            return "/admin/san-pham-chi-tiet/san-pham-chi-tiet";
        }
        else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            redirectAttributes.addFlashAttribute("checkTab", "true");
            sanPham.setMa("SP" + sanPhamService.genMaTuDong());
            sanPham.setNgayTao(new Date());
            sanPham.setNgaySua(new Date());
            sanPham.setTrangThai(0);
            sanPhamService.add(sanPham);
            hinhAnhSanPhamSerivce.saveImage(multipartFiles, sanPham);

            return "redirect:/admin/san-pham-chi-tiet";
        }
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id, Model model) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        SanPham sanPham = sanPhamService.getById(id);
        List<SanPhamChiTiet> listCTSP = sanPhamChiTietService.getAllCtspByIdSanPham(id);
        model.addAttribute("sanPhamDetail", sanPham);
        model.addAttribute("listCTSP", listCTSP);
        return "/admin/san-pham-chi-tiet/sua-san-pham-chi-tiet";
    }

    @PostMapping("/update")
    public String Update(@RequestParam("listIdChiTietSp") List<String> listIdChiTietSp,
                         @RequestParam("listSanPham") List<String> listSanPham,
                         @RequestParam("listKichCo") List<String> listKichCo,
                         @RequestParam("listMauSac") List<String> listMauSac,
                         @RequestParam("listLoaiDe") List<String> listLoaiDe,
                         @RequestParam("listTrangThai") List<String> listTrangThai,
                         @RequestParam("listSoLuong") List<String> listSoLuong,
                         @RequestParam("listDonGia") List<String> listDonGia,
                         RedirectAttributes attributes) {
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        sanPhamChiTietService.updateAllCTSP(listIdChiTietSp, listSanPham, listKichCo,
                listMauSac, listLoaiDe, listTrangThai, listSoLuong, listDonGia);
        return "redirect:/admin/san-pham-chi-tiet";
    }

    @PostMapping("/add")
    public String add(@RequestParam("listSanPham") List<String> listSanPham,
                      @RequestParam("listKichCo") List<String> listKichCo,
                      @RequestParam("listMauSac") List<String> listMauSac,
                      @RequestParam("listLoaiDe") List<String> listLoaiDe,
                      @RequestParam("listSoLuong") List<String> listSoLuong,
                      @RequestParam("listDonGia") List<String> listDonGia,
                      RedirectAttributes attributes) {
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        sanPhamChiTietService.add(listSanPham, listKichCo, listMauSac, listLoaiDe, listSoLuong, listDonGia);
        return "redirect:/admin/san-pham-chi-tiet";

    }

//    @PostMapping("/add")
//    public String addSanPhamChiTiet(@RequestParam("listSanPham") Long sanPhamId,
//                                    @RequestParam("listLoaiDe") Long loaiDeId,
//                                    @RequestParam("listKichCo") List<Long> kichCoIds,
//                                    @RequestParam("listMauSac") List<Long> mauSacIds,
//                                    @RequestParam("listSoLuong") List<Integer> soLuongs,
//                                    @RequestParam("listDonGia") List<Integer> donGias,
//                                    Model model) {
//        // Xử lý logic thêm sản phẩm chi tiết ở đây
//
//        return "redirect:/admin/san-pham-chi-tiet"; // Chuyển hướng đến trang hiển thị danh sách sản phẩm chi tiết
//    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes){
        sanPhamChiTietService.delete(id);
        redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
        redirectAttributes.addFlashAttribute("checkModal", "modal");
        return "redirect:/admin/san-pham-chi-tiet";
    }


    @PostMapping("import-excel")
    public String importExcel(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes attributes,
            Model model

    ) throws IOException {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        if (!file.isEmpty()) {
//            String directory = "D:\\DATN-SD47\\BE-DATN-SD47";
//            String fileName = file.getOriginalFilename();
//            String filePath = directory + "\\" + fileName;
            FileExcelCTSP importFileExcelCTSP = new FileExcelCTSP();
            try {
                importFileExcelCTSP.ImportFile(file, sanPhamRepository, mauSacRepository, kichCoRepository,
                        loaiDeRepository, sanPhamChiTietRepository, sanPhamChiTietService);
                if (importFileExcelCTSP.checkLoi() > 0) {
                    attributes.addFlashAttribute("checkTab", "true");
                    attributes.addFlashAttribute("checkThongBao", "thanhCong");
                    attributes.addFlashAttribute("thongBaoLoiImport", "Đã thêm sản phẩm thành công nhưng có một số sản phẩm lỗi.");
                    return "redirect:/admin/san-pham-chi-tiet";
                }
            } catch (Exception e) {
                attributes.addFlashAttribute("checkTab", "true");
                attributes.addFlashAttribute("checkThongBao", "thaiBai");
                attributes.addFlashAttribute("thongBaoLoiImport", "Sai định dạng file");
                return "redirect:/admin/san-pham-chi-tiet";
            }
            attributes.addFlashAttribute("checkThongBao", "thanhCong");
            return "redirect:/admin/san-pham-chi-tiet";
        }
        attributes.addFlashAttribute("checkTab", "true");
        attributes.addFlashAttribute("thongBaoLoiImport", "Bạn chưa chọn file excel nào");
        attributes.addFlashAttribute("checkThongBao", "thaiBai");
        return "redirect:/admin/san-pham-chi-tiet";
    }

    @GetMapping("export-excel")
    public String exportExcel(
            RedirectAttributes attributes
    ) throws IOException {
        ExportFileCTSP exportFileCTSP = new ExportFileCTSP();
        exportFileCTSP.ExportFileExcel(sanPhamChiTietService);
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        return "redirect:/admin/san-pham-chi-tiet";
    }

    @GetMapping("file-mau-excel")
    public String fileMauExcel(
            RedirectAttributes attributes
    ) throws IOException {
        ExportFileCTSP exportFileCTSP = new ExportFileCTSP();
        exportFileCTSP.ExportFileExcelMau(sanPhamChiTietService);
        attributes.addFlashAttribute("checkTab", "true");
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        return "redirect:/admin/san-pham-chi-tiet";
    }
}

