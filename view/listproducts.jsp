<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="paingainshop.model.HangHoa"%>
<%@page import="java.util.ArrayList"%>
<title>Danh Sách Hàng Hóa</title>
<%@include file="frame/header.jsp"%>
<%@include file="frame/sidebar.jsp"%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
       <h1>
            <a href="${pageContext.request.contextPath}/index">Home</a>
            <small>Quản lí hàng hóa</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i>Quản lí hàng hóa</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/danhsach">Danh sách</a></li>
          
        </ol>
    </section>
    <section class="content">
        <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
                <table id="example1" class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Mã hàng</th>
                            <th>Tên hàng</th>
                            <th>số lượng</th>
                            <th>Giá bán</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% ArrayList<HangHoa> list = (ArrayList<HangHoa>) request.getAttribute("result");%>
                        <% if (list != null) {
                                for (HangHoa h : list) {
                        %>
                        <tr>
                            <td><a href="#" title="click vào để xem chi tiết sản phẩm"><%= h.getMaHH()%></a></td>
                            <td><%= h.getTenHH()%></td>
                            <td><%= h.getSoLuong()%></td>
                            <td><%= h.getGiaBan()%></td>
                            <td>
                                <a href="hanghoa/edit?mahh=<%=h.getMaHH()%>"><span class="fa fa-edit">Sửa</span></a>
                                <span class="fa" style="margin: 0px 5px;"></span>
                                <a href="#" onclick="confirmDelete('<%=h.getMaHH() %>')"><span class="fa fa-remove">Xóa</span></a>
                            </td>
                        </tr>
                        <%}
                      }%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Mã hàng</th>
                            <th>Tên hàng</th>
                            <th>số lượng</th>
                            <th>Giá bán</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
    </section>
</div>
<%@include file="frame/footer.jsp"%>