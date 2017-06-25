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
import java.util.ListIterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paingainshop.model.DAO.*;
import paingainshop.model.service.PainAndGainService;
import paingainshop.model.*;


/**
 *
 * @author BANH MY
 */
public class UpdateTTDonDatHang extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateTTDonDatHang</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTTDonDatHang at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("utf-8");
        String MaDDH = request.getParameter("MaDDH");

        int tongtien = 0;

        DonDatHangDAO db = new DonDatHangDAO();
        HangHoaDAO db1 = new HangHoaDAO();
        CTDonDatHangDAO db2 = new CTDonDatHangDAO();
        PhieuChiDAO db3 = new PhieuChiDAO();
        NhanVienDAO db4 = new NhanVienDAO();
        String message1 = "";
        try {
            DonDatHang ddh = db.getById(MaDDH);
            if (ddh.getTrangThai() == 1) {
                message1 = "Đơn hàng này đã được cập nhật";
            } else {
                ArrayList<CTDonDatHang> lst = (ArrayList<CTDonDatHang>) db2.getByID(MaDDH);
                for (CTDonDatHang ct : lst) {
                    
                    String ma = ct.getMaHH();
                    int sl = db1.getById(ma).getSoLuong() + ct.getSoLuong();
                    db1.updateSLHangHoa(sl, ma);
                    // tinh tong tien de dua sang phieu chi
                    int sl1=ct.getSoLuong();
                    int dongia = ct.getDonGia();
                    tongtien += sl1 * dongia;
                }
                db.updateTTDonDatHang(1, MaDDH);
                // bắn ra phiếu chi
                try {
                    String MaPC = PainAndGainService.CreatePKey("PC", new PhieuChiDAO().getLastPkey());
                    Date date = new Date();
                    SimpleDateFormat datefrmat = new SimpleDateFormat("yyyy-MM-dd");
                    String datestr = datefrmat.format(date);
                    PhieuChi pc = new PhieuChi(MaPC, datestr, "Tiền nhập hàng", "", tongtien, "", ddh.getMaNV());
                    db3.insertPhieuChi(pc);
                    
                 // thêm nhật kí
                    HttpSession session = request.getSession();
                    NhanVien nv = (NhanVien)session.getAttribute("login");
                    String MaNK = PainAndGainService.CreatePKey("NK", new NhatKiDAO().getLastPkey());
                    String Ngay = datefrmat.format(date);
                    
                    SimpleDateFormat timefrmat = new SimpleDateFormat("HH:mm:ss");
                    String Gio = timefrmat.format(date);
                    String ND = nv.getHoTen() +" cập nhật đơn hàng "+ ddh.getMaDDH(); 
                    NhatKi nk = new NhatKi(MaNK,Ngay,Gio,ND);
                    NhatKiDAO db5 = new NhatKiDAO();
                    db5.insertNhatKi(nk);

                } catch (Exception e1) {
                    message1="khong tao dc phieu chi"+ e1.getMessage();
                }
                message1 = "Cập nhật đơn hàng thành công.";
                
                //db1.updateSLHangHoa(db2.getByMaDDH(MaDDH).getSoLuong(), MaDDH);
            }
        } catch (Exception e) {
            message1 = "Cập nhật đơn hàng không thành công."+ e.getMessage();
        }
        response.getWriter().print(message1);
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
