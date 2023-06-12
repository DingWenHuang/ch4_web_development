package myCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "PostFormServlet", value = "/PostFormServlet")
public class PostFormServlet extends HttpServlet {

    private PreparedStatement preparedStatement;

    @Override
    public void init() throws ServletException {
        initializeJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        System.out.println(bookId + " " +  name + " " + price + " " + author);

        storeBook(Integer.parseInt(bookId), name, Integer.parseInt(price), author);
        PrintWriter out = response.getWriter();

        out.println("Book " + name + " has been stored in the database.");
        out.close();
    }

    private void initializeJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videoDB", "wen", "12345");
            System.out.println("Database connected...");

            preparedStatement = conn.prepareStatement("INSERT INTO book (bookId, name, price, author) VALUES (?, ?, ?, ?)");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void storeBook(int id, String name, int price, String author) {
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4, author);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
