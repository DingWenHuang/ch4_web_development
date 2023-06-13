package myCode;

import obj.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "SessionServlet", value = "/SessionServlet")
public class SessionServlet extends HttpServlet {
    private PreparedStatement preparedStatement;

    @Override
    public void init() throws ServletException {
        initializeJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        String bookId = request.getParameter("bookId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String author = request.getParameter("author");

        Book book = new Book();
        book.setBookId(Integer.parseInt(bookId));
        book.setName(name);
        book.setPrice(Integer.parseInt(price));
        book.setAuthor(author);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("book", book);

        PrintWriter out = response.getWriter();

        out.println("You entered the following data :");
        out.println("<p>Book ID : " + bookId + "</p>");
        out.println("<br />");
        out.println("<p>Book NAME : " + name + "</p>");
        out.println("<br />");
        out.println("<p>Book PRICE : " + price + "</p>");
        out.println("<br />");
        out.println("<p>Book AUTHOR : " + author + "</p>");
        out.println("<br />");

        out.println("<form method=\"post\" action=\"/sessionRegis\">");
        out.println("<input type=\"submit\" value=\"Confirm\">");
        out.println("</form>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Book book = (Book) httpSession.getAttribute("book");

        try {
            storeBook(book.getBookId(), book.getName(), book.getPrice(), book.getAuthor());
            PrintWriter out = response.getWriter();
            out.println("Book" + book.getName() + "has been stored in the database.");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
