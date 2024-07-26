package com.datnsd09.Datnsd09.config;

import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ExportFileCTSP {

    public void ExportFileExcel(SanPhamChiTietService chiTietSanPhamSerivce) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Quản Lý Sản Phẩm");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell1 = firstRow.createCell(0);
            Cell firstCell2 = firstRow.createCell(1);
            Cell firstCell3 = firstRow.createCell(2);
            Cell firstCell4 = firstRow.createCell(3);
            Cell firstCell5 = firstRow.createCell(4);
            Cell firstCell6 = firstRow.createCell(5);
            Cell firstCell7 = firstRow.createCell(6);
            Cell firstCell8 = firstRow.createCell(7);

            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Tên Sản Phẩm");
            firstCell3.setCellValue("Màu Sắc");
            firstCell4.setCellValue("Kích Cỡ");
            firstCell5.setCellValue("Loại Đế");
            firstCell6.setCellValue("Số Lượng");
            firstCell7.setCellValue("Giá");
            firstCell8.setCellValue("Trạng Thái");
            int index = 1;
            for (SanPhamChiTiet chiTietSanPham : chiTietSanPhamSerivce.getAll()) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(index++);

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(chiTietSanPham.getSanPham().getTen());

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(chiTietSanPham.getMauSac().getTen());

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(chiTietSanPham.getKichCo().getTen());

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(chiTietSanPham.getLoaiDe().getTen());

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(chiTietSanPham.getSoLuong());

                Cell cell7 = row.createCell(6);
                cell7.setCellValue(chiTietSanPham.getGiaHienHanh());

                Cell cell8 = row.createCell(7);
                cell8.setCellValue(chiTietSanPham.getTrangThai() == 0 ? "Đang hoạt động" : "Dừng hoạt động");

            }
            try {
                FileOutputStream outputStream = new FileOutputStream("ExportFileSanPham" + Calendar.getInstance().getTimeInMillis() + ".xlsx");
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void ExportFileExcelMau(SanPhamChiTietService chiTietSanPhamSerivce) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("File Mẫu Import");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell1 = firstRow.createCell(0);
            Cell firstCell2 = firstRow.createCell(1);
            Cell firstCell3 = firstRow.createCell(2);
            Cell firstCell4 = firstRow.createCell(3);
            Cell firstCell5 = firstRow.createCell(4);
            Cell firstCell6 = firstRow.createCell(5);

            firstCell1.setCellValue("Tên Sản Phẩm");
            firstCell2.setCellValue("Màu Sắc");
            firstCell3.setCellValue("Kích Cỡ");
            firstCell4.setCellValue("Loại Đế");
            firstCell5.setCellValue("Số Lượng");
            firstCell6.setCellValue("Giá");

            int index = 0;
            for (SanPhamChiTiet chiTietSanPham : chiTietSanPhamSerivce.getAllDangHoatDong()) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(chiTietSanPham.getSanPham().getTen());

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(chiTietSanPham.getMauSac().getTen());

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(chiTietSanPham.getKichCo().getTen());

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(chiTietSanPham.getLoaiDe().getTen());

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(chiTietSanPham.getSoLuong());

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(chiTietSanPham.getGiaHienHanh());
                index++;
                if (index > 5) {
                    break;
                }
            }
            try {
                FileOutputStream outputStream = new FileOutputStream("FileMauImport" + Calendar.getInstance().getTimeInMillis() + ".xlsx");
                workbook.write(outputStream);
                workbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
