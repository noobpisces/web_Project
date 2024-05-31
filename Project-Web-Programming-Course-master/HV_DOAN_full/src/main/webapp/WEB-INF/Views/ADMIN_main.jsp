<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <style>
  body {
           font-family: Arial, sans-serif;
           background-color: #f4f4f4;
           text-align: center;
           margin: 0;
           padding: 0;
           background-image: url("https://cdn5.vectorstock.com/i/1000x1000/02/64/a-school-building-background-vector-24320264.jpg");
           background-repeat: no-repeat;
           background-size: cover;
           height: 100vh;
       }
  
    /* CSS cho nút */
    .button {
      display: inline-block;
      padding: 10px 20px;
      font-size: 16px;
      text-align: center;
      text-decoration: none;
      background: #4876FF;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      margin-right: 10px;
    }
        .button-container {
        display: flex;
        justify-content: center;
                   margin-top: 150px;
    }
    .button:hover {
        background-color: #436EEE
        
        ;
    }

    /* CSS cho nút cuối cùng */
    .button:last-child {
      margin-right: 0;
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
       
    
  </style>
</head>
<body>
  <!-- Form -->
<h1>ĐĂNG KÝ MÔN HỌC</h1>
<form action="${pageContext.request.contextPath}/ADMIN_main" method="post">
    <div class="button-container">
        <button class="button" name="action" value="1">
            <img src="https://cdn-icons-png.flaticon.com/128/2784/2784445.png" alt="Button 1">
                        <label for="button1">Giảng viên</label>
            
        </button>
        <button class="button" name="action" value="2">
            <img src="https://cdn-icons-png.flaticon.com/128/2995/2995620.png" alt="Button 2">
                        <label for="button2">Học viên</label>
            
        </button>
        <button class="button" name="action" value="3">
            <img src="https://cdn-icons-png.flaticon.com/128/207/207121.png" alt="Button 3">
                        <label for="button3">Lớp học</label>
            
        </button>
        <button class="button" name="action" value="4">
            <img src="https://cdn-icons-png.flaticon.com/128/1348/1348448.png" alt="Button 4">
                        <label for="button4">Đăng xuất</label>
            
        </button>
    </div>
</form>

  <!-- Phần mã JavaScript -->
  <script>
    function requestAction(action) {
      var xhr = new XMLHttpRequest();
      xhr.open('POST', '${pageContext.request.contextPath}/ADMIN_main', true);
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
      xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
          // Xử lý phản hồi từ máy chủ (nếu cần)
        }
      };
      xhr.send('action=' + action);
    }
  </script>
</body>
</html>
