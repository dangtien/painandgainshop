/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paingainshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paingainshop.model.DAO.NhanVienDAO;
import paingainshop.model.NhanVien;

/**
 *
 * @author dangt
 */
public class Login extends HttpServlet {

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
        HttpSession session = request.getSession();
        NhanVien nv = (NhanVien) session.getAttribute("login");
        if (nv != null) {
            response.sendRedirect("index");
        } else{
            response.sendRedirect("/login.jsp");
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
        HttpSession session = request.getSession();
        NhanVien nv = (NhanVien) session.getAttribute("login");
        if (nv != null) {
            response.sendRedirect("index");
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            NhanVienDAO nvacess = new NhanVienDAO();

            try {
                nv = nvacess.getUserByUserName(username);
                if (nv == null) {
                    response.sendRedirect("/login.jsp");
                } else {
                    if (nv.getPass().equals(password) && nv.getTrangThai().equals("clv")) {

                        session.setAttribute("login", nv);
                        response.sendRedirect("index");
                    } else {
                        response.sendRedirect("/login.jsp");
                    }
                }
            } catch (Exception ex) {
                //request.getRequestDispatcher("login.jsp").forward(request, response);
                response.getWriter().print(ex.getMessage());
            }
        }

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
