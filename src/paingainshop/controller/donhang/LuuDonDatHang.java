/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paingainshop.controller.donhang;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paingainshop.model.CTDonDatHang;
import paingainshop.model.CTHoaDon;
import paingainshop.model.DAO.CTDonDatHangDAO;
import paingainshop.model.DAO.CTHoaDonDAO;
import paingainshop.model.DAO.DonDatHangDAO;
import paingainshop.model.DAO.HoaDonDAO;
import paingainshop.model.DAO.NhatKiDAO;
import paingainshop.model.service.PainAndGainService;
import paingainshop.model.DonDatHang;
import paingainshop.model.DonHang;
import paingainshop.model.HoaDon;
import paingainshop.model.HoaDonData;
import paingainshop.model.NhanVien;
import paingainshop.model.NhatKi;

/**
 *
 * @author BANH MY
 */
public class LuuDonDatHang extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LuuDonDatHang</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LuuDonDatHang at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        DonHang donhang = (DonHang) session.getAttribute("donhang");
        DonDatHang dh = new DonDatHang(donhang.getMaDDH(), donhang.getNgay(), donhang.getMaNV(), 0);
        ArrayList<CTDonDatHang> list = donhang.getItems();
        String msg = "";
        DonDatHangDAO dhdao = new DonDatHangDAO();
        CTDonDatHangDAO ctdao = new CTDonDatHangDAO();
        if (list.isEmpty()) {
            msg = "không có sản phẩm nào trong đơn đặt hàng";
        } else if(!list.isEmpty()) {
            try {
                dhdao.insertDonDatHang(dh);
             // thêm nhật kí
                
                NhanVien nv = (NhanVien)session.getAttribute("login");
                String MaNK = PainAndGainService.CreatePKey("NK", new NhatKiDAO().getLastPkey());
                Date date = new Date();
                SimpleDateFormat datefrmat = new SimpleDateFormat("yyyy-MM-dd");
                String Ngay = datefrmat.format(date);
                
                SimpleDateFormat timefrmat = new SimpleDateFormat("HH:mm:ss");
                String Gio = timefrmat.format(date);
                String ND = nv.getHoTen() +" thêm đơn đặt hàng "+ dh.getMaDDH(); 
                NhatKi nk = new NhatKi(MaNK,Ngay,Gio,ND);
                NhatKiDAO db5 = new NhatKiDAO();
                db5.insertNhatKi(nk);
                for (CTDonDatHang ct : list) {
                    
                    ctdao.insertCTDonDatHang(ct);
                    
                }
                session.setAttribute("donhang", null);
                msg = "success";
            } catch (Exception ex) {
                msg = "loi: " + ex.getMessage();
            }
        }
        response.getWriter().print(msg);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
