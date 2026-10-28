/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import datos.CSubred;

/**
 *
 * @author Jobae
 */
@WebServlet(name = "Subred", urlPatterns = {"/Subred"})
public class Subred extends HttpServlet {

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
        String ip = request.getParameter("ip");
        if (!validarIP(ip)) {
            response.sendRedirect(request.getContextPath() + "/jsp/ip-invalida.jsp");
            return;
        }

        try {
            int prefijo = Integer.parseInt(request.getParameter("prefijo"));
            CSubred subred = new CSubred(ip, prefijo);

            request.setAttribute("ip", ip);
            request.setAttribute("prefijo", prefijo);
            request.setAttribute("subred", subred);
            request.getRequestDispatcher("/jsp/resultado.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
        }
    }

    /** Comprueba que el texto tenga el formato y los rangos de una IPv4 válida. */
    private boolean validarIP(String ip) {
        if (ip == null || ip.trim().isEmpty()) {
            return false;
        }
        try {
            new CSubred(ip.trim(), 0);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
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
        processRequest(request, response);
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
