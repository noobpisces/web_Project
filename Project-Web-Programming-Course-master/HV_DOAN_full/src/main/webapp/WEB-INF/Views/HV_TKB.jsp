<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@ page import="Data_Structure.MyList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thời khóa biểu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
  			background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOlntPB3M-u_0R38QcDWOIkZflHHRmeeypGw&usqp=CAU");
            text-align: center;
            margin: 0;
            padding: 0;
            background-image: white;
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
            color: #000080;
            margin-top: 150px;
            font-size: 45px;
    		text-shadow: 2px 2px 4px #999999;
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

        

        
    </style>
</head>
<body>
<h1>ĐĂNG KÝ MÔN HỌC</h1>
<h2>Thời Khóa Biểu</h2>
    <table>
        <thead>
            <tr>
                <th>Tên môn</th>
                <th>Số tín chỉ</th>
                <th>Thứ</th>
                <th>Tiết</th>
                <th>Phòng</th>
                <th>Giảng viên</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${DSDaDK.list_LH.size() > 0}">
                <c:forEach var="i" begin="0" end="${DSDaDK.list_LH.size()-1}">
                    <tr>
                        <td>${DSDaDK.list_MH[i].getTenMonHoc()}</td>
                        <td>${DSDaDK.list_MH[i].getSoTinChi()}</td>
                        <td>${DSDaDK.list_LH[i].getThu()}</td>
                        <td>${DSDaDK.list_LH[i].getTiet()}</td>
                        <td>${DSDaDK.list_LH[i].getMaPhong()}</td>
                        <td>${DSDaDK.list_GV[i].getHoTen()}</td>
                    </tr>
                </c:forEach>
                
            </c:if>
        </tbody>
    </table>
    <a href="Xuat_TKB" >Xuất file</a>   
    
</body>
</html>