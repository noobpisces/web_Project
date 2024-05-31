<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Account</title>
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

        .form-container {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-container label {
          
            display: block;
    	margin-bottom: 10px;
    	font-size:17px
            
        }

        .form-container input[type="text"],
        .form-container input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            margin: 20px auto 0;
        }

        .form-container a.cancel-link {
            display: block;
            text-align: center;
            margin-top: 10px;
            text-decoration: none;
            color: #333;
        }
        

        .error {
            color: red;
            margin-top: 10px;
        }
        .form-group {
        display: flex;
        flex-direction: column;
        margin-bottom: 10px;
    }
    .error-label {
    display: block;
    background-color: #f8d7da;
    color: #721c24;
    padding: 10px;
    border: 1px solid #f5c6cb;
    margin-bottom: 10px;
}
.cancel-button {
        background: none;
        border: none;
        padding: 0;
        cursor: pointer;
        margin-top:30px
    }
    

    .cancel-button img {
        width: 20px;
        height: 20px;
    }
    </style>
</head>
<body>
        <h2>Thêm tài khoản</h2>
                <img src="https://cdn-icons-png.flaticon.com/128/4117/4117111.png" alt="ảnh" />
        

    <div class="form-container">
        <%-- Hiển thị thông báo lỗi nếu có --%>
<% String errorString = (String) request.getAttribute("errorString");
if (errorString != null && !errorString.isEmpty()) { %>
    <label class="error-label"><%= errorString %></label>
<% } %>
        <%-- Form thêm tài khoản --%>
        <form action="Add_TKGV_servlet" method="POST">
    <div class="form-group">
        <label for="maGiangVien">Mã giảng viên:</label>
        <input type="text" id="maGiangVien" name="maGiangVien" value="${maGiangVien}" required>
    </div>

    <div class="form-group">
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <input type="submit" value="Thêm tài khoản">
<button class="cancel-button" onclick="window.location.href='${pageContext.request.contextPath}/ADMIN_GV'">
    <img src="https://cdn-icons-png.flaticon.com/128/4400/4400629.png" alt="Hủy">
</button></form>
    </div>
</body>
</html>
