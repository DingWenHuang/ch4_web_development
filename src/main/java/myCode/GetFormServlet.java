package myCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetFormServlet", value = "/GetFormServlet")
public class GetFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Get parameters from get request
        String bookName = request.getParameter("name");//後面的""裡面的內容是對應到myForm.jsp裡面
        String bookPrice = request.getParameter("price");//name=""的這個部分，要對應好才抓的到資料
        String bookAuthor = request.getParameter("author");


        PrintWriter out = response.getWriter();
        out.println("<h3> Name :" + bookName + "</h3>");
        out.println("<h3> Price:" + bookPrice + "</h3>");
        out.println("<h3> Author:" + bookAuthor + "</h3>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
