/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paingainshop.controller.nhanvien;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paingainshop.model.DAO.PhieuChiDAO;
import paingainshop.model.NhanVien;
import paingainshop.model.PhieuChi;
import paingainshop.model.service.PainAndGainService;

/**
 *
 * @author BANH MY nhanvien/traluong
 */
public class LapPhieuChiTraLuong extends HttpServlet {

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
            out.println("<title>Servlet LapPhieuChiTraLuong</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LapPhieuChiTraLuong at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        String tennv = request.getParameter("TenNV");
        int luong = Integer.parseInt(request.getParameter("Luong"));
        int thuong = Integer.parseInt(request.getParameter("Thuong"));
        String MaPC = null;
        String msg = "";
        try {
            MaPC = PainAndGainService.CreatePKey("PC", new PhieuChiDAO().getLastPkey());
        } catch (Exception ex) {
            msg = "loi server: " + ex.getMessage();
        }
        String datestr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        NhanVien nv = (NhanVien)request.getSession().getAttribute("login");
        PhieuChi pc = new PhieuChi(MaPC, datestr, "Thanh toán lương", tennv, luong + thuong, "",nv.getMaNV());
        PhieuChiDAO dbaccess = new PhieuChiDAO();
        try {
            if(dbaccess.insertPhieuChi(pc)){
                msg="Thanh Toán thành công! Đã lập phiếu chi";
            }else{
                msg=" Không thể lập phiếu chi";
            }
            
        } catch (Exception ex) {
            msg = "lỗi csdl: "+ ex.getMessage();
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
