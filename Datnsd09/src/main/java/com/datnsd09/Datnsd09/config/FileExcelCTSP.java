package com.datnsd09.Datnsd09.config;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.repository.KichCoRepository;
import com.datnsd09.Datnsd09.repository.LoaiDeRepository;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.repository.SanPhamRepository;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FileExcelCTSP {
    Integer indexLoi = 0;

    public void ImportFile(
            MultipartFile file, SanPhamRepository sanPhamRepository, MauSacRepository mauSacRepository,
            KichCoRepository kichThuocRepository, LoaiDeRepository deGiayRepository,
            SanPhamChiTietRepository chiTietSanPhamRepository, SanPhamChiTietService chiTietSanPhamService) throws Exception {

//        FileInputStream fileExcel = new FileInputStream(new File(path));
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            Row firtRow = iterator.next();
            Cell firtCell = firtRow.getCell(0);
            List<Integer> listIndex = new ArrayList<>();
            int index = 0;
            while (iterator.hasNext()) {
                index++;
                try {
                    Row row = iterator.next();
                    String sanPhamStr = String.valueOf(getCellValue(row.getCell(0))).trim();
                    String mauSacStr = String.valueOf(getCellValue(row.getCell(1))).trim();
                    String kichThuocStr = String.valueOf((int) row.getCell(2).getNumericCellValue()).trim();
                    String deGiayStr = String.valueOf(getCellValue(row.getCell(3))).trim();
                    String soLuongTon = String.valueOf((int) row.getCell(4).getNumericCellValue()).trim();
                    String giaBan = String.valueOf((int) row.getCell(5).getNumericCellValue()).trim();

                    if (mauSacStr.isEmpty() && sanPhamStr.isEmpty() && kichThuocStr.isEmpty() && deGiayStr.isEmpty()
                            && soLuongTon.isEmpty() && giaBan.isEmpty()) {
                        listIndex.add(index);
                        continue;
                    }

                    SanPham sanPham = sanPhamRepository.findSanPhamByTen(sanPhamStr);
                    MauSac mauSac = mauSacRepository.findMauSacByTen(mauSacStr);
                    KichCo kichThuoc = kichThuocRepository.findKichCoByTen(Integer.parseInt(kichThuocStr));
                    LoaiDe deGiay = deGiayRepository.findDeGiayByTen(deGiayStr);
                    if (sanPham == null || mauSac == null || kichThuoc == null || deGiay == null) {
                        listIndex.add(index);
                        continue;
                    }
                    if (Integer.parseInt(soLuongTon) <= 0 || Integer.parseInt(soLuongTon) > 99999 ||
                            Integer.parseInt(giaBan) <= 0 || Integer.parseInt(giaBan) > 1000000000) {
                        listIndex.add(index);
                        continue;
                    }

                    SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet();
                    SanPhamChiTiet chiTietSanPhamCheck =
                            chiTietSanPhamRepository.findChiTietSanPham(
                                    sanPham.getId(),
                                    mauSac.getId(),
                                    deGiay.getId(),
                                    kichThuoc.getId());
                    if (chiTietSanPhamCheck == null) {
                        chiTietSanPham.setSanPham(sanPham);
                        chiTietSanPham.setMauSac(mauSac);
                        chiTietSanPham.setKichCo(kichThuoc);
                        chiTietSanPham.setLoaiDe(deGiay);
                        chiTietSanPham.setSoLuong(Integer.parseInt(soLuongTon));
                        chiTietSanPham.setGiaHienHanh(Long.valueOf(giaBan));
                        chiTietSanPham.setTrangThai(0);
                        chiTietSanPham.setNgayTao(new Date());
                        chiTietSanPham.setNgaySua(new Date());
                        chiTietSanPhamService.saveExcel(chiTietSanPham);
                    } else {
                        chiTietSanPhamCheck.setSoLuong(chiTietSanPhamCheck.getSoLuong() + Integer.parseInt(soLuongTon));
                        chiTietSanPhamCheck.setGiaHienHanh(Long.valueOf(giaBan));
                        chiTietSanPhamService.saveExcel(chiTietSanPhamCheck);
                    }

                    workbook.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    listIndex.add(index);
                    continue;
                }
            }
            this.SaveFileError(file, listIndex);
            indexLoi = listIndex.size();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Integer checkLoi() {

        return indexLoi;

    }

    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    public void SaveFileError(MultipartFile file, List<Integer> listIndex) {

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            int rowIndex = 0;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                for (Integer index : listIndex) {
                    if (rowIndex == index) {
                        colorRowRed(workbook, currentRow);
                        break;
                    }
                }
                rowIndex++;
            }

            String currentWorkingDirectory = System.getProperty("user.dir");

            String beDatnSd47Path = Paths.get(currentWorkingDirectory, file.getOriginalFilename()).toString();
            System.out.println(beDatnSd47Path);

            try (OutputStream outputStream = new FileOutputStream(beDatnSd47Path)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void colorRowRed(Workbook workbook, Row row) {
        CellStyle redCellStyle = createRedCellStyle(workbook);

        int lastCellNum = row.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellStyle(redCellStyle);
        }
    }

    private static CellStyle createRedCellStyle(Workbook workbook) {
        CellStyle redCellStyle = workbook.createCellStyle();
        redCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        redCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return redCellStyle;
    }
}
