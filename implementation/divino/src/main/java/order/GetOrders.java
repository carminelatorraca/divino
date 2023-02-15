package order;

import account.AccountEntity;
import account.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GetOrders", value = "/order-manager")
public class GetOrders extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderDAO = (OrderDAO) super.getServletContext().getAttribute("orderDAO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //visualizzazione degli ordini da parte di gestore e magazzino
        if (request.getParameter("mode").equalsIgnoreCase("showOrders")) {
            UserEntity user = (UserEntity) request.getSession().getAttribute("user");

            try {
                //trovo tutti gli ordini
                ArrayList<OrderEntity> orders = orderDAO.retrieveAllOrders();
                request.getSession().setAttribute("orders", orders);

                if (user.getRole() == AccountEntity.Role.MANAGERUSER)
                    response.sendRedirect("./admin/order-view.jsp");
                else if (user.getRole() == AccountEntity.Role.WAREHOUSEUSER) {
                    response.sendRedirect("./warehouse/order-view.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //aggiornamento stato ordine da parte di gestore e magazzino
        if (request.getParameter("mode").equalsIgnoreCase("updateStatus")) {
            UserEntity user = (UserEntity) request.getSession().getAttribute("user");

            Integer orderID = Integer.valueOf(request.getParameter("orderID"));
            String orderStatus = request.getParameter("p_status");

            try {
                OrderEntity orderToUpdate = orderDAO.retrieveOrder(orderID);
                orderToUpdate.setOrderStatus(orderStatus);
                orderDAO.updateOrder(orderToUpdate);

                request.getSession().setAttribute("orders", orderDAO.retrieveAllOrders());
                if (user.getRole() == AccountEntity.Role.MANAGERUSER)
                    response.sendRedirect("./admin/order-view.jsp");
                else if (user.getRole() == AccountEntity.Role.WAREHOUSEUSER) {
                    response.sendRedirect("./warehouse/order-view.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
