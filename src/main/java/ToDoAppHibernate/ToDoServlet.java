package ToDoAppHibernate;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ToDoServlet extends HttpServlet {

    private ToDoList toDoList = ToDoList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("list".equals(action)) {
            req.setAttribute("items", toDoList.getItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("listItems.jsp");
            dispatcher.forward(req, resp);
        } else if ("add".equals(action)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("addItem.jsp");
            dispatcher.forward(req, resp);
        } else if ("delete".equals(action)) {
            req.setAttribute("items", toDoList.getItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("deleteItem.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String description = req.getParameter("description");
            toDoList.addItem(description);
            resp.sendRedirect("ToDoServlet?action=list");
        } else if ("delete".equals(action)) {
            int index = Integer.parseInt(req.getParameter("index"));
            toDoList.deleteItem(index);
            resp.sendRedirect("ToDoServlet?action=list");
        }
    }
}