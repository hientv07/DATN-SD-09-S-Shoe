package com.datnsd09.Datnsd09.service.impl;


import com.datnsd09.Datnsd09.entity.HoaDon;
import com.datnsd09.Datnsd09.entity.HoaDonChiTiet;
import com.datnsd09.Datnsd09.repository.HoaDonRepository;
import com.datnsd09.Datnsd09.service.HoaDonService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public List<HoaDon> findByTrangThai(Integer trangThai) {
        return hoaDonRepository.findByTrangThai(trangThai);
    }

    @Override
    public Integer countHoaDonTreo() {
        return hoaDonRepository.countHoaDonTreo();
    }

    @Override
    public List<HoaDon> find5ByTrangThai(Integer trangThai) {
        return hoaDonRepository.find5ByTrangThai(trangThai);
    }

    @Override
    public HoaDon findByMa(String ma) {
        return hoaDonRepository.findByMa(ma);
    }

    @Override
    public HoaDon finByHoaDonMaHDSdt(String maDonHang, String sdt) {
        return hoaDonRepository.finByHoaDonMaHDSdt(maDonHang, sdt);
    }

    @Override
    public List<HoaDon> findAllOrderByNgaySua() {
        return hoaDonRepository.findAllOrderByNgaySua();
    }

    @Override
    public List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan, Integer trangThai) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(idTaiKhoan, trangThai);

    }

    @Override
    public Integer countHoaDonDay(Date ngayTao) {
        return hoaDonRepository.countHoaDonNgay(ngayTao);
    }

    @Override
    public List<HoaDon> getAllHoaDonByTaiKhoanOrderByNgaySua(Long idTaiKhoan) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanOrderByNgaySua(idTaiKhoan);

    }


    @Override
    public Long sumHoaDonDay(Date ngayTao) {
        return hoaDonRepository.sumGiaTriHoaDonNgay(ngayTao);
    }

    @Override
    public Integer countHoaDonMonth(Date ngayTao) {
        return hoaDonRepository.countHoaDonThang(ngayTao);
    }

    @Override
    public Long sumHoaDonMonth(Date ngayTao) {
        return hoaDonRepository.sumGiaTriHoaDonThang(ngayTao);
    }

    @Override
    public Integer countHoaDon(Integer trangThai) {
        return hoaDonRepository.countHoaDon(trangThai);
    }

    @Override
    public Integer countHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.countHoaDonBetween(startDate, endDate);
    }

    @Override
    public Long sumGiaTriHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.sumGiaTriHoaDonBetween(startDate,endDate);
    }

    @Override
    public Integer countHoaDonTrangThaiBetween(Date startDate, Date endDate, Integer trangThai) {
        return hoaDonRepository.countHoaDonTrangThaiBetween(startDate, endDate, trangThai);
    }

    @Override
    public Integer countHoaDonTrangThaiNgay(Date ngayTao, Integer trangThai) {
        return hoaDonRepository.countHoaDonTrangThaiNgay(ngayTao, trangThai);
    }

    @Override
    public Integer countHoaDonTrangThaiThang(Date ngayTao, Integer trangThai) {
        return hoaDonRepository.countHoaDonTrangThaiThang(ngayTao, trangThai);
    }

    @Override
    public Integer countHoaDonAll() {
        return hoaDonRepository.countHoaDonAll();
    }

    @Override
    public Long sumGiaTriHoaDonAll() {
        return hoaDonRepository.sumGiaTriHoaDonAll();
    }

    public void deleteHoaDonHoanTra() {
        // TODO Auto-generated method stub
        hoaDonRepository.deleteHoaDonHoanTra();
    }

    @Override
    public void guiHoaDonDienTu(HoaDon hoaDon, String url) {

        String from = "sshoeshopshoes@gmail.com";
        String to = hoaDon.getKhachHang().getEmail();
        String subject = "Thông tin hóa đơn";
        StringBuilder content = new StringBuilder();
        int index = 0;
        content.append("<p style=\"color: black;\"><b>Xin chào ").append(hoaDon.getNguoiNhan()).append(",</b></p>");

        if (hoaDon.getTrangThai() == 1) {

            content.append("<p style=\"color: black;\">Chúng tôi cảm ơn vì bạn đã lựa chọn mua sắm tại S-Shoe.</p>")
                    .append("<p style=\"color: black;\">Chúng tôi xin thông báo rằng đơn hàng của bạn đã được xác nhận và chúng tôi đang chuẩn bị hàng. Chúng tôi sẽ gửi cho đơn vị vận chuyển sớm nhất để bạn có thể có một trải nghiệm tốt.</p>")
                    .append("<p style=\"color: black;\">Để kiểm tra chi tiết đơn hàng, vui lòng <a href=\"[[URL]]\" target=\"_self\">nhấn vào đây</a> và nhập mã đơn hàng cùng với số điện thoại của bạn. Để đảm bảo tính chính xác, hãy sử dụng thông tin mà bạn đã cung cấp khi đặt hàng.</p>")
                    .append("<p style=\"color: black;\">Đây là mã đơn hàng và số điện thoại của bạn dựa trên thông tin bạn cung cấp trong đơn hàng</p>")
                    .append("<p style=\"color: black;\">Mã Đơn Hàng: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getMaHoaDon() + " </span>" + "</p>")
                    .append("<p style=\"color: black;\">Số Điện Thoại: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getSdtNguoiNhan() + " </span>" + "</p>") ;

        } else if (hoaDon.getTrangThai() == 2) {

            content.append("<p style=\"color: black;\">Chúng tôi xin thông báo rằng đơn hàng của bạn đã được chuyển cho đơn vị vận chuyển và đang trong quá trình vận chuyển đến địa chỉ của bạn.</p>")
                    .append("<p style=\"color: black;\">Xin cảm ơn vì đã lựa chọn sản phẩm của chúng tôi!</p>")
                    .append("<p style=\"color: black;\">Để kiểm tra chi tiết đơn hàng, vui lòng <a href=\"[[URL]]\" target=\"_self\">nhấn vào đây</a> và nhập mã đơn hàng cùng với số điện thoại của bạn. Để đảm bảo tính chính xác, hãy sử dụng thông tin mà bạn đã cung cấp khi đặt hàng.</p>")
                    .append("<p style=\"color: black;\">Đây là mã đơn hàng và số điện thoại của bạn dựa trên thông tin bạn cung cấp trong đơn hàng</p>")
                    .append("<p style=\"color: black;\">Mã Đơn Hàng: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getMaHoaDon() + " </span>" + "</p>")
                    .append("<p style=\"color: black;\">Số Điện Thoại: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getSdtNguoiNhan() + " </span>" + "</p>") ;

        } else if (hoaDon.getTrangThai() == 3) {

                content.append("<p style=\"color: black;\">Chúng tôi xin thông báo rằng đơn hàng của bạn đã được giao thành công đến địa chỉ của bạn!</p>")
                        .append("<p style=\"color: black;\">Bạn chỉ có thể yêu cầu hoàn trả trong vòng 7 ngày kể từ ngày nhận hàng. Sau thời gian này, chúng tôi không thể chấp nhận yêu cầu hoàn trả.</p>")
                        .append("<p style=\"color: black;\">Để biết rõ về chính sách hoàn trả bạn có thể liên hệ với chúng tôi hoặc vào trang của chúng tôi để xem.</p>")
                        .append("<p style=\"color: black;\">Để kiểm tra chi tiết đơn hàng, vui lòng <a href=\"[[URL]]\" target=\"_self\">nhấn vào đây</a> và nhập mã đơn hàng cùng với số điện thoại của bạn. Để đảm bảo tính chính xác, hãy sử dụng thông tin mà bạn đã cung cấp khi đặt hàng.</p>")
                        .append("<p style=\"color: black;\">Đây là mã đơn hàng và số điện thoại của bạn dựa trên thông tin bạn cung cấp trong đơn hàng</p>")
                        .append("<p style=\"color: black;\">Mã Đơn Hàng: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getMaHoaDon() + " </span>" + "</p>")
                        .append("<p style=\"color: black;\">Số Điện Thoại: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getSdtNguoiNhan() + " </span>" + "</p>") ;

        } else if (hoaDon.getTrangThai() == 5) {

            content.append("<p style=\"color: black;\">Chúng tôi xin chân thành xin lỗi vì sự bất tiện gây ra. Chúng tôi muốn thông báo rằng hóa đơn của bạn có mã " + "<span style=\"color:red\">" + hoaDon.getMaHoaDon() + "</span>" + " đã bị hủy do một số lý do khách quan. Dưới đây là thông tin chi tiết về tình trạng của hóa đơn:</p>")
                    .append("<p style=\"color: black;\">Lý Do Hủy Hóa Đơn: " + "<span style=\"font-weight: bold;color: black;\">" + hoaDon.getGhiChu() + "</span>" + "</p>")
                    .append("<p style=\"color: black;\">Để kiểm tra chi tiết đơn hàng, vui lòng <a href=\"[[URL]]\" target=\"_self\">nhấn vào đây</a> và nhập mã đơn hàng cùng với số điện thoại của bạn. Để đảm bảo tính chính xác, hãy sử dụng thông tin mà bạn đã cung cấp khi đặt hàng.</p>")
                    .append("<p style=\"color: black;\">Đây là mã đơn hàng và số điện thoại của bạn dựa trên thông tin bạn cung cấp trong đơn hàng</p>")
                    .append("<p style=\"color: black;\">Mã Đơn Hàng: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getMaHoaDon() + " </span>" + "</p>")
                    .append("<p style=\"color: black;\">Số Điện Thoại: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getSdtNguoiNhan() + " </span>" + "</p>")
                    .append("<p style=\"color: black;\">Nếu có bất kỳ thắc mắc nào hoặc bạn cần hỗ trợ thêm, đừng ngần ngại liên hệ với chúng tôi qua số điện thoại này 0377463664.</p>")
                    .append("<p style=\"color: black;\">Chúng tôi rất xin lỗi vì mọi phiền phức và hiểu rằng điều này có thể tạo ra sự bất tiện cho bạn. Chúng tôi cam kết tiếp tục nỗ lực để cải thiện dịch vụ và hy vọng được phục vụ bạn tốt hơn trong tương lai.</p>")
                    .append("<p style=\"color: black;\">Trân trọng,</p>")
                    .append("<p style=\"color: black;\">S-Shoe Shop</p>");

        } else if (hoaDon.getTrangThai() == 6) {

            content.append("<p style=\"color: black;\">Chúng tôi xin trân trọng thông báo rằng quá trình hoàn trả của bạn đã được xử lý thành công. Dưới đây là chi tiết về đơn hoàn trả:</p>")
                    .append("<p style=\"color: black;\">Để kiểm tra chi tiết đơn hàng, vui lòng <a href=\"[[URL]]\" target=\"_self\">nhấn vào đây</a> và nhập mã đơn hàng cùng với số điện thoại của bạn. Để đảm bảo tính chính xác, hãy sử dụng thông tin mà bạn đã cung cấp khi đặt hàng.</p>")
                    .append("<p style=\"color: black;\">Đây là mã đơn hàng và số điện thoại của bạn dựa trên thông tin bạn cung cấp trong đơn hàng</p>")
                    .append("<p style=\"color: black;\">Mã Đơn Hàng: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getMaHoaDon() + " </span>" + "</p>")
                    .append("<p style=\"color: black;\">Số Điện Thoại: " + "<span style=\"font-weight: bold;color: red;\"> " + hoaDon.getSdtNguoiNhan() + " </span>" + "</p>") ;
            content.append("<section class=\"row\" style=\"margin: auto;background-color: white\">" +
                    "    <div class=\"col-md-12\" style=\"text-align: center\">" +
                    "        <div class=\"mid\">" +
                    "            <h2>THÔNG TIN ĐƠN HÀNG</h2>" +
                    "            <h4>Mã đơn hàng: ").append("<span style=\"color:red\">" + hoaDon.getMaHoaDon() + "</span>").append(" </h4>" +
                    "            <h4>Số điện thoại: ").append("<span style=\"color:red\">" + hoaDon.getSdtNguoiNhan() + "</span>").append(" </h4>" +
                    "            <h4>Trạng thái: ").append("<span style=\"color:red\">" + hoaDon.getStringTrangThai() + "</span>").append(" </h4>" +
                    "        </div>" +
                    "    </div>" +
                    "        <div style=\"border-bottom: 1px solid black;margin-bottom:2%;\"></div>" +
                    "    <div class=\"col-md-12\" style=\"text-align: center;margin-bottom: 1rem;\">" +
                    "        <table class=\"table\" style=\"margin: auto;width: 100%\">" +
                    "            <thead>" +
                    "            <tr>" +
                    "                  <th scope=\"col\">STT</th>" +
                    "                <th scope=\"col\">Tên Sản Phẩm</th>" +
                    "                <th scope=\"col\">Màu Sắc</th>" +
                    "                <th scope=\"col\">Kích Cỡ</th>" +
                    "                <th scope=\"col\">Loại Đế</th>" +
                    "                <th scope=\"col\">Thương Hiệu</th>" +
                    "                <th scope=\"col\">Trạng Thái</th>" +
                    "                <th scope=\"col\">Số Lượng</th>" +
                    "                <th scope=\"col\">Giá</th>" +
                    "            </tr>");
            String trangThai = "";
            content
                    .append("</thead>")
                    .append("<tbody>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDon.getLstHoaDonChiTiet()) {
                if (hoaDonChiTiet.getTrangThai() == 0) {
                    trangThai = "Đã Nhận";
                } else {
                    trangThai = "Hoàn Trả";
                }
                content.append("<tr>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + index++ + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getMauSac().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getKichCo().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getLoaiDe().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getThuongHieu().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + trangThai + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSoLuong() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getDonGia() + " VND" + "</td>")
                        .append("</tr>");
            }
            content.append("</tbody>")
                    .append("</table>")
                    .append("</div>");

            content.append(
                    "    <div class=\"col-md-12\">" +
                            "        <div style=\"border-bottom: 1px solid black\"></div>" +
                            "        <div>" +
                            "            <p>" +
                            "                <span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền hàng:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">" + hoaDon.tongTienHoaDon() + " VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Phí vận chuyển:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">" + hoaDon.getPhiShip() + " VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tiền giảm:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\"><del>" + hoaDon.getTienGiam() + " VND</del></span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền hoàn trả:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;color:red;font-size: 1.1rem\">" + (hoaDon.tongTienHoaDonDaNhan()!=0?hoaDon.tongTienHoaDonHoanTra()-hoaDon.getTienGiam():(hoaDon.tongTienHoaDonHoanTra()-hoaDon.getTienGiam())) + " VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền thanh toán:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;color:red;font-size: 1.1rem\">" +(hoaDon.tongTienHoaDonDaNhan()!=0?hoaDon.tongTienHoaDonDaNhan()+hoaDon.getPhiShip():0) + " VND</span></p>" +
                            "        </div>" +
                            "    </div>" +
                            "</section>"
            );

            content.append(

                    "<div class=\"col-md-12\">" +
                            "<p style=\"color: black;\" class=\"email-content\">\n" +
                            "Cảm ơn bạn đã chọn S-Shoe! Nếu có bất kỳ thắc mắc nào hoặc cần hỗ trợ, hãy liên hệ với chúng tôi qua số 0377463664 để được hỗ trợ.\n" +
                            "</p>" +
                            "<p style=\"color: black;\">Trân trọng,</p>" +
                            "<p style=\"color: black;\">S-Shoe</p>" +
                            "</div>"

            );
        }

        if (hoaDon.getTrangThai() != 5 && hoaDon.getTrangThai() != 6) {
            content.append("<section class=\"row\" style=\"margin: auto;background-color: white\">" +
                    "    <div class=\"col-md-12\" style=\"text-align: center\">" +
                    "        <div class=\"mid\">" +
                    "            <h2>THÔNG TIN ĐƠN HÀNG</h2>" +
                    "            <h4>Mã đơn hàng: ").append("<span style=\"color:red\">" + hoaDon.getMaHoaDon() + "</span>").append(" </h4>" +
                    "            <h4>Số điện thoại: ").append("<span style=\"color:red\">" + hoaDon.getSdtNguoiNhan() + "</span>").append(" </h4>" +
                    "            <h4>Trạng thái: ").append("<span style=\"color:red\">" + hoaDon.getStringTrangThai() + "</span>").append(" </h4>" +
                    "        </div>" +
                    "    </div>" +
                    "        <div style=\"border-bottom: 1px solid black;margin-bottom:2%;\"></div>" +
                    "    <div class=\"col-md-12\" style=\"text-align: center;margin-bottom: 1rem;\">" +
                    "        <table class=\"table\" style=\"margin: auto;width: 100%\">" +
                    "            <thead>" +
                    "            <tr>" +
                    "                <th scope=\"col\">STT</th>" +
                    "                <th scope=\"col\">Tên Sản Phẩm</th>" +
                    "                <th scope=\"col\">Màu Sắc</th>" +
                    "                <th scope=\"col\">Kích Cỡ</th>" +
                    "                <th scope=\"col\">Loại Đế</th>" +
                    "                <th scope=\"col\">Thương Hiệu</th>" +
                    "                <th scope=\"col\">Số Lượng</th>" +
                    "                <th scope=\"col\">Giá</th>" +
                    "            </tr>");
            content
                    .append("</thead>")
                    .append("<tbody>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDon.getLstHoaDonChiTiet()) {
                content.append("<tr>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + index++ + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getMauSac().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getKichCo().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getLoaiDe().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getThuongHieu().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSoLuong() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getDonGia() + " VND" + "</td>")
                        .append("</tr>");
            }
            content.append("</tbody>")
                    .append("</table>")
                    .append("</div>");

            content.append(
                    "    <div class=\"col-md-12\">" +
                            "        <div style=\"border-bottom: 1px solid black\"></div>" +
                            "        <div>" +
                            "            <p>" +
                            "                <span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền hàng:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">" + hoaDon.tongTienHoaDon() + " VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Phí vận chuyển:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">" + hoaDon.getPhiShip() + " VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tiền giảm:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">" + hoaDon.getTienGiam() + " VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền thanh toán:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;color:red;font-size: 1.1rem\">" + hoaDon.tongTienHoaDonKhiGiam() + " VND</span></p>" +
                            "        </div>" +
                            "    </div>" +
                            "</section>"
            );

            content.append(

                    "<div class=\"col-md-12\">" +
                            "<p style=\"color: black;\" class=\"email-content\">\n" +
                            "Cảm ơn bạn đã chọn S-Shoe! Nếu có bất kỳ thắc mắc nào hoặc cần hỗ trợ, hãy liên hệ với chúng tôi qua số 0961515329 để được hỗ trợ.\n" +
                            "</p>" +
                            "<p style=\"color: black;\">Trân trọng,</p>" +
                            "<p style=\"color: black;\">S-Shoe</p>" +
                            "</div>"

            );
        }


        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            String siteUrl = url + "/tra-cuu-don-hang";
            String modifiedContent = content.toString().replace("[[URL]]", siteUrl);

            System.out.println(siteUrl);
            System.out.println(modifiedContent);

            content = new StringBuilder(modifiedContent);
            helper.setFrom(from, "S-Shoe");
            helper.setTo(to);
            helper.setSubject(subject);


            helper.setText(String.valueOf(content), true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public void saveOrUpdate(HoaDon hoaDonls) {
//        hoaDonRepository.save(hoaDonls);
//    }
}
