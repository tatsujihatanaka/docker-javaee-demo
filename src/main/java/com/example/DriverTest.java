package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/driver-test")
public class DriverTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>JDBC Driver Load Test</h1>");
        
        try {
            // MySQL 8.0用のドライバクラス名を指定
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            
            out.println("<p style='color:green;'>SUCCESS: " + driverName + " is loaded!</p>");
            out.println("<p>Payara correctly recognizes the MySQL Connector JAR.</p>");
            
        } catch (ClassNotFoundException e) {
            out.println("<p style='color:red;'>FAILED: Driver not found.</p>");
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
            out.println("<p>Check if the JAR is in /opt/payara/appserver/glassfish/domains/domain1/lib/</p>");
        } finally {
            out.println("<br><a href='index.jsp'>Back to Home</a>");
            out.println("</body></html>");
        }
    }
}