package com.datnsd09.Datnsd09.controller;


import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.UserInfoUserDetails;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/thong-ke")
public class ThongKeController {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping()
    public String hienThi(Model model

                          ){
//        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
//        if (name != null) {
//            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
//        } else {
//            return "redirect:/login";
//        }
        Integer soLuong = 50;
        List<Object[]>danhSachSapHetHang10 = sanPhamChiTietService.danhSachSapHetHang(soLuong);
        model.addAttribute("listSapHetHang",danhSachSapHetHang10);
        return "/admin/thong-ke/thong-ke";
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
