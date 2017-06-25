package paingainshop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paingainshop.model.*;
import paingainshop.model.DAO.*;
import paingainshop.model.service.PainAndGainService;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/addemployee")
public class AddEmployee extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset= UTF-8");
        request.setCharacterEncoding("utf-8");
        String message = "";
        try {

            String MaNV = PainAndGainService.CreatePKey("NV", new NhanVienDAO().getLastPkey());
            String TenNV = request.getParameter("Tennv");
            int Luong = Integer.parseInt(request.getParameter("Luong"));
            String Username = request.getParameter("Username");
            String Pass = request.getParameter("Password");
            String Email = request.getParameter("Email");
            String DiaChi = request.getParameter("Diachi");
            String SDT = request.getParameter("Sodt");
            String gc = request.getParameter("Ghichu");
            String tt = "clv";
            NhanVien nv = new NhanVien(MaNV, TenNV, Luong, Email, DiaChi, SDT, Username, Pass, gc, tt);
            NhanVienDAO db = new NhanVienDAO();

            

            try {
                if (TenNV != "" && Username != "" && Pass != "" && DiaChi != "" && SDT != "") {
                    db.insertNhanVien(nv);
                    
                    message = "success";

                } else {
                    message = "Thêm nhân viên không thành công.";

                }
            } catch (Exception e) {
                message = "Thêm nhân viên không thành công. " + e.getMessage();

            }
        } catch (Exception e) {

             message = "Thêm nhân viên không thành công." + e.getMessage();

        }
        response.getWriter().print(message);
    }

}
