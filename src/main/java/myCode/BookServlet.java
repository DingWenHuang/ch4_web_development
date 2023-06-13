package myCode;

import obj.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends HttpServlet {
    private PreparedStatement preparedStatement;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initializeJDBC();
        try {
            preparedStatement.setString(1, request.getParameter("bookId"));
            ResultSet rs = preparedStatement.executeQuery();//因為要去做的是查詢，所以是要做executeQuery()
            //然後經過executeQuery()出來的結果一種ResultSet，我們賦值給rs變數
            Book book = null;//這裡的Book是前面做session的時候用來存資料的物件
            if (rs.next()) {//如果ResultSet有找到某筆資料的話，就去做下面的事情
                book = new Book();
                book.setBookId(rs.getInt("bookId"));//去找資料庫中欄位標籤叫做bookId的值，設定成BookId
                book.setName(rs.getString("name"));//下面意思都一樣
                book.setPrice(rs.getInt("price"));
                book.setAuthor(rs.getString("author"));
            }
            //我們可以把上面servlet拿到的資料送給一個jsp的檔案
            request.setAttribute("book", book);
            request.getRequestDispatcher("/book.jsp").forward(request, response);
            //getRequestDispatcher的意思是我們要去render一個jsp的頁面，把上一行設定的book丟進去
            //把上面得到的request跟response都forward到/book.jsp頁面
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver  loading...");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videodb", "wen", "12345");
            System.out.println("Database connected...");

            preparedStatement = conn.prepareStatement("select * from book where bookId= ?");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
