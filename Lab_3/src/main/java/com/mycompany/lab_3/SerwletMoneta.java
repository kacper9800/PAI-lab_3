package com.mycompany.lab_3;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

public class SerwletMoneta extends HttpServlet 
{  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int liczbaOrlow = 0;
        int liczbaReszek = 0;
        Cookie[] ciasteczka = request.getCookies();
        if (ciasteczka != null)
        for (int i = 0; i < ciasteczka.length; i++)
        {
            if (ciasteczka[i].getName().equals("orzel"))
                liczbaOrlow = Integer.parseInt(ciasteczka[i].getValue());
            if (ciasteczka[i].getName().equals("reszka"))
                liczbaReszek = Integer.parseInt(ciasteczka[i].getValue());
        }
        String rzut = (Math.random() <= 0.5) ? "orzel" : "reszka";
        if(rzut.equals("orzel"))
            liczbaOrlow++;
        else
            liczbaReszek++;
        Cookie orzel = new Cookie("orzel", String.valueOf(liczbaOrlow));
        orzel.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(orzel);
        Cookie reszka = new Cookie("reszka", String.valueOf(liczbaReszek));
        reszka.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(reszka);
        out.println("<html>");
        out.println("<body>");
        out.println("Wynik rzutu: " + rzut + "<br/>");
        out.println("Liczba orlow: " + liczbaOrlow + "<br/>");
        out.println("Liczba reszek: " + liczbaReszek + "<br/>");
        out.println("<a href=\"SerwletMoneta\">ponowny rzut</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}