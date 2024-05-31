<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html> 
<head>
    <meta charset="UTF-8">
    <title>Dexuat</title>

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
        h2 {
            color: #000080		;
            text-shadow: 2px 2px 4px #999999;
            
            
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        form input[type="text"],
		form input[type="number"] {
    	width: 100%;
    	padding: 16px;
    	border: 1px solid #ccc;
   		border-radius: 4px;
    	box-sizing: border-box;
    	margin-bottom: 10px;
		}

		form input[type="submit"] {
    	background-color: #2E8B57;
    	color: white;
    	padding: 10px 20px;
    	border: none;
    	border-radius: 4px;
    	cursor: pointer;
    	font-size: 18px;
    	
		}
		form input[type="submit"]:hover {
    	background-color: #006241;
		}

        label[for="result"] {
            color: red;
        }
    </style>
    <script>
    function openInNewTabWithSize(url) {
	    window.open(url, '_blank', 'width=1000' + ', height=1000' );
	}
    </script>
</head>
<h1>ĐĂNG KÝ MÔN HỌC</h1>
<h2>Đề xuất</h2>
<body>
    <form id="DeXuatForm" action="${pageContext.request.contextPath}/GV_DeXuat"  method="post">
        <label for="MaMon">Mã môn muốn đề xuất mở lớp:</label>
        <input type="text" id="MaMon" name="MaMon" required><br>

        <label for="SoHocVien">Số học viên được đăng ký:</label>
        <input type="number" id="SoHocVien" name="SoHocVien" required min="1"><br>

        <input type="submit" value="Đề xuất">
        
        <c:if test="${not empty requestScope.result}">
            <label for="result">${requestScope.result}</label>
        </c:if>
    </form>
</body>
</html>
