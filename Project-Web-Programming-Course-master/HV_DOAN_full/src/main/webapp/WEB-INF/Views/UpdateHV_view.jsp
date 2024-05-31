<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa thông tin Học viên</title>
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
       
    
    p {
        color: red;
    }

    table {
        border-collapse: collapse;
    }

    td {
        padding: 5px;
    }

    input[type="text"] {
        width: 200px;
    }

    input[type="submit"]{
        display: inline-block;
        padding: 5px 10px;
        background-color: #337ab7;
        color: #fff;
        text-decoration: none;
        border: none;
        cursor: pointer;
    }

    a {
        margin-left: 10px;
    }
    
    .center {
        display: flex;
        justify-content: center;
        align-items: center;
        height: auto;
    }
    form {
  /* Tùy chỉnh các thuộc tính bố cục */
  margin: 0 auto;
  padding: 30px;
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: #6495ED	; /* Màu sắc nền */
  background-repeat: no-repeat; /* Ngăn lặp lại hình ảnh */
  background-size: cover; /* Hiển thị hình ảnh vừa với kích thước của form */
  font-family: Arial, sans-serif; /* Font chữ */
  font-size: 14px; /* Kích thước font */
  font-weight: bold; /* Độ đậm */
  color: #333; /* Màu chữ */
  text-align: left;
    max-width: 300px; /* Giới hạn độ rộng tối đa của form */
      text-align: left;
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

label {
  /* Tùy chỉnh kiểu dáng cho nhãn */
  display: block;
  margin-bottom: 5px;
}

input[type="text"] {
  /* Tùy chỉnh kiểu dáng cho các ô input */
  width: 100%;
  padding: 6px;
  margin-bottom: 10px;
}


td.button-container {
    text-align: center; /* Căn giữa nội dung theo chiều ngang */
  }
  h2{
       color: #000080;
       text-shadow: 2px 2px 4px #999999;
       }

    </style>
</head>
<body>
    <%-- <jsp:include page="_header.jsp"></jsp:include> --%>
    <%-- <jsp:include page="_menu.jsp"></jsp:include> --%>
    <h2>Chỉnh sửa thông tin Học viên</h2>
        <img src="https://cdn-icons-png.flaticon.com/128/1754/1754534.png" alt="ảnh học viên" />
    
        
    
    <p style="color: red;"><c:out value="${errorString}" /></p>
    <c:if test="${not empty hocvien}">
        <form method="POST" action="${pageContext.request.contextPath}/updateHocVien">
            <input type="hidden" name="maHocVien" value="<c:out value='${hocvien.maHocVien}' />" />
            <table border="0">
                <tr>
                    <td>Mã Hoc viên</td>
                    <td><input  type="text" name="maHocVien" value="<c:out value='${hocvien.maHocVien}' />"readonly /></td>
                </tr>
                <tr>
                    <td>Họ và tên</td>
                    <td><input type="text" name="hoTen" value="<c:out value='${hocvien.hoTen}' />" /></td>
                </tr>
                <tr>
                    <td>Ngày sinh</td>
                    <td><input type="date" name="ngaySinh" value="<c:out value='${hocvien.ngaySinh}' />" /></td>
                </tr>
                <tr>
                    <td>Địa chỉ</td>
                    <td><input type="text" name="diaChi" value="<c:out value='${hocvien.diaChi}' />" /></td>
                </tr>
                <tr>
                    <td>Quê quán</td>
                    <td><input type="text" name="queQuan" value="<c:out value='${hocvien.queQuan}' />" /></td>
                </tr>
                <tr>
                    <td>Mã khoa</td>
                    <td><input type="text" name="maKhoa" value="<c:out value='${hocvien.maKhoa}' />" /></td>
                </tr>
                <tr>
  <td colspan="2" class="button-container" >
    <input type="image" src="https://cdn-icons-png.flaticon.com/128/489/489707.png" alt="Lưu" style="width: 40px; height: 40px; margin-right: 30px;" />
    <a href="${pageContext.request.contextPath}/ADMIN_HV">
      <img src="https://cdn-icons-png.flaticon.com/128/753/753345.png" alt="Hủy" style="width: 40px; height: 40px; margin-left: 50px;" />
    </a>
  </td>
</tr>
            </table>
        </form>
    </c:if>
    <%-- <jsp:include page="_footer.jsp"></jsp:include> --%>
</body>
</html>
