package com.datnsd09.Datnsd09.controller;


import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.UserInfoUserDetails;
import com.datnsd09.Datnsd09.repository.HoaDonChiTietRepository;
import com.datnsd09.Datnsd09.repository.HoaDonRepository;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.service.HoaDonChiTietService;
import com.datnsd09.Datnsd09.service.HoaDonService;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonService hoaDonService;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping()
    public String hienThi(Model model,
                          RedirectAttributes redirectAttributes
                          ){
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
//
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDateChart = calendar.getTime();

        Date endDateChart = new Date();
        if (model.asMap().get("startDateChart") != null) {
            startDateChart = (Date) model.asMap().get("startDateChart");
        }
        if (model.asMap().get("endDateChart") != null) {
            endDateChart = (Date) model.asMap().get("endDateChart");
        }
        List<Object[]> thongKeSP = hoaDonChiTietService.thongKeSanPhamTheoNgay(startDateChart, endDateChart);

        model.addAttribute("startDateChart", startDateChart);
        model.addAttribute("endDateChart", endDateChart);
        model.addAttribute("thongKeSP", thongKeSP);
        //tong sp bans được
        Integer sumSanPham = (Integer) model.asMap().get("sumSanPham");
        Integer sumSanPhamAll = hoaDonChiTietService.sumSanPhamHoaDonAll();
        if (sumSanPham == null) {
            model.addAttribute("sumSanPham", sumSanPhamAll);
        }else{
            model.addAttribute("sumSanPham", sumSanPham);
        }

        //Tổng số đơn hàng
        Integer countHoaDon = (Integer) model.asMap().get("countHoaDon");
        Integer countHoaDonAll = hoaDonService.countHoaDonAll();
        if (countHoaDon == null) {
            model.addAttribute("countHoaDon", countHoaDonAll);
        }else{
            model.addAttribute("countHoaDon", countHoaDon);
        }
        //Tổng tiền / dơn hàng
        Long sumHoaDon = (Long) model.asMap().get("sumHoaDon");
        Long sumHoaDonAll = hoaDonService.sumGiaTriHoaDonAll();
        if (sumHoaDon == null) {
            model.addAttribute("sumHoaDon", sumHoaDonAll);
        }else{
            model.addAttribute("sumHoaDon", sumHoaDon);
        }
        //trạng thái đơn hàng
        Integer countHoaDonChoXacNhan = (Integer) model.asMap().get("countHoaDonChoXacNhanBetween");
        Integer countHoaDonChoGiao = (Integer) model.asMap().get("countHoaDonChoGiaoBetween");
        Integer countHoaDonDangGiao = (Integer) model.asMap().get("countHoaDonDangGiaoBetween");
        Integer countHoaDonHoanThanh = (Integer) model.asMap().get("countHoaDonHoanThanhBetween");
        Integer countHoaDonDaHuy = (Integer) model.asMap().get("countHoaDonDaHuyBetween");
        Integer countHoaDon0 = hoaDonService.countHoaDon(0);
        Integer countHoaDon1 = hoaDonService.countHoaDon(1);
        Integer countHoaDon2 = hoaDonService.countHoaDon(2);
        Integer countHoaDon3 = hoaDonService.countHoaDon(3);
        Integer countHoaDon5 = hoaDonService.countHoaDon(5);
       // Integer countHoaDon5 = hoaDonService.countHoaDon(5);

        ///danh sach sap heets
        List<Object[]> danhSachSapHetHangAll = (List<Object[]>) model.asMap().get("danhSachSapHetHang");
        Integer soLuong = 50;
        List<Object[]>danhSachSapHetHang10 = sanPhamChiTietService.danhSachSapHetHang(soLuong);


        if (countHoaDonChoXacNhan == null) {
            model.addAttribute("countHoaDonChoXacNhanBetween", countHoaDon0);
        }else{
            model.addAttribute("countHoaDonChoXacNhanBetween", countHoaDonChoXacNhan);
        }
        if (countHoaDonChoGiao == null) {
            model.addAttribute("countHoaDonChoGiaoBetween", countHoaDon1);
        }else{
            model.addAttribute("countHoaDonChoGiaoBetween", countHoaDonChoGiao);
        }
        if (countHoaDonDangGiao == null) {
            model.addAttribute("countHoaDonDangGiaoBetween", countHoaDon2);
        }else{
            model.addAttribute("countHoaDonDangGiaoBetween", countHoaDonDangGiao);
        }
        if (countHoaDonHoanThanh == null) {
            model.addAttribute("countHoaDonHoanThanhBetween", countHoaDon3);
        }else{
            model.addAttribute("countHoaDonHoanThanhBetween", countHoaDonHoanThanh);
        }
        if (countHoaDonDaHuy == null) {
            model.addAttribute("countHoaDonDaHuyBetween", countHoaDon5);
        }else{
            model.addAttribute("countHoaDonDaHuyBetween", countHoaDonDaHuy);
        }
        if (danhSachSapHetHangAll == null){
            model.addAttribute("danhSachSapHetHang",danhSachSapHetHang10);
        }else{
            model.addAttribute("danhSachSapHetHang",danhSachSapHetHangAll);
        }

        //bán chạy
        List<Object[]> thongKeSanPhamBetween = (List<Object[]>) model.asMap().get("thongKeBetween");
        List<Object[]> thongKeSanPhamAll = hoaDonChiTietService.findByTongSoLuongBetweenGetAll();
        if (thongKeSanPhamBetween == null) {
            model.addAttribute("thongKeBetween", thongKeSanPhamAll);
        }else{
            model.addAttribute("thongKeBetween", thongKeSanPhamBetween);
        }

        model.addAttribute("listSapHetHang",danhSachSapHetHang10);
        return "/admin/thong-ke/thong-ke";
    }

    @PostMapping("/count-between")
    public String countHoaDonBetween(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                     RedirectAttributes redirectAttributes){
        //tổng sp bán đc
        Integer sumSanPham = hoaDonChiTietService.sumSanPhamBanDuocBetween(startDate,endDate);
        //tổng đơn hàng, hoa don
        Integer countHoaDon = hoaDonService.countHoaDonBetween(startDate, endDate);
        //tổng tiền hóa đơn
        Long sumHoaDon = hoaDonService.sumGiaTriHoaDonBetween(startDate, endDate);
        //Trạng thái của các hóa đơn
        Integer countHoaDonChoXacNhanBetween = hoaDonService.countHoaDonTrangThaiBetween(startDate, endDate, 0);
        Integer countHoaDonChoGiaoBetween = hoaDonService.countHoaDonTrangThaiBetween(startDate,endDate,1);
        Integer countHoaDonDangGiaoBetween = hoaDonService.countHoaDonTrangThaiBetween(startDate, endDate, 2);
        Integer countHoaDonHoanThanhBetween = hoaDonService.countHoaDonTrangThaiBetween(startDate,endDate,3);
        Integer countHoaDonDaHuyBetween = hoaDonService.countHoaDonTrangThaiBetween(startDate,endDate,5);
        //
        redirectAttributes.addFlashAttribute("countHoaDonChoXacNhanBetween", countHoaDonChoXacNhanBetween);
        redirectAttributes.addFlashAttribute("countHoaDonChoGiaoBetween", countHoaDonChoGiaoBetween);
        redirectAttributes.addFlashAttribute("countHoaDonDangGiaoBetween", countHoaDonDangGiaoBetween);
        redirectAttributes.addFlashAttribute("countHoaDonHoanThanhBetween", countHoaDonHoanThanhBetween);
        redirectAttributes.addFlashAttribute("countHoaDonDaHuyBetween", countHoaDonDaHuyBetween);
        redirectAttributes.addFlashAttribute("sumSanPham", sumSanPham);
        redirectAttributes.addFlashAttribute("startDate", startDate);
        redirectAttributes.addFlashAttribute("endDate", endDate);
        redirectAttributes.addFlashAttribute("countHoaDon", countHoaDon);
        redirectAttributes.addFlashAttribute("sumHoaDon",sumHoaDon);
        List<Object[]> thongKeSanPhamBetween = hoaDonChiTietService.findByTongSoLuongBetween(startDate,endDate);
        redirectAttributes.addFlashAttribute("thongKeBetween", thongKeSanPhamBetween);
        return "redirect:/admin/thong-ke";

    }

    @PostMapping("/count-range")
    public String countHoaDonTrongKhoangThoiGian(
            @RequestParam(name = "startDateChart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDateChart,
            @RequestParam(name = "endDateChart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateChart,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("startDateChart", startDateChart);
        redirectAttributes.addFlashAttribute("endDateChart", endDateChart);
        List<Object[]> thongKeSP = hoaDonChiTietService.thongKeSanPhamTheoNgay(startDateChart, endDateChart);
        redirectAttributes.addFlashAttribute("thongKeSP", thongKeSP);
        return "redirect:/admin/thong-ke";
    }

    @PostMapping("/sapHetHang")
    public String countSapHetHang(
            @RequestParam(name = "soLuong", required = false) Integer soLuong,
            @RequestParam(name = "outputFormat", defaultValue = "table") String outputFormat,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response) {

        List<Object[]> danhSachSapHetHang = sanPhamChiTietRepository.danhSachSapHetHang(soLuong);

        redirectAttributes.addFlashAttribute("soLuong", soLuong);
        redirectAttributes.addFlashAttribute("danhSachSapHetHang", danhSachSapHetHang);

        if ("excel".equals(outputFormat)) {
            // Export to Excel
            response.setHeader("Content-Disposition", "attachment; filename=SapHetHang.xlsx");
            exportToExcel(response, danhSachSapHetHang);
            return null;  // Returning null to indicate that the response is already handled
        } else {
            return "redirect:/admin/thong-ke";  // Redirect to the table view
        }
    }

    private void exportToExcel(HttpServletResponse response, List<Object[]> listSapHetHang) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("SapHetHang");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("STT");
            header.createCell(1).setCellValue("Sản phẩm");
            header.createCell(2).setCellValue("Kích cỡ");
            header.createCell(3).setCellValue("Màu sắc");
            header.createCell(4).setCellValue("Loại đế");
            header.createCell(5).setCellValue("Số Lượng");

            // Populate data rows
            int rowNum = 1;
            for (Object[] row : listSapHetHang) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(rowNum - 1); // STT
                for (int i = 1; i < row.length + 1; i++) {
                    Cell cell = dataRow.createCell(i);
                    cell.setCellValue(String.valueOf(row[i - 1]));
                }
            }

            // Write the workbook to the response output stream
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();

        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }


}
