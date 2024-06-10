// Biến global để lưu trữ button được chọn cho từng nhóm
var selectedButtons = {
  group1: null,
  group2: null,
};

function toggleButton(buttonId, group) {
  // Lấy ra ID của button được chọn trong nhóm
  var selectedButtonId = selectedButtons[group];

  // Nếu button trước đó đã được chọn, loại bỏ lớp 'active'
  if (selectedButtonId !== null) {
    var previousButton = document.getElementById(selectedButtonId);
    previousButton.classList.remove("active");
  }

  // Thêm lớp 'active' vào button được chọn và cập nhật giá trị của biến selectedButtons
  var selectedButton = document.getElementById(buttonId);
  selectedButton.classList.add("active");
  selectedButtons[group] = buttonId;
}
