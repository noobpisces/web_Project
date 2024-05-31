<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Lớp học</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    
    <script type="text/javascript">
        function testConfirmDialog(maLop) {
            var result = confirm("Bạn chắc chắn muốn xóa lớp học này?");
            if(result) {
                window.location.href = "deleteLopHoc?MaLop=" + maLop;
            } else {
                return false;
            }
        }
        window.addEventListener('DOMContentLoaded', function() {
            var errorMessage = document.querySelector('.errorString');
            var displayDuration = 2000; // Thời gian hiển thị (miligiây)
            if (errorMessage && errorMessage.textContent.trim() !== '') {
                errorMessage.style.display = 'block';
               
                setTimeout(function() {
                    errorMessage.style.display = 'none';
                }, displayDuration);
            }
        });
    </script>
    
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
       h2{
       color: #000080;
       text-shadow: 2px 2px 4px #999999;
       }

        

        table {
           border-collapse: collapse;
           width: 100%;
           margin: 20px auto;
       }
       table, th, td {
 		border: 1px solid black;
       }
       th, td {
           padding: 8px;
           text-align: left;
       }
       th {
           background-color: #4CAF50;
           color: white;
       }
       .paging {
        display: flex;
        justify-content: flex-start;
        margin-top: 20px;
        
    }

    .paging a {
        margin: 0 5px;
        padding: 5px 10px;
        background-color: #f0f0f0;
        border: 1px solid #ccc;
        text-decoration: none;
        color: #333;
    }

    .paging a:hover {
        background-color: #ccc;
    }

    .paging .current-page {
        font-weight: bold;
    }

       
        .errorString {
   display: none;
   position: fixed;
   top: 50%;
   left: 50%;
   transform: translate(-50%, -50%);
   background-color: red;
   border: 1px solid #000000;
   padding: 10px;
   color: white;
   max-width: 80%;
   border-radius: 5px; /* Đặt bán kính bo góc ở đây */
}


        .button {
            display: inline-block;
            padding: 6px 12px;
            margin: 5px;
            font-size: 14px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            white-space: nowrap;
            color: #fff;
            border-radius: 4px;
            border: none;
        }

        
        .menu {
        list-style-type: none;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: flex-end;
        list-style: none;
        
    }

    .menu-item {
        display: inline-block;
        margin-right: 10px;
    }

    .menu-item a {
        display: block;
        padding: 10px 20px;
        text-decoration: none;
        color: #fff;
        border-radius: 4px;
        margin-top:-45px;
    }
    .menu-item img {
        width: 30px;
        height: 30px;
    }
    .button-image {
    width: 24px; /* Thay đổi kích thước theo ý muốn */
    height: 24px; /* Thay đổi kích thước theo ý muốn */
    background-color: transparent; /* Xóa nền của ảnh */
}
    </style>
</head>
<body>
<h1>ĐĂNG KÝ MÔN HỌC</h1>

    
     <div style="display: flex; justify-content: center; margin-top: 20px;">
    <form action="findLopHoc" method="GET" style="display: flex; align-items: center;">
        <input type="text" id="txtMaLop" name="maLopHoc" style="width: 300px; margin: 0 auto; padding: 5px; text-align: left;" placeholder="Tìm kiếm lớp học...">
   <input type="hidden" name="index" value="1">
   <input type="image" src="https://cdn-icons-png.flaticon.com/128/9709/9709589.png" alt="Kính lúp" style="height: 20px; width: 20px; margin-left: 10px;">
    </form>
    </div>
        <h2>Danh sách lớp học</h2>
    
<div class="errorString">
   <c:if test="${not empty errorString}">
       <label for="errorString">${errorString}</label>
   </c:if>
</div>
    <table>
        <tr>
            <th>Mã lớp</th>
            <th>Tên lớp</th>
            <th>Mã giảng viên</th>
            <th>Mã môn</th>
            <th>Mã học kỳ</th>
            <th>Số học viên đăng ký</th>
            <th>Mã phòng</th>
            <th>Thứ</th>
            <th>Tiết</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${lophocList}" var="lophoc">
            <tr>
                <td>${lophoc.getMaLop()}</td>
                <td>${lophoc.getTenLop()}</td>
                <td>${lophoc.getMaGiangVien()}</td>
                <td>${lophoc.getMaMon()}</td>
                <td>${lophoc.getMaHocKy()}</td>
                <td>${lophoc.getSoHocVienDangKy()}</td>
                <td>${lophoc.getMaPhong()}</td>
                <td>${lophoc.getThu()}</td>
                <td>${lophoc.getTiet()}</td>
                <td>
                    <a href="updateLopHoc?MaLop=${lophoc.getMaLop()}" class="button">
                    <img src="https://cdn-icons-png.flaticon.com/128/1160/1160515.png" alt="Edit" class="button-image">
                    </a>
                </td>
                <td>
                    <a href="#" onclick="testConfirmDialog('${lophoc.getMaLop()}');" class="button">
                    <img src="https://cdn-icons-png.flaticon.com/128/9790/9790368.png" alt="Delete" class="button-image">
                    
                    </a>
                </td>    
            </tr>
        </c:forEach>
    </table>
<div class ="paging">
		<c:forEach begin ="1" end ="${end}" var="i">
				<a href="findLopHoc?index=${i}&maLopHoc=${maLopHoc}">${i}</a>	 
		
		</c:forEach>
	</div>


    <ul class="menu">
    <li class="menu-item">
        <a href="insertLopHoc"><img src="https://cdn-icons-png.flaticon.com/128/148/148764.png" alt="Thêm Lớp Học"></a>
    </li>
    <li class="menu-item">
        <a href="${pageContext.request.contextPath}/ADMIN_main"><img src="https://cdn-icons-png.flaticon.com/128/9138/9138369.png" alt="Cancel"></a>
    </li>
</ul>
</body>
</html>
