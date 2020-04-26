package com.mycompany.lab_3.pai.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="zliczaFilter", urlPatterns={"/*"})
public class zliczaFilter implements Filter
{
    private FilterConfig filterConfig = null;
    private HashMap links = null;
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
    FilterChain chain) throws IOException, ServletException
    {
        String link = ((HttpServletRequest)req).getRequestURL().toString();
        int licznik = 0;
        if (links.containsKey(link))
            licznik = Integer.parseInt(links.get(link).toString());
        links.put(link, ++licznik);
        Iterator i = links.keySet().iterator();
        while (i.hasNext())
        {
            link = i.next().toString();
            licznik = Integer.parseInt(links.get(link).toString());
            String str = link + " wywoływany " + licznik + " razy";
            this.getFilterConfig().getServletContext().log(str);
        }
        chain.doFilter(req, resp);
    }
    public FilterConfig getFilterConfig()
    {
        return (this.filterConfig);
    }
    public void setFilterConfig(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }
    @Override
    public void destroy()
    {
    }
    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
        this.links = new HashMap();
    }
}