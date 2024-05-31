<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
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
            color: #000000;
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
        /* Checkbox */
		input[type="checkbox"] {
    	display: none;
    	
		}

		input[type="checkbox"] + label {
    	position: relative;
    	padding-left: 25px;
    	cursor: pointer;
    	font-size: 18px;
    	display: inline-block;
    	margin-right: 10px;
    	position: relative;
    	left: 50px;
        color: white;
           
        
    
    	
		}

		input[type="checkbox"] + label:before {
    	content: "";
    	position: absolute;
    	left: 0;
    	top: 0;
    	width: 18px;
    	height: 18px;
    	border: 2px solid #ccc;
    	border-radius: 50%;
		}

		input[type="checkbox"]:checked + label:before {
    	background-color: white;
   	 	border-color: #000000;
   	 	   	 	
		}
		.change-password-label {
 		text-decoration: underline;
  		cursor: pointer;
  		color: white;
  		font-size: 18px;
  		
  		
		}
		
		.error-message {
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
footer {
   position: fixed;
   bottom: 0;
   left: 0;
   width: 100%;
   color: white;
   padding: 20px;
   text-align: center;
}

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        
    </style>
    <script type="text/javascript">
    function handleCheckboxClick(checkboxIndex) {
        var checkboxes = document.getElementsByName('Authorization');

        for (var i = 0; i < checkboxes.length; i++) {
            if (i !== checkboxIndex) {
                checkboxes[i].checked = false;
            }
        }
    }    
    function submitSelectedCheckbox() {
        var checkboxes = document.getElementsByName('Authorization');
        var selectedValue = null;

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedValue = checkboxes[i].value;
                break;
            }
        }

        if (selectedValue) {
            // Set the value of the hidden input field
            document.getElementById('Who').value = selectedValue;
        } 
        else {
            alert("Hãy chọn 1 vai trò!");
            return false; // Prevent the form from submitting
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
</head>
<body>
	<h1>ĐĂNG KÝ MÔN HỌC</h1>
    <h2>Đăng nhập</h2>
	


	<form id="loginForm" action="${pageContext.request.contextPath}/login" method="post" onsubmit="return submitSelectedCheckbox();">
    <label for="username">Tài khoản:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Mật khẩu:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="hidden" id="Who" name="Who" value=""> <!-- Trường input ẩn -->

    <input type="submit" value="Đăng nhập">
	</form>
	
	<label class="change-password-label" onclick="window.location.href='ChangePass'">Đổi mật khẩu</label>
    
	<input type="checkbox" 
    					   name="Authorization" 
                           value="HV" 
                           id="checkbox1" 
                           onclick="handleCheckboxClick(0);">
    <label for="checkbox1">Học viên</label>
    <input type="checkbox" 
    					   name="Authorization" 
                           value="GV"
                           id="checkbox2" 
                           onclick="handleCheckboxClick(1);">
    <label for="checkbox2">Giảng viên</label>
    <input type="checkbox" 
    					   name="Authorization" 
                           value="AD"
                           id="checkbox3" 
                           onclick="handleCheckboxClick(2);">
    <label for="checkbox3">Admin</label>
    <div class="error-message">
    <c:if test="${not empty requestScope.err}">
        <label for="error-message">${requestScope.err}</label>
    </c:if>
</div>
	<footer>
   <div class="footer-content">
      <p>&copy;Copyright &copy; 2023 Nhóm 8 HCMUTE</p>
   </div>
</footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
                       
</body>
</html>