<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm mới Học viên</title>

    <!-- Thêm thư viện Moment.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

    <style>
        body {
           font-family: Arial, sans-serif;
 			background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOlntPB3M-u_0R38QcDWOIkZflHHRmeeypGw&usqp=CAU");
           text-align: center;
           margin: 0;
           padding: 0;
           background-repeat: no-repeat;
           background-size: cover;
           height: auto;
       }
        h2{
       color: #000080;
       text-shadow: 2px 2px 4px #999999;
       }

        h3 {
            color: #333;
        }

        table {
            margin-top: 20px;
        }

        table td {
            padding: 5px;
        }

        .error {
            color: red;
        }
     
form {
  margin: 0 auto; /* Đặt margin top và bottom là 0, margin left và right tự động căn giữa */
  max-width: 300px; /* Giới hạn độ rộng tối đa của form */
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #6495ED;
  font-family: Arial, sans-serif;
  font-size: 14px;
  font-weight: bold;
  color: #333;
  text-align: left;
  display: flex;
  flex-direction: column;
  align-items: center;
}

        .form-row {
            margin-bottom: 10px;
        }

        .form-row label {
            display: flex;
  flex-direction: column;
            width: 100px;
              text-align: left;
            
     
        }

        .form-row input[type="text"],
        .form-row input[type="submit"],
        .form-row a {
            padding: 5px 10px;
            font-size: 14px;
            border-radius: 4px;
        }

        .form-row input[type="submit"] {
            background-color: #337ab7;
            color: #fff;
            border: none;
        }

        .form-row input[type="submit"]:hover {
            background-color: #23527c;
        }

        .form-row a {
            text-decoration: none;
            color: #333;
            border: 1px solid #333;
        }

        .form-row a:hover {
            background-color: #f2f2f2;
        }
        td.button-container {
    text-align: center; /* Căn giữa nội dung theo chiều ngang */
     display: flex;
    justify-content: space-between;
    align-items: center;
    
  }
    </style>

    <script type="text/javascript">
        function validateForm() {
            var x = document.forms["insertHocVienForm"]["hoTen"].value;
            if (x == "") {
                alert("Vui lòng nhập họ tên");
                return false;
            } else {
                return true;
            }
        }

        function openDatePicker() {
            var datePicker = document.getElementById("datePicker");
            datePicker.readOnly = false;
            datePicker.value = moment().format("DD-MM-YYYY");
        }

        function closeDatePicker() {
            var datePicker = document.getElementById("datePicker");
            datePicker.readOnly = true;
        }
    </script>
</head>
<body>
    <h2>Thêm mới Học viên</h2>
            <img src="https://cdn-icons-png.flaticon.com/128/7218/7218043.png" alt="ảnh" />
    
    <p class="error"><c:out value="${errorString}" /></p>
    <form method="POST" action="${pageContext.request.contextPath}/insertHocVien" name="insertHocVienForm" onsubmit="return validateForm()">

        <div class="form-row">
            <label>Mã Học viên</label>
            <input type="text" name="maHocVien" />
        </div>
        <div class="form-row">
            <label>Họ và tên</label>
            <input type="text" name="hoTen" required />
        </div>
        <div class="form-row">
            <label>Ngày sinh</label>
            <input id="datePicker" type="text" name="ngaySinh" readonly="readonly" onclick="openDatePicker()" onblur="closeDatePicker()" />
        </div>
        <div class="form-row">
            <label>Địa chỉ</label>
            <input type="text" name="diaChi" />
        </div>
        <div class="form-row">
            <label>Quê quán</label>
            <input type="text" name="queQuan" />
        </div>
        <div class="form-row">
            <label>Mã khoa</label>
            <input type="text" name="maKhoa" />
        </div>
        <tr>
  <td colspan="2" class="button-container" >
  <div>
    <input type="image" src="https://cdn-icons-png.flaticon.com/128/489/489707.png" alt="Lưu" style="width: 40px; height: 40px; margin-right: 30px;" />
    <a href="${pageContext.request.contextPath}/ADMIN_HV">
      <img src="https://cdn-icons-png.flaticon.com/128/753/753345.png" alt="Hủy" style="width: 40px; height: 40px; margin-left: 30px;" />
    </a>
    </div>
  </td>
  
</tr>
    </form>
</body>
</html>
