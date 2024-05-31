<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="Data_Structure.MyList" %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DanhSachLop</title>




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
        
        h1 {
            top: 0;
            left: 0;
            width: 100%;
            color: 000000;
            padding: 10px;
            margin: 0;
            font-size: 45px;
            text-transform: uppercase;
    		text-decoration: underline;
    		text-shadow: 2px 2px 4px #999999;
        }

        h3 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }

        table, th, td {
  		border: 1px solid black;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        

        
        .error-message {
            display: none;
            color: red;
        }
        button{
        background-color: #2E8B57;
    	color: white;
    	padding: 10px 20px;
    	border: none;
    	border-radius: 4px;
    	cursor: pointer;
    	font-size: 18px;}
    	
    	button:hover {
    	background-color: #006241;
		}
		.error-message {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #2E8B57;
    border: 1px solid #000000;
    padding: 10px;
    color: white;
    max-width: 80%;
    border-radius: 5px; /* Đặt bán kính bo góc ở đây */
}
    </style>
</head>
<body >

<script>

	
    function handleCheckboxClick(checkboxIndex) {
        var checkboxes = document.getElementsByName('dangkyCheckbox');

        for (var i = 0; i < checkboxes.length; i++) {
            if (i !== checkboxIndex) {
                checkboxes[i].checked = false;
            }
        }
    }

    function submitSelectedCheckbox() {
        var checkboxes = document.getElementsByName('dangkyCheckbox');
        var selectedValue = null;

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedValue = checkboxes[i].value;
                break;
            }
        }

        if (selectedValue) {
            // Create a form and append a hidden input with the selected value
            var form = document.createElement('form');
            form.method = 'post';
            form.action = "${pageContext.request.contextPath}/DK_Lop"; 

            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'MaLop';
            input.value = selectedValue;

            form.appendChild(input);
            document.body.appendChild(form);

            // Submit the form
            form.submit();
        } 
        else {
            alert("Hãy chọn 1 lớp!");
        }
    }
    window.addEventListener('DOMContentLoaded', function() {
        var errorMessage = document.querySelector('.error-message');
        var displayDuration = 2000; // Thời gian hiển thị (miligiây)

        if (errorMessage && errorMessage.textContent.trim() !== '') {
            errorMessage.style.display = 'block';
            
            setTimeout(function() {
                errorMessage.style.display = 'none';
            }, displayDuration);
        }
    });

    
</script>
<h1>ĐĂNG KÝ MÔN HỌC</h1>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th>Đăng ký</th>
        <th>Mã Lớp</th>
        <th>Tên môn</th>
        <th>Số tín chỉ</th>
        <th>Tiết</th>
        <th>Thứ</th>
        <th>Phòng</th>
        <th>Giảng viên</th>
        <th>Mã khoa</th>
        <th>Mã môn</th>
        <th>Số học viên đăng ký</th>
    </tr>

    <c:if test="${DSLopDK.list_LH.size() > 0}">
        <c:forEach var="i" begin="0" end="${DSLopDK.list_LH.size() - 1}">
            <tr>
                <td>
                    <input type="checkbox" 
                           name="dangkyCheckbox" 
                           value="${DSLopDK.list_LH[i].getMaLop()}" 
                           id="checkbox${i}" 
                           
                           onclick="handleCheckboxClick(${i});">
                </td>
                <td>${DSLopDK.list_LH[i].getMaLop()}</td>
                <td>${DSLopDK.list_MH[i].getTenMonHoc()}</td>
                <td>${DSLopDK.list_MH[i].getSoTinChi()}</td>
                <td>${DSLopDK.list_LH[i].getTiet()}</td>
                <td>${DSLopDK.list_LH[i].getThu()}</td>
                <td>${DSLopDK.list_LH[i].getMaPhong()}</td>
                <td>${DSLopDK.list_GV[i].getHoTen()}</td>
                <td>${DSLopDK.list_MH[i].getMaKhoa()}</td>
                <td>${DSLopDK.list_MH[i].getMaMon()}</td>
                <td>${DSLopDK.list_LH[i].getSoHocVienDangKy()}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>

<button onclick="submitSelectedCheckbox()">${button_name}</button>

<div class="error-message">
    <c:if test="${not empty requestScope.err}">
        <label for="error-message">${requestScope.err}</label>
    </c:if>
</div>



</body>
</html>