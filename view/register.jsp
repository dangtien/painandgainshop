<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="paingainshop.model.BangChamCong" %>
<%@ page import="paingainshop.model.NhanVien" %>
<%@ page import="paingainshop.model.DAO.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import=" java.util.Date" %>
<%@ page import=" java.text.*" %>
<title>Quản Lý Chấm Công </title>
<%@include file="frame/header.jsp"%>
<%@include file="frame/sidebar.jsp"%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
            <a href="${pageContext.request.contextPath}/index">Home</a>
            <small>Chấm công</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i>Chấm công</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/bangchamcong">Chấm công</a></li>
             
        </ol>
    </section>
	
    <!-- Main content -->
    <section class="content">
    <span style="color:red"><i id="msg"></i></span>
    <form action="addregister" method="post">
      <div class="row">
        <div class="col-md-3">
          <!-- /. box -->
          <div class="box box-solid">
            <div class="box-header with-border">
              <h3 class="box-title">Thêm chấm công</h3>
              
            </div>
            <span style="color:red"><i>${msg}</i></span>
            <div class="box-body">
              <!-- /btn-group -->
              <div class="input-group">
              	<input class="form-control" id="nowdate" name="Ngay" type="text" style="margin-bottom: 7px" placeholder="Ngày hiện tại" disabled value=<%= (new SimpleDateFormat("yyyy-MM-dd").format(new Date()) )%>>
                <div class="form-group" >
		                <select class="form-control select2" onchange="ShowEmployee(this);" id="MaNV"  style="margin-bottom: 7px; "  data-placeholder="Chọn nhân viên" style="width: 100%;" name="MaNV" required>
		                 <option value="" disabled selected>Chọn nhân viên</option>
		                  <% ArrayList<NhanVien> list2 = new ArrayList<NhanVien>(); 
		                  list2 = new NhanVienDAO().getAll();
		                  %>
		                  
	                <%
	                	if(list2!=null){
	                		for(NhanVien nv : list2){
	                %>
		                 <option value ="<%=nv.getMaNV() %>"><%=nv.getHoTen() %></option>
		             <%
	                		}
	                	}
	                %>
		                </select>
		              </div>
		              
				<select class="form-control input input2" id="MaCa" style="margin-bottom: 7px" name="MaCa" required>
				  <option value="" disabled selected>Chọn ca làm việc</option>
				  <option value="1">Ca 1</option>
				  <option value="2">Ca 2</option>
				  <option value="3">Ca 3</option>
				</select>
				
					
					<div class="form-group">
                  <div class="input-group" style="width : 100%">
                    <input type="time" class="form-control " id="timepicker1" name="GioBD" placeholder="Giờ bắt đầu" required>
                    <div class="input-group-addon">
                      <span>Giờ bắt đầu</span>
                    </div>
                  
                  <!-- /.input group -->
                </div>
                </div>
                
                <!-- /.form group -->
                <div class="form-group" >
                  <div class="input-group" style="width : 100%">
                    <input type="time"  class="form-control "id="timepicker2" name="GioKT" placeholder="Giờ kết thúc" required>
                    <div class="input-group-addon">
                      <span>Giờ kết thúc</span>
                   
                  </div>
                  <!-- /.input group -->
                </div>
              </div>
				  <div class="form-group">
				    <label class="sr-only" for="exampleInputAmount">Tiền Phạt</label>
				    <div class="input-group">
				      <div class="input-group-addon">--</div>
				      <input type="text" class="form-control" id="exampleInputAmount1" placeholder="Tiền Phạt" name="TienPhat" value="0">
				      <div class="input-group-addon">VND</div>
				    </div>
				  </div>
			
				  <div class="form-group">
				    <label class="sr-only" for="exampleInputAmount">Tiền Phụ cấp</label>
				    <div class="input-group">
				      <div class="input-group-addon">+</div>
				      <input type="text" class="form-control" id="exampleInputAmount2" placeholder="Tiền Phụ cấp" name="PhuCap" value="0">
				      <div class="input-group-addon">VND</div>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="sr-only" for="exampleInputAmount">Tạm ứng</label>
				    <div class="input-group">
				      <div class="input-group-addon">--</div>
				      <input type="text" class="form-control" id="exampleInputAmount3" placeholder="Tạm ứng" name="TamUng" value="0">
				      <div class="input-group-addon">VND</div>
				    </div>
				  </div>
                
                <!-- /btn-group -->
              </div>
             <div class="input-group-btn">
                  <button id="add-new-event" type="submit"  style="float: right; margin-right: 20px; " class="btn btn-primary btn-flat">Thêm</button>
                </div>
               
              <!-- /input-group -->
            </div>
          </div>
        </div>
        <!-- /.col -->
        
        <div class="col-md-9">
          <div class="box box-primary">
            <div class="box-body no-padding">
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
      </form>
    </section>
    <!-- /.content -->
  </div>


<%@include file="frame/footer.jsp"%>


<!-- fullCalendar 2.2.5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/templates/plugins/fullcalendar/fullcalendar.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/templates/plugins/select2/select2.full.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>


<!-- Page specific script -->
<script>
	function ShowEmployee(obj){
		MaNV=$(obj).val();
		$.get(MaNV)
		
	}
  $(function () {
	  
    var calendar = $('#calendar').fullCalendar({
        selectable:false,
        selectHelper:false,
        editable:false,
        droppable: false,
        header:{
           left: 'prev,next today',
           center: 'title',
           right: 'month,agendaWeek,agendaDay'
        },

      events: [
        {
            title  : 'Nguyen Duy Manh',
            start  : '2017-06-06T08:00:00',
            end    : '2017-06-06T12:00:00',
            backgroundColor: "#00a65a", //Success (green)
            borderColor: "#00a65a"  
        },
        {
          title: 'Vu Thi Khanh Chi',
          start  : '2017-06-06T12:00:00',
          end    : '2017-06-06T16:00:00',
          backgroundColor: "#f56954", //red
          borderColor: "#f56954" //red
        },
        {
          title: 'Tran Van Huy',
          start  : '2017-06-10T08:00:00',
          end    : '2017-06-10T12:00:00',
          backgroundColor: "#f39c12", //yellow
          borderColor: "#f39c12" //yellow
        },
        {
          title: 'Dang Tien Chieu',
          start  : '2017-06-12T16:00:00',
          end    : '2017-06-12T21:00:00',
          backgroundColor: "#0073b7", //Blue
          borderColor: "#0073b7" //Blue
        },
        {
          title: 'Nguyen Duy Manh',
          start  : '2017-06-16T16:00:00',
          end    : '2017-06-16T21:00:00',
          backgroundColor: "#00c0ef", //Info (aqua)
          borderColor: "#00c0ef" //Info (aqua)
        },
        {
          title: 'Tran Van Huy',
          start  : '2017-06-16T08:00:00',
          end    : '2017-06-16T12:00:00',
          backgroundColor: "#00a65a", //Success (green)
          borderColor: "#00a65a" //Success (green)
        },
        {
          title: 'Dang Tien Chieu',
          start  : '2017-06-23T12:00:00',
          end    : '2017-06-23T16:00:00',
          backgroundColor: "#3c8dbc", //Primary (light-blue)
          borderColor: "#3c8dbc" //Primary (light-blue)
        }
    ]
 
    });
      //Remove event from text input
      $(".select2").select2();
       
  });
</script>
