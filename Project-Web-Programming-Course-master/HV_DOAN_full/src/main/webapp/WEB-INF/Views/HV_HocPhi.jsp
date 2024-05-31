<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ page import="Data_Structure.MyList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HocPhi</title>

    <!-- Thêm CSS tại đây -->
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

        h3 {
            color: #333;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>ĐĂNG KÝ MÔN HỌC</h1>
<h2>Học phí</h2>
    <table>
        <tr>
            <th>Tên môn</th>
            <th>Số tín chỉ</th>
            <th>Giảng viên</th>
            <th>Mã môn</th>
            <th>Số tiền</th>
        </tr>
        <c:if test="${DS_HP.list_LH.size() > 0}">
            <c:forEach var="i" begin="0" end="${DS_HP.list_LH.size()-1}">
                <tr>
                    <td>${DS_HP.list_MH[i].getTenMonHoc()}</td>
                    <td>${DS_HP.list_MH[i].getSoTinChi()}</td>
                    <td>${DS_HP.list_GV[i].getHoTen()}</td>
                    <td>${DS_HP.list_LH[i].getMaMon()}</td>
                    <td>${DS_HP.list_HocPhi[i]}</td>
                </tr>
            </c:forEach>
             
        </c:if>
    </table>
    <h3>Tổng học phí: ${Tong_HocPhi} VND</h3>
    <a href="XuatPhieu_DK" >Xuất file</a>
    
</body>
</html>
