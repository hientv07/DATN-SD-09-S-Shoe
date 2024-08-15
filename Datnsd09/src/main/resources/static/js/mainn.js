$(document).ready(function () {
    // Sự kiện click nút giảm số lượng
    $('.js-btn-minus').on('click', function () {
        capNhatSoLuong(this, -1);
    });

    // Sự kiện click nút tăng số lượng
    $('.js-btn-plus').on('click', function () {
        capNhatSoLuong(this, 1);
    });

    // Sự kiện thay đổi giá trị của input
    $('input[id^="soLuong_"]').on('input', function () {
        // Không làm gì khi input thay đổi
    });

    // Sự kiện mất focus (blur) trên input
    $('input[id^="soLuong_"]').on('blur', function () {
        capNhatSoLuong(this, 0); // Giá trị 0 để chỉ cập nhật số lượng, không tăng/giảm
    });

    function capNhatSoLuong(button, soLuongThem) {
        // var id = $(button).closest('div.input-group').find('input[id^="soLuong_"]').attr('id').split('_')[1];
        // var soLuongHienTai = parseInt($(button).closest('div.input-group').find('input[id^="soLuong_"]').val());
        // var soLuongMoi = soLuongHienTai + soLuongThem;
        var input = $(button).closest('div.input-group').find('input[id^="soLuong_"]');
        var id = input.attr('id').split('_')[1];
        var max = input.attr('id').split('_')[2];
        var min = parseInt(input.attr('min'));
        var soLuongHienTai = parseInt(input.val());
        var soLuongMoi = soLuongHienTai + soLuongThem;


        if (max && soLuongMoi > max) {
            soLuongMoi = max;
        }
        if (min && soLuongMoi < min) {
            soLuongMoi = min;
        }

        // Tiếp tục với cập nhật số lượng
        $.ajax({
            url: '/user/cart/update/' + id,
            type: 'GET',
            data: {soLuong: soLuongMoi},
            success: function (data) {
                input.val(soLuongMoi);
                // Cập nhật giá trị mới của tổng tiền
                console.log('Cập nhật thành công');
                // Lưu vị trí trang khi số lượng được cập nhật
                scrollPosition = window.pageYOffset;
                // Load lại trang
                location.reload();
            },
            error: function (error) {
                console.error('Lỗi khi cập nhật:', error);
            }
        });

    }
});


$(document).ready(function () {
    // Kích hoạt tooltip

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    var tongGia = 0; // Biến để lưu tổng giá

    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
        tinhTongGia();
    });

    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
        tinhTongGia();
    });


    // Hàm tính tổng giá
    function tinhTongGia() {
        tongGia = 0;
        checkbox.each(function () {
            if (this.checked) {
                var row = $(this).closest('tr');
                var giaSanPham = parseFloat(row.find('td:eq(5)').text().replace(/[,.]/g, ''));
                tongGia += giaSanPham;
            }
        });
        // Hiển thị tổng giá hoặc thực hiện bất kỳ hành động nào khác với nó
        console.log("Tổng Giá: " + tongGia);
        var tongThanhToan = document.getElementById("tongTienCart");
        tongThanhToan.innerHTML = '';
        tongThanhToan.innerText = tongGia.toLocaleString('vi-VN')+ ' VNĐ';
    }

});

// $(document).ready(function () {
//     // Kích hoạt tooltip
//
//     // Select/Deselect checkboxes
//     var checkbox = $('table tbody input[type="checkbox"]');
//     var tongGia = 0; // Biến để lưu tổng giá
//
//     $("#selectAll").click(function () {
//         if (this.checked) {
//             checkbox.each(function () {
//                 this.checked = true;
//             });
//         } else {
//             checkbox.each(function () {
//                 this.checked = false;
//             });
//         }
//         tinhTongGia();
//     });
//
//     checkbox.click(function () {
//         if (!this.checked) {
//             $("#selectAll").prop("checked", false);
//         }
//         tinhTongGia();
//     });
//
//     // Hàm tính tổng giá
//     function tinhTongGia() {
//         tongGia = 0;
//         checkbox.each(function () {
//             if (this.checked) {
//                 var row = $(this).closest('tr');
//                 var giaSanPham = parseFloat(row.find('td:eq(5)').text().replace(/,/g, ''));
//                 tongGia += giaSanPham;
//             }
//         });
//
//         // Hiển thị tổng giá hoặc thực hiện bất kỳ hành động nào khác với nó
//         console.log("Tổng Giá: " + tongGia.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }));
//         var tongThanhToan = document.getElementById("tongTienCart");
//         tongThanhToan.innerHTML = '';
//         tongThanhToan.innerText = tongGia.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
//     }
//
// });


// Lấy URL hiện tại
var url = window.location.href;

// Tìm kiếm chuỗi query parameters trong URL
var queryString = url.split('?')[1];

// Tách các tham số
var params = new URLSearchParams(queryString);

// Lấy giá trị của tham số 'checkGHCT'
var checkGHCTValue = params.get('checkGHCT');


if (checkGHCTValue.startsWith('true')) {
    // Lấy số lượng từ chuỗi sau "true"
    var quantity = parseInt(checkGHCTValue.substring(4));

    // Thực hiện hành động khi giá trị bắt đầu bằng 'true'
    console.log('checkGHCT is true');

    $(document).ready(function () {
        // Chọn checkbox dựa trên số lượng
        var selectedCheckboxes = $('input[name="options[]"]').slice(0, quantity);
        selectedCheckboxes.prop('checked', true);

        // Lấy giá trị cột thứ 5 của hàng chứa checkbox đầu tiên được chọn
        var giaTriCotThu5 = 0; // Khởi tạo giá trị tổng thanh toán

        selectedCheckboxes.each(function () {
            giaTriCotThu5 += parseInt($(this).closest('tr').find('td:eq(5)').text().replace(/[,.]/g, '')) || 0;
        });

        // Cập nhật giá trị tổng thanh toán
        var tongThanhToan = document.getElementById("tongTienCart");
        tongThanhToan.innerHTML = '';
        tongThanhToan.innerText = giaTriCotThu5.toLocaleString('vi-VN')+ ' VNĐ';
    });

} else {
    // Thực hiện hành động khi giá trị không phải 'true'
    console.log('checkGHCT is not true');
}
