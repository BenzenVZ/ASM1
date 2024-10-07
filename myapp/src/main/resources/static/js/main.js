// Hàm kiểm tra nếu người dùng chọn thêm phòng ban mới
function toggleDepartmentFields() {
    var departmentSelect = document.getElementById("departmentSelect");
    var newDepartmentFields = document.getElementById("newDepartmentFields");
    if (departmentSelect.value == "new") {
        newDepartmentFields.style.display = "block";  // Hiển thị phần thêm mới phòng ban
    } else {
        newDepartmentFields.style.display = "none";  // Ẩn phần thêm mới phòng ban
    }
}