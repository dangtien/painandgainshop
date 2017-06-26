package paingainshop.controller;

import java.io.IOException;
import java.sql.Time;
import java.text.*;
import javax.servlet.RequestDispatcher;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paingainshop.model.*;
import paingainshop.model.DAO.BangChamCongDAO;

public class AddNewRegister extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset= UTF-8");
        request.setCharacterEncoding("utf-8");
        Date Ngay = new Date();
        SimpleDateFormat datefrmat = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = datefrmat.format(Ngay);
        String GioBD = request.getParameter("GioBD");
        String GioKT = request.getParameter("GioKT");
        int TienPhat = Integer.parseInt(request.getParameter("TienPhat"));
        int Phucap = Integer.parseInt(request.getParameter("PhuCap"));
        int Tamung = Integer.parseInt(request.getParameter("TamUng"));
        int MaCa = Integer.parseInt(request.getParameter("MaCa"));
        String MaNV = request.getParameter("MaNV");
        response.getWriter().println("GioDB: " + GioBD);
        response.getWriter().print("GioKT: " + GioKT);
        String message = "";
        try {
            int MaCC = (new BangChamCongDAO().getLastPkey()) + 1;
            BangChamCong cc = new BangChamCong(MaCC, datestr, GioBD, GioKT, TienPhat, Phucap, Tamung, MaNV, MaCa);
            BangChamCongDAO db = new BangChamCongDAO();
            
            try {

                if (db.insertBCC(cc)) {
                    message = "  Chấm công thành công.";
                    

                } else {
                    message = "  Chấm công không thành công.";
                    
                }

            } catch (Exception e) {
                message = "  Chấm công không thành công."+ e.getMessage();
                
            }
        } catch (Exception ex) {
            message = "  Chấm công không thành công. "+ex.getMessage();
        }

        RequestDispatcher xxx = request.getRequestDispatcher("register.jsp");
        request.setAttribute("msg", message);
        xxx.forward(request, response);
        System.out.println("loi 3");

    }

}
