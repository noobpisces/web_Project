<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa thông tin Lớp học</title>
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
    <h2>Chỉnh sửa thông tin Lớp học</h2>
            <img src="https://cdn-icons-png.flaticon.com/128/4928/4928801.png" alt="ảnh lớp" />
    
    <p style="color: red;"><c:out value="${errorString}" /></p>
    <c:if test="${not empty lophoc}">
        <form method="POST" action="${pageContext.request.contextPath}/updateLopHoc">
            <input type="hidden" name="maLop" value="<c:out value='${lophoc.maLop}' />" />
            <table border="0">
                <tr>
                    <td>Mã Lớp</td>
                    <td><input  type="text" name="maLopHoc" value="<c:out value='${lophoc.maLop}' />" readonly/></td>
                </tr>
                <tr>
                    <td>Tên Lớp</td>
                    <td><input type="text" name="tenLop" value="<c:out value='${lophoc.tenLop}' />" /></td>
                </tr>
                <tr>
                    <td>Mã Giảng viên</td>
                    <td><input type="text" name="maGiangVien" value="<c:out value='${lophoc.maGiangVien}' />" /></td>
                </tr>
                <tr>
                    <td>Mã Môn</td>
                    <td><input type="text" name="maMon" value="<c:out value='${lophoc.maMon}' />" /></td>
                </tr>
                <tr>
                    <td>Mã Học kỳ</td>
                    <td><input type="text" name="maHocKy" value="<c:out value='${lophoc.maHocKy}' />" /></td>
                </tr>
                <tr>
                    <td>Số Học viên Đăng ký</td>
                    <td><input type="number" name="soHocVienDangKy" value="<c:out value='${lophoc.soHocVienDangKy}' />" /></td>
                </tr>
                <tr>
                    <td>Mã Phòng</td>
                    <td><input type="text" name="maPhong" value="<c:out value='${lophoc.maPhong}' />" /></td>
                </tr>
                <tr>
                    <td>Thứ</td>
                    <td><input type="text" name="thu" value="<c:out value='${lophoc.thu}' />" /></td>
                </tr>
                <tr>
                    <td>Tiết</td>
                    <td><input type="text" name="tiet" value="<c:out value='${lophoc.tiet}' />" /></td>
                </tr>
                <tr>
  <td colspan="2" class="button-container" >
    <input type="image" src="https://cdn-icons-png.flaticon.com/128/489/489707.png" alt="Lưu" style="width: 40px; height: 40px; margin-right: 30px;" />
    <a href="${pageContext.request.contextPath}/ADMIN_LH">
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
