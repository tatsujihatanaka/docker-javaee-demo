package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jdbc-test")
public class JdbcTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Docker Compose の environment セクションで定義した変数名から取得
        String host = System.getenv("MYSQL_HOST");
        String db   = System.getenv("MYSQL_DATABASE");
        String user = System.getenv("MYSQL_USER");
        String pass = System.getenv("MYSQL_PASSWORD");

        // 接続URLを組み立て
        // ホスト名、DB名、ユーザー名、パスワードのハードコーディングを排除
        String url = String.format("jdbc:mysql://%s:3306/%s?useSSL=false&allowPublicKeyRetrieval=true", host, db);

        out.println("<html><body>");
        out.println("<h2>Jakarta EE JDBC Connection Test (Environment Variables)</h2>");

        try {
            // MySQLドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            out.println("<p>Connecting to: <strong>" + url + "</strong></p>");
            out.println("<p>User: <strong>" + user + "</strong></p>");

            // 環境変数から得た情報で接続
            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                out.println("<p style='color:green;'><strong>Successfully connected to MySQL using Environment Variables!</strong></p>");

                // SQLを実行して動作確認
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT NOW() AS now_time")) {
                    
                    if (rs.next()) {
                        out.println("<p>Database Time: " + rs.getString("now_time") + "</p>");
                    }
                }
            }
        } catch (Exception e) {
            out.println("<p style='color:red;'><strong>Connection Failed!</strong></p>");
            out.println("<p>Check if environment variables (MYSQL_HOST, etc.) are set correctly.</p>");
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }

        out.println("<br><a href='index.jsp'>Back to Index</a>");
        out.println("</body></html>");
    }
}