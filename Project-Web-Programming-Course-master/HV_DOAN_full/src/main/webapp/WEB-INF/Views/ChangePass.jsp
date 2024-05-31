<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change pass</title>
    
    <style>
        /* Định nghĩa các quy tắc CSS tại đây */
       body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
            background-image: url("https://images.fpt.shop/unsafe/filters:quality(90)/fptshop.com.vn/uploads/images/tin-tuc/150215/Originals/Education-Course-Study-Backgrounds.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            height: 100vh;
        }
        
        h1 {
    		position: sticky;
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
            color: #FFFFFF;
            margin-top: 150px;
            font-size: 45px;
            text-transform: uppercase;
    		text-shadow: 2px 2px 4px #999999;
        }
        
         form {
   		max-width: 500px;
    	margin: 20px auto;
    	background-color: #fff;
   		padding: 20px;
   		border-radius: 5px;
    	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}
        
        form label {
    	display: block;
    	margin-bottom: 10px;
    	font-weight: bold;
    	text-decoration: underline;
    	text-shadow: 2px 2px 4px #999999;
    	font-size:20px
    	
		}
        
        form input[type="text"],
		form input[type="password"] {
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
        
        form .error-message {
    	color: red;
    	margin-top: 5px;
		}
		c\:if {
            color: #F8F8FF; /* Change the color as needed */
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
		.logout-button {
    padding: 5px;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  .logout-button img {
    width: 20px;
    height: 20px;
  }
		

        
    </style>
    <script>
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
    function goBack() {
        // Sử dụng hàm history.back() để quay lại trang trước
        history.back();
      }
     
    </script>
</head>
<body>
	<h1>ĐĂNG KÝ MÔN HỌC</h1>
    <h2>Đổi mật khẩu</h2>
    <form id="ChangePassForm" action="${pageContext.request.contextPath}/ChangePass" method="post">
        <label for="username">Tài khoản:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="oldpassword">Mật khẩu cũ:</label>
        <input type="password" id="oldpassword" name="oldpassword" required><br>

        <label for="newpassword">Mật khẩu Mới:</label>
        <input type="password" id="newpassword" name="newpassword" required><br>
        
        <input type="submit" value="Hoàn thành">
    </form>

    <div class="error-message">
    <c:if test="${not empty requestScope.err}">
        <label for="error-message">${requestScope.err}</label>
    </c:if>
    
</div>
<button class="logout-button" onclick="goBack()">
  <img src="https://cdn-icons-png.flaticon.com/128/4400/4400629.png" alt="Trở về">
</button>


</body>
</html>