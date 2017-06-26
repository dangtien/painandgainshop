<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="paingainshop.model.*" %>
<%@ page import="paingainshop.model.DAO.*" %>
<%@ page import="java.util.ArrayList" %>
<title>Quản Lý Tính Lương</title>
<%@include file="frame/header.jsp"%>
<%@include file="frame/sidebar.jsp"%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            <a href="${pageContext.request.contextPath}/index">Home</a>
            <small>Nhân viên</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i>Nhân viên</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/tinhluong.jsp">Bảng lương</a></li>

        </ol>
    </section>
    <section class="content">
        <span style="color:red"><i id="msg"></i></span>

        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Danh sách nhân viên</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="col-md-12 "><h3 style="text-align: center; color:blue;"><%                        Date date = new Date();
                        out.print("Bảng lương trong tháng: " + new SimpleDateFormat("MM / yyyy").format(date));
                            %></h3></div>   
                    <div class="box-body">
                        <table id="example1" class="table table-striped table-bordered  table-hover">
                            <thead>
                                <tr>
                                    <th>Mã NV</th>
                                    <th>Tên nhân viên</th>
                                    <th>Số giờ làm</th>
                                    <th>Lương/giờ</th>
                                    <th>Phạt</th>
                                    <th>Phụ cấp</th>
                                    <th>Tổng lương</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% ArrayList<BangTinhLuong> list = new ArrayList<BangTinhLuong>();
                                    try {
                                        list = new BangTinhLuongDAO().getInfoLuong();
                                    } catch (Exception e) {
                                    }
                                    if (list != null) {
                                        for (BangTinhLuong btl : list) {


                                %>
                                <tr>
                                    <td><%=btl.getManv()%></td>
                                    <td><%=btl.getTen()%></td>
                                    <td><%=btl.getGio()%></td>
                                    <td><%long luong = btl.getLuong();
                                        String sluong = NumberFormat.getIntegerInstance().format(luong);
                                        out.print(sluong + " VND");
                                        %></td>
                                    <td><% long phat = btl.getPhat();
                                        String sphat = NumberFormat.getIntegerInstance().format(phat);
                                        out.print(sphat + " VND");
                                        %></td>
                                    <td><% long phucap = btl.getPhucap();
                                        String sphucap = NumberFormat.getIntegerInstance().format(phucap);
                                        out.print(sphucap + " VND");
                                        %></td>
                                    <td style="color:blue;"><% long tongl = btl.getGio() * btl.getLuong() - btl.getPhat() + btl.getPhucap();
                                        String s = NumberFormat.getIntegerInstance().format(tongl);
                                        out.print(s + " VND");
                                        %> </td>
                                    <td><button class="btn btn-default" data-toggle="modal" data-target="#modal-inputbonus" onclick="thanhtoan('<%=tongl%>','<%=btl.getTen()%>');">Thanh toán</button></td>
                                </tr>
                                <%
                                        }
                                    }
                                %>



                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Mã NV</th>
                                    <th>Tên nhân viên</th>
                                    <th>Số giờ làm</th>
                                    <th>Lương/giờ</th>
                                    <th>Phạt</th>
                                    <th>Phụ cấp</th>
                                    <th>Tổng Lương</th>
                                    <th>Action</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </div>
        <div class="modal  fade" id="modal-inputbonus">
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header with-border">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Nhập thưởng</h4>
                        <span style="color:red"><i id="msg"></i></span>
                    </div>

                    <div class="modal-body">
                        <input type="text" value="0" class="form-control" id="txttennv" style="display: none;" disabled="disabled">
                        <input type="text" value="0" class="form-control" id="txtluong" style="display: none;" disabled="disabled">
                        <input type="text" value="0" class="form-control" id="txtthuong">
                    </div> 
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary" onclick="createPhieuChiTraLuong();">Thanh Toán</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </section>
</div>
<%@include file="frame/footer.jsp"%>