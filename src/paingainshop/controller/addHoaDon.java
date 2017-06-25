/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paingainshop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paingainshop.model.DAO.HoaDonDAO;
import paingainshop.model.HoaDonData;
import paingainshop.model.NhanVien;
import paingainshop.model.service.PainAndGainService;

/**
 *
 * @author dangt
 */
public class addHoaDon extends HttpServlet {

   

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
        try{
            String MaHD = PainAndGainService.CreatePKey("HD",new HoaDonDAO().getLastPkey());
            Date date = new Date();
            SimpleDateFormat datefrmat = new SimpleDateFormat("yyyy-MM-dd");
            String datestr = datefrmat.format(date);
            HttpSession session = request.getSession();
            NhanVien nv = (NhanVien)session.getAttribute("login");
             HoaDonData hoadon = new HoaDonData(MaHD, datestr, "", nv.getMaNV());
            session.setAttribute("hoadon", hoadon);
            request.setAttribute("MaHD", MaHD);
            request.setAttribute("Ngay", datestr);
            request.setAttribute("Nhanvien", nv.getHoTen());
            request.getRequestDispatcher("createbill.jsp").forward(request, response);
       } catch (Exception ex) {
                response.getWriter().print("loi: "+ ex.getMessage());
       }
        
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
        doGet(request, response);
        
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
