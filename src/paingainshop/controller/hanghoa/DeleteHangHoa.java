/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paingainshop.controller.hanghoa;

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
import javax.servlet.http.HttpSession;

import paingainshop.model.NhanVien;
import paingainshop.model.NhatKi;
import paingainshop.model.DAO.HangHoaDAO;
import paingainshop.model.DAO.NhatKiDAO;
import paingainshop.model.service.PainAndGainService;

/**
 *
 * @author dangt
 */
public class DeleteHangHoa extends HttpServlet {

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
            out.println("<title>Servlet DeleteHangHoa</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteHangHoa at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html; charset = UTF-8");
        String mahh = request.getParameter("mahh");
        HangHoaDAO hhdao = new HangHoaDAO();
        String msg;
        try {
            if(hhdao.deleteHangHoa(mahh)){
                msg ="success";
                // thêm nhật kí
                HttpSession session = request.getSession();
                NhanVien nv = (NhanVien)session.getAttribute("login");
                String MaNK = PainAndGainService.CreatePKey("NK", new NhatKiDAO().getLastPkey());
                Date date = new Date();
                SimpleDateFormat datefrmat = new SimpleDateFormat("yyyy-MM-dd");
                String Ngay = datefrmat.format(date);
                
                SimpleDateFormat timefrmat = new SimpleDateFormat("HH:mm:ss");
                String Gio = timefrmat.format(date);
                String ND = nv.getHoTen() +" xóa hàng hóa "+ mahh; 
                NhatKi nk = new NhatKi(MaNK,Ngay,Gio,ND);
                NhatKiDAO db5 = new NhatKiDAO();
                db5.insertNhatKi(nk);
            }
        } catch (Exception ex) {
            msg =" Lá»—i" + ex.getMessage();
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
