package com.mycompany.lab_3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SerwletSesja extends HttpServlet {

    @Override
    public void init () throws ServletException {
        this.getServletContext().log("Inicjalizacja serwletu o czasie: " + new Date());
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SerwletSesja</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SerwletSesja at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ArrayList notes = (ArrayList)session.getAttribute("notes");
        Integer maxTime = 1800;
        if (notes == null)
        {
            notes = new ArrayList();
            session.setAttribute("maxTime", maxTime);
            session.setAttribute("notes",notes);
        }
        
        String notuj = request.getParameter("notuj");
        if (notes != null)
            notes.add(notuj);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Moje notatki</h1>");
        out.println("<ul>");
        for (int i = 0; i < notes.size(); i++)
            out.println("<li>" + notes.get(i));
        out.println("</ul>");
        out.println("<form action='SerwletSesja'>");
        out.println("<input type='text' name='notuj'/><br/>");
        out.println("<input type='submit' value='Dodaj'/>");
        out.println("</form>");
        
        out.println("<form action='SerwletSesja2'>");
        // out.println("<br><hr> Czas po którym wygasa sesja: "+ 
           //     session.getMaxInactiveInterval()/60 + " min");
           out.println("Czas po którym wygasa sesja: 60 min");
        out.println("<br>Podaj nowy czas wygaszania (w minutach): <input type='text' name='zmien_czas'/>");
        out.println("<br><input type='submit' value='Zmien czas wygaszania'/>");
        out.println("</form>");
        out.println("</body>");
        
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
