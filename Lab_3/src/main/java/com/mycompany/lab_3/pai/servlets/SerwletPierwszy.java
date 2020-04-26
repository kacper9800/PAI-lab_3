package com.mycompany.lab_3.pai.servlets;
import java.io.*;
import java.util.Random;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name="SerwletPierwszy", urlPatterns={"/SerwletPierwszy"})
public class SerwletPierwszy extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
	ServletContext sc = this.getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher("/SerwletDrugi");
        Random los = new Random();
        int calkLos = los.nextInt(999999);
        req.setAttribute("liczba", calkLos);
        dispatcher.include(req, resp);
    }
}