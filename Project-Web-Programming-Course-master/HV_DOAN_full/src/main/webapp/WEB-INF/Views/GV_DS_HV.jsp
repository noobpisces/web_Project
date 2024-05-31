<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Data_Structure.MyList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DS_GV</title>

    <!-- Include your CSS file -->

    <style>
        /* You can also include inline styles here if needed */
        body {
            font-family: Arial, sans-serif;
  			background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOlntPB3M-u_0R38QcDWOIkZflHHRmeeypGw&usqp=CAU");
            text-align: center;
            margin: 0;
            padding: 0;
            background-repeat: no-repeat;
            background-size: cover;
            height: auto;}
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
        h2 {
            color: #000080		;
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

        /* Add more styles as needed for your specific layout */
    </style>
    <Script>
   		 function showFileDialog() {
        	var filePath = prompt("Chọn đường dẫn để xuất file:", "");
        	if (filePath !== null) {
            // Ở đây, bạn có thể thực hiện các bước cần thiết với đường dẫn đã chọn.
            // Ví dụ: Gửi đường dẫn này đến máy chủ để xử lý, hoặc thực hiện các hành động khác.
        		 console.log("Đường dẫn đã chọn: " + filePath);
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
    </Script>
</head>
<h1>ĐĂNNG KÝ MÔN HỌC</h1>
<h2>Danh sách học viên</h2>
<body>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Mã lớp</th>
            <th>Mã học viên</th>
            <th>Họ tên</th>
        </tr>
        <c:if test="${DS_HV.list_HV.size() > 0}">
            <c:forEach var="i" begin="0" end="${DS_HV.list_HV.size()-1}">
                <tr>
                    <td>${DS_HV.list_LH[i].getMaLop()}</td>
                    <td>${DS_HV.list_HV[i].getMaHocVien()}</td>
                    <td>${DS_HV.list_HV[i].getHoTen()}</td>
                </tr>
            </c:forEach>
            
        </c:if>
    </table>
    <a href="Xuat_Excel?MaLop=${DS_HV.list_LH[0].getMaLop()}" >Xuất file</a>
    
    
<div class="error-message">
    <c:if test="${not empty requestScope.err}">
        <label for="error-message">${requestScope.err}</label>
    </c:if>
</div>

</body>
</html>
