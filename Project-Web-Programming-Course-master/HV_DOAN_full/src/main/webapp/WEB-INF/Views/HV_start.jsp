<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="Data_Structure.MyList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home HV</title>
<meta http-equiv="Content-Type” content="text/html; charset=UTF-8"/>
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

        h3 {
            color: #333;
              font-family: 'Times New Roman', sans-serif; /* Thay 'Your Font' bằng font bạn muốn sử dụng */
            
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
  		
  		/* Navbar styling */
.menu {
        background-color: #55acee;
        padding: 10px;
    }

    .menu-item {
        display: inline-block;
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
            color: #3498db;
        }

        a:hover {
            color: #2980b9;
        }

        p {
            color: red;
        }

        label {
            color: red;
        }
        .content1 {
    margin-bottom: 130px; /* Đảm bảo không bị che phủ bởi menu */
}
       
        
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    
	
</head>
<body>
<script >
	function DeleteConfirmDialog(MaLop) {
    	var result = confirm("Bạn chắc chắn muốn xóa đăng ký lớp học này?");
    	if (result) {
        	window.location.href = "HV_XoaDK?MaLop=" + MaLop;
    	}
    	return result;
		}
	function openInNewTabWithSize(url) {
	    window.open(url, '_blank', 'width=1000' + ', height=1000' );
	}
	
    </script>
<h1>ĐĂNG KÝ MÔN HỌC</h1>
<div class="container">
  <img src="https://cdn-icons-png.flaticon.com/128/9408/9408099.png" alt="Ảnh học viên" class="image">
  <div class="content">
    <h3>${HV.getHoTen()}</h3>
    <h3>Khoa: ${HV.getMaKhoa()}</h3>
    <h3>Mã học viên: ${HV.getMaHocVien()}</h3>
  </div>
</div>


	<div style="display: flex; justify-content: flex-end; margin-top: -70px;">
  <form action="${pageContext.request.contextPath}/DS_Lop" method="get" style="display: flex; align-items: center;">
    <input type="text" id="MaMon" name="MaMon" style="width: 300px; margin: 0 auto; padding: 5px; text-align: left;" placeholder="Tìm kiếm môn học...">
    <input type="hidden" name="action" value="dangky">
    <input type="image" src="https://cdn-icons-png.flaticon.com/128/9709/9709589.png" alt="Kính lúp" style="height: 20px; width: 20px; margin-left: 10px;">
  </form>
</div>



<h2>Danh sách môn</h2>
	
	<p style="color: red;"> ${errorString}</p>  
<div class="content1">
<table border="1" cellpadding ="5" cellspacing ="1">
	<tr>
		<th>Số tín chỉ</th>
		<th>Tên môn</th>
		<th>Mã môn</th>
		<th>Danh sách lớp mở</th>
	</tr>
	<c:forEach items="${DS_MON}" var ="Mon">
	<tr>
		<td>${Mon.soTinChi}</td>
		<td>${Mon.tenMonHoc}</td>
		<td>${Mon.maMon}</td>
		<td>
			<a href ="DS_Lop?MaMon=${Mon.maMon}&action=dangky" onclick="openInNewTabWithSize('DS_Lop?MaMon=${Mon.maMon}&action=dangky'); return false;" >Xem</a>
		</td>
	</tr>
	</c:forEach>
</table>
<h2>Danh sách môn đã đăng ký</h2>

<table border="1" cellpadding ="5" cellspacing ="1" >
	<tr>
		<th>Xóa</th>
		<th>Chuyển lớp</th>
		<th>Mã Lớp</th>
		<th>Tên môn</th>
		<th>Số tín chỉ</th>
		<th>Tiết</th>
		<th>Thứ</th>
		<th>Phòng</th>
		<th>Giảng viên</th>
		<th>Mã học viên</th>
		<th>Mã môn</th>
	</tr>
	<c:if test="${DSDaDK.list_LH.size() > 0}">
	 <c:forEach var="i" begin="0" end="${DSDaDK.list_LH.size()-1}">
        <tr>
			<td>
    			<a href="HV_XoaDK?MaLop=${DSDaDK.list_LH[i].getMaLop()}" onclick="return DeleteConfirmDialog('${DSDaDK.list_LH[i].getMaLop()}')">Xóa đăng ký</a>
			</td>
			
        	<td>
				
				<a href ="DS_Lop?MaMon=${DSDaDK.list_LH[i].getMaMon()}&action=chuyenlop&from=${DSDaDK.list_LH[i].getMaLop()}"onclick="openInNewTabWithSize('DS_Lop?MaMon=${DSDaDK.list_LH[i].getMaMon()}&action=chuyenlop&from=${DSDaDK.list_LH[i].getMaLop()}'); return false;">Xem</a>
					 
			</td>
            <td>${DSDaDK.list_LH[i].getMaLop()}</td>
            <td>${DSDaDK.list_MH[i].getTenMonHoc()}</td>
            <td>${DSDaDK.list_MH[i].getSoTinChi()}</td>
            <td>${DSDaDK.list_LH[i].getTiet()}</td>
            <td>${DSDaDK.list_LH[i].getThu()}</td>
            <td>${DSDaDK.list_LH[i].getMaPhong()}</td>
            <td>${DSDaDK.list_GV[i].getHoTen()}</td>
            <td>${HV.getMaHocVien()}</td>
            <td>${DSDaDK.list_LH[i].getMaMon()}</td>
        </tr>
    </c:forEach>
    </c:if>
	
</table>
	<c:if test="${not empty requestScope.err}">
            <label for="err">${requestScope.err}</label>
        </c:if>
        </div>
    <div class="menu">
    <div class="menu-item">
        <a href="HV_TKB" onclick="openInNewTabWithSize('TKB'); return false;">Xem thời khóa biểu</a>
    </div>
    <div class="menu-item">
        <a href="HV_HocPhi">Chi tiết học phí</a>
    </div>
    <div class="menu-item">
        <a href="LogOut">Đăng xuất</a>
    </div>
</div>
</body>
</html>