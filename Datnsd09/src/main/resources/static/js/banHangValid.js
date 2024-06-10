
function validatePhoneNumber(phoneNumber) {
    // Biểu thức chính quy để kiểm tra số điện thoại cơ bản
    var phonePattern = /((09|03|07|08|05)+([0-9]{8})\b)/g;

    // Kiểm tra xem số điện thoại có khớp với pattern không
    console.log(phoneNumber)
    return phonePattern.test(phoneNumber);
}
function validateAndSubmit(slg, sl, idCtsp) {

    var soLuong = document.getElementById('soLuongEdit' + idCtsp).value;
    var soLuongGoc = slg;
    if (isNaN(soLuong) || soLuong < 0) {
        console.log('eeee')
        document.getElementById('errorSoLuongEdit').innerHTML = "Số lượng không hợp lệ!"

        // alert("Vui lòng nhập một số nguyên không âm.");
    } else if (parseInt(soLuong) > parseInt(soLuongGoc)) {
        document.getElementById('errorSoLuongEdit').innerHTML = "Số lượng không được lớn hơn số lượng trong kho"

        // alert("Số lượng không được lớn hơn số lượng hiện tại (" + soLuongGoc + ").");
    } else if (soLuong === "") {
        document.getElementById('errorSoLuongEdit').innerHTML = "Vui lòng nhập số lượng"
        // alert("Vui lòng nhập số lượng");
    } else {
        // alert("Số lượng hợp lệ: " + soLuong);

        // Nếu số lượng hợp lệ, thực hiện submit form
        document.getElementById("myForm" + idCtsp).submit();
    }
}

function validateAndSubmitTraHang(slg, sl, idCtsp) {

    var soLuong = document.getElementById('soLuongEditTra' + idCtsp).value;
    var soLuongGoc = slg;
    if (isNaN(soLuong) || soLuong < 0) {
        document.getElementById('errorSoLuongEditTra').innerHTML = "Số lượng không hợp lệ!"

        // alert("Vui lòng nhập một số nguyên không âm.");
    } else if (parseInt(soLuong) > parseInt(soLuongGoc)) {
        document.getElementById('errorSoLuongEditTra').innerHTML = "Số lượng không được lớn hơn số lượng sản phẩm"

        // alert("Số lượng không được lớn hơn số lượng hiện tại (" + soLuongGoc + ").");
    } else if (soLuong === "") {
        document.getElementById('errorSoLuongEditTra').innerHTML = "Vui lòng nhập số lượng"
        // alert("Vui lòng nhập số lượng");
    } else {
        // alert("Số lượng hợp lệ: " + soLuong);

        // Nếu số lượng hợp lệ, thực hiện submit form
        document.getElementById("myForm" + idCtsp).submit();
    }
}
function validGhiChuThanhToan() {
    var textarea = document.getElementById("nhapGhiChu");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChuThanhToan")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";

        
        return true;
    } else {
        
    }
}

function validGhiChuHuy() {
    var textarea = document.getElementById("ghiChuHuy");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChuHuy")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";
        errorGhiChu.style.color = 'red';
        // alert('s')
        return false;
    } else {
        // alert('vd')
    }
}

function validGhiChuRollback() {
    var textarea = document.getElementById("ghiChuRollback");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChuRollback")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";
        errorGhiChu.style.color = 'red';
        // alert('s')
        return false;
    } else {
        // alert('vd')
    }
}

function validGhiChuHoanTra() {
    var textarea = document.getElementById("ghiChuHoanTra");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChuHoanTra")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";
        errorGhiChu.style.color = 'red';
        // alert('s')
        return false;
    } else {
        // alert('vd')
    }
}


function validateThanhToan() {

    // var inputGhiChuThanhToan = document.getElementById('nhapGhiChu')
    var errorGhiChu = document.getElementById("errorGhiChuThanhToan")

    // if (inputGhiChuThanhToan.value.trim() === '') {
    //     errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";


    //     return false;
    // }
    // return false;
    handleButtonClick('thanhToan');

    const checkGiaoHang = document.getElementById('toggleVisibility').checked

    const inputHoVaTen = document.getElementById('inputHoVaTen').value
    const inputSoDienThoai = document.getElementById('inputSoDienThoai').value
    const citySelect = document.getElementById('citySelect').value
    const districtSelect = document.getElementById('districtSelect').value
    const wardSelect = document.getElementById('wardSelect').value
    const inputDiaChiCuThe = document.getElementById('inputDiaChiCuThe').value

    const errorInputDiaChiCuThe = document.getElementById('errorInputDiaChiCuThe')
    const errorInputHoVaTen = document.getElementById('errorInputHoVaTen')
    const errorWardSelect = document.getElementById('errorWardSelect')
    const errorInputSoDienThoai = document.getElementById('errorInputSoDienThoai')
    const errorDistrictSelect = document.getElementById('errorDistrictSelect')
    const errorCitySelect = document.getElementById('errorCitySelect')
    var checkTong = true;
    if (checkGiaoHang) {
        if (inputHoVaTen === '') {
            errorInputHoVaTen.innerHTML = 'Vui lòng nhập họ tên';
            checkTong = false;
        } else {
            errorInputHoVaTen.innerHTML = '';
        }
        if (inputDiaChiCuThe === '') {
            errorInputDiaChiCuThe.innerHTML = 'Vui lòng nhập địa chỉ cụ thể';
            checkTong = false;
        } else {
            errorInputDiaChiCuThe.innerHTML = '';
        }
        if (inputSoDienThoai === '') {
            errorInputSoDienThoai.innerHTML = 'Vui lòng nhập số điện thoại';
            checkTong = false;

        } else if (!validatePhoneNumber(inputSoDienThoai)) {
            checkTong = false;
            errorInputSoDienThoai.innerHTML = 'Số điện thoại không đúng định dạng';
        } else {
            errorInputSoDienThoai.innerHTML = '';
        }



        if (citySelect === '') {
            errorCitySelect.innerHTML = 'Vui lòng chọn thành phố';
            checkTong = false;
        } else {
            errorCitySelect.innerHTML = '';
        }
        if (districtSelect === '') {
            errorDistrictSelect.innerHTML = 'Vui lòng chọn quận/huyện';
            checkTong = false;
        } else {
            errorDistrictSelect.innerHTML = '';
        }
        if (wardSelect === '') {
            errorWardSelect.innerHTML = 'Vui lòng chọn phường/xã';
            checkTong = false;
        } else {
            errorWardSelect.innerHTML = '';
        }

    }
    if (checkTong) {
        $('#ghiChuThanhToan').modal('show')
    }
    return checkTong;


}
function validCapNhat() {

    const inputHoVaTen = document.getElementById('inputHoVaTen').value
    const inputSoDienThoai = document.getElementById('inputSoDienThoai').value
    const citySelect = document.getElementById('citySelect').value
    const districtSelect = document.getElementById('districtSelect').value
    const wardSelect = document.getElementById('wardSelect').value
    const inputDiaChiCuThe = document.getElementById('inputDiaChiCuThe').value

    const errorInputDiaChiCuThe = document.getElementById('errorInputDiaChiCuThe')
    const errorInputHoVaTen = document.getElementById('errorInputHoVaTen')
    const errorWardSelect = document.getElementById('errorWardSelect')
    const errorInputSoDienThoai = document.getElementById('errorInputSoDienThoai')
    const errorDistrictSelect = document.getElementById('errorDistrictSelect')
    const errorCitySelect = document.getElementById('errorCitySelect')
    var checkTong = true;

    if (inputHoVaTen === '') {
        errorInputHoVaTen.innerHTML = 'Vui lòng nhập họ tên';
        checkTong = false;
    } else {
        errorInputHoVaTen.innerHTML = '';
    }
    if (inputDiaChiCuThe === '') {
        errorInputDiaChiCuThe.innerHTML = 'Vui lòng nhập địa chỉ cụ thể';
        checkTong = false;
    } else {
        errorInputDiaChiCuThe.innerHTML = '';
    }
    if (inputSoDienThoai === '') {
        errorInputSoDienThoai.innerHTML = 'Vui lòng nhập số điện thoại';
        checkTong = false;

    } else if (!validatePhoneNumber(inputSoDienThoai)) {
        checkTong = false;
        errorInputSoDienThoai.innerHTML = 'Số điện thoại không đúng định dạng';
    } else {
        errorInputSoDienThoai.innerHTML = '';
    }



    if (citySelect === '') {
        errorCitySelect.innerHTML = 'Vui lòng chọn thành phố';
        checkTong = false;
    } else {
        errorCitySelect.innerHTML = '';
    }
    if (districtSelect === '') {
        errorDistrictSelect.innerHTML = 'Vui lòng chọn quận/huyện';
        checkTong = false;
    } else {
        errorDistrictSelect.innerHTML = '';
    }
    if (wardSelect === '') {
        errorWardSelect.innerHTML = 'Vui lòng chọn phường/xã';
        checkTong = false;
    } else {
        errorWardSelect.innerHTML = '';
    }



    return checkTong;

}
function handleButtonClick(idBtn) {
    var button = document.getElementById(idBtn); // Lấy button
    console.log('handleButtonClick')
    if (button.getAttribute('disabled') === 'disabled') {
        // Nếu button đã được vô hiệu hóa, không làm gì cả (để ngăn chặn spam)
        return;
    }

    // Vô hiệu hóa button sau khi được nhấn
    button.setAttribute('disabled', 'disabled');

    // Gửi yêu cầu hoặc thực hiện công việc cần thiết ở đây

    // Sau một khoảng thời gian, có thể muốn kích hoạt lại button (nếu thích)
    setTimeout(function () {
        button.removeAttribute('disabled'); // Kích hoạt lại button sau một khoảng thời gian
    }, 1500); // Ví dụ: kích hoạt lại sau 3 giây (3000 milliseconds)
}

const inputElement = document.getElementById('tienKhachTra'); // Thay 'inputId' bằng ID thực của ô input
const tongTien = document.getElementById('tongAll');
const traLai = document.getElementById('traLai');
inputElement.addEventListener('input', function (event) {
    const newValue = event.target.value;
    traLai.innerHTML = (newValue - tongTien.value)

    // Thực hiện các hành động khác ngay khi giá trị thay đổi
});

function resetTienKhachTra() {
    const inputElement = document.getElementById('tienKhachTra'); // Thay 'inputId' bằng ID thực của ô input
    const tongTien = document.getElementById('tongAll');
    const traLai = document.getElementById('traLai');
    inputElement.value = null
    traLai.innerHTML = '0'
}
function resetTienKhachTra2() {
    const inputElement2 = document.getElementById('tongAll'); // Thay 'inputId' bằng ID thực của ô input

    inputElement2.addEventListener('input', function (event) {
        const newValue = event.target.value;
        console.log('Giá trị mới tt:', newValue);

        // Thực hiện các hành động khác sau khi giá trị thay đổi
    });
    const inputElement = document.getElementById('tienKhachTra'); // Thay 'inputId' bằng ID thực của ô input
    const tongTien = document.getElementById('tongAll');
    const traLai = document.getElementById('traLai');
    inputElement.value = null
    traLai.innerHTML = '0'
}

function checkValidGhiChu() {
    var textarea = document.getElementById("ghiChu");
    var text = textarea.value.trim(); // Lấy giá trị và loại bỏ khoảng trắng
    var errorGhiChu = document.getElementById("errorGhiChu")
    if (text === '') {
        errorGhiChu.innerHTML = "Vui lòng nhập ghi chú";


        return false;
    } else {
        return true;
    }
}

function getNameXaPhuong(idQuanHuyen, idXaPhuong) {
    fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${idQuanHuyen}`, {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            // populateSelect(wardSelect, data.data, 'WardCode', 'WardName');
            data.data.forEach(x => {
                if (x.WardCode === idXaPhuong) {
                    console.log(x.WardName)
                    document.getElementById('phuongXaChonDc').innerHTML = x.WardName
                }
            })
            // console.log(data.data)

        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu quận:', error);
        });
}

function getNameQuanHuyenById(idThanhPho, idQuanHuyen) {
    fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${idThanhPho}`, {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            data.data.forEach(q => {
                if (q.DistrictID === idQuanHuyen) {
                    console.log(q.DistrictName)
                }
            });
            // console.log(data.data)


        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu quận:', error);
        });
}

function getNameThanhPhoById(idThanhPho) {
    var tenThanhPho = ''

    fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/province`, {
        method: 'GET',
        headers: {
            'token': 'd900c67f-742d-11ee-96dc-de6f804954c9',
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu khi API trả về thành công
            var lst = data.data
            lst.forEach(dc => {
                // console.log(dc.ProvinceID)
                if (idThanhPho === dc.ProvinceID) {
                    tenThanhPho = dc.ProvinceName
                    console.log(tenThanhPho)
                }


            });


        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu quận:', error);
        });

}


function validThemKhachHang() {
    var tenTaiKhoan = document.getElementById('tenTaiKhoan')
    var hoVaTen = document.getElementById('hoVaTen')
    var ngaySinh = document.getElementById('ngaySinh')
    var soDienThoai = document.getElementById('soDienThoai')
    var email = document.getElementById('email')
    var gioiTinh1 = document.getElementById('gioiTinh1')
    var gioiTinh2 = document.getElementById('gioiTinh2')






    var errorTenTaiKhoan = document.getElementById('errorTenTaiKhoan')
    var errorHoVaTen = document.getElementById('errorHoVaTen')
    var errorNgaySinh = document.getElementById('errorNgaySinh')
    var errorsoDienThoai = document.getElementById('errorsoDienThoai')
    var errorEmail = document.getElementById('errorEmail')
    var check = true;
    var d = new Date(ngaySinh.value);

    if (tenTaiKhoan.value === '') {
        errorTenTaiKhoan.innerHTML = 'Vui lòng nhập tên tài khoản'
        check = false;
    } else {
        errorTenTaiKhoan.innerHTML = ''
    }
    if (hoVaTen.value === '') {
        errorHoVaTen.innerHTML = 'Vui lòng nhập tên khách hàng'
        check = false;
    } else {
        errorHoVaTen.innerHTML = ''
    }

    if (ngaySinh.value === '') {
        errorNgaySinh.innerHTML = 'Vui lòng nhập ngày sinh'
        check = false;
    } else if (!isValidNgaySinh(ngaySinh.value)) {
        errorNgaySinh.innerHTML = 'Năm sinh phải lớn hơn 1923'
        check = false;
    } else if (!isPastOrPresent(d)) {
        errorNgaySinh.innerHTML = 'Ngày không phải là ngày trong quá khứ hoặc ngày hiện tại'
        alert(isValidNgaySinh(ngaySinh.value))
        check = false;
    } else {
        errorNgaySinh.innerHTML = ''
    }


    if (soDienThoai.value === '') {
        errorsoDienThoai.innerHTML = 'Vui lòng nhập số điện thoại'
        check = false;
    } else if (!checkPhoneNumber(soDienThoai.value)) {
        errorsoDienThoai.innerHTML = 'Số điện thoại không đúng định dạng'
        check = false;
    } else {
        errorsoDienThoai.innerHTML = ''
    }
    if (email.value === '') {
        errorEmail.innerHTML = 'Vui lòng nhập email'
        check = false;
    } else if (!testEmailFormat(email.value)) {
        errorEmail.innerHTML = 'Email không đúng định dạng'
        check = false;
    } else {
        errorEmail.innerHTML = ''
    }


    return check;
}

function isValidNgaySinh(ngaySinh) {
    if (ngaySinh) {
        const cal = new Date(ngaySinh);
        const year = cal.getFullYear();
        return year >= 1923;
    }
    return true; // Trường ngày sinh có thể để trống
}

function isPastOrPresent(inputDate) {
    const today = new Date(); // Lấy ngày hiện tại
    return inputDate <= today;
}

function checkPhoneNumber(phoneNumber) {
    const regex = /^(0[35789][0-9]{8,9})$/;
    return regex.test(phoneNumber);
}

function testEmailFormat(email) {
    const regex = /^[A-Za-z0-9+_.-]+@(.+)$/;
    return regex.test(email);
}


