

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="Data_Structure.MyList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin Giảng viên</title>

    <style>
        /* Add your CSS styles here */
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

        h2 {
            color: #000080		;
            
        }
        h3 {
            color: #000080		;
            margin-top: -20px;
            font-size: 25px;
    		text-shadow: 2px 2px 4px #999999;
            
        }
       
        .container {
    	display: flex;
    	align-items: center;
    	gap: 10px;
  		}

  		.image {
    	order: 1;
  		}

  		.content {
    	order: 2;
  		}
  		p {
    	font-family: Times New Roman, serif;
    	font-size: 18px;
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
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        a {
            text-decoration: none;
            color: #0066cc;
        }

        a:hover {
            color: #004080;
        }
        .menu {
  background-color: #55acee;
  display: flex;
  justify-content: center;
  position: fixed;
  bottom: 0;
  width: 100%;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
          padding: 10px;
  
}



    .menu-inner {
        display: flex;
        justify-content: space-between;
    }

    .menu-item {
        margin-right: 10px;
    }

    .menu-item a {
        padding: 10px 20px;
        text-decoration: none;
        color: #333;
        background-color: transparent;
        border-radius: 10px;
            font-family: Arial, sans-serif;
            color: black;
              transition: background-color 0.3s ease-in-out, color 0.3s ease-in-out;
    }

    .menu-item a:hover {
         background-color: #3399FF	;
          color: #FFFFFF;
    }
        
    </style>
    <script>
    function openInNewTabWithSize(url) {
	    window.open(url, '_blank', 'width=1000' + ', height=1000' );
	}
    </script>
</head>
<body>
<h1>ĐĂNG KÝ MÔN HỌC</h1>

    <div style="display: flex; align-items: center;">
  <div class = "container">
    <img src="https://cdn-icons-png.flaticon.com/128/4140/4140037.png" alt="Ảnh giảng viên" style="width: 170px; height: 170px;">
  </div>
  <div style="margin-left: 20px;">
    <h2>Thông tin Giảng viên</h2>
    <p><strong>Mã Giảng viên:</strong> ${giangvien.getMaGiangVien()}</p>
    <p><strong>Họ và Tên:</strong> ${giangvien.hoTen}</p>
    <p><strong>Học vị:</strong> ${giangvien.hocVi}</p>
    <p><strong>Địa chỉ:</strong> ${giangvien.diaChi}</p>
    <p><strong>Số điện thoại:</strong> ${giangvien.sdt}</p>
    <p><strong>Mã Khoa:</strong> ${giangvien.maKhoa}</p>
  </div>
</div>
    <h3>Danh sách lớp</h3>   
    <table border="1" cellpadding ="5" cellspacing ="1">
	<tr>
		<th>Xem</th>
		<th>Mã giảng viên</th>
		<th>Tên môn</th>
		<th>Mã Lớp</th>
		<th>Mã môn</th>
		<th>Tiết</th>
		<th>Thứ</th>
		<th>Phòng</th>
	</tr>
	<c:if test="${DS_LopDay.list_LH.size() > 0}">
	 <c:forEach var="i" begin="0" end="${DS_LopDay.list_LH.size()-1}">
        <tr>
			<td>
    			<a href="GV_DS_HV?MaLop=${DS_LopDay.list_LH[i].getMaLop()}" onclick="openInNewTabWithSize('GV_DS_HV?MaLop=${DS_LopDay.list_LH[i].getMaLop()}'); return false">Xem</a>
			</td>			
			<td>${DS_LopDay.list_GV[i].getMaGiangVien()}</td>
			<td>${DS_LopDay.list_MH[i].getTenMonHoc()}</td>
            <td>${DS_LopDay.list_LH[i].getMaLop()}</td>
            <td>${DS_LopDay.list_LH[i].getMaMon()}</td>
            <td>${DS_LopDay.list_LH[i].getTiet()}</td>
            <td>${DS_LopDay.list_LH[i].getThu()}</td>
            <td>${DS_LopDay.list_LH[i].getMaPhong()}</td>
            
        </tr>
    </c:forEach>
    </c:if>
    </table>
    
    <div class="menu">
    <div class="menu-inner">
        <div class="menu-item">
            <a href="GV_DeXuat" onclick="openInNewTabWithSize('GV_DeXuat'); return false;">Đề xuất mở lớp</a>
        </div>
        <div class="menu-item">
            <a href="LogOut">Đăng xuất</a>
        </div>
    </div>
</div>
  

</body>
</html>
