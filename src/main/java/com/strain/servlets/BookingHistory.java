package com.strain.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.strain.beans.HistoryBean;
import com.strain.beans.TrainException;
import com.strain.constant.UserRole;
import com.strain.service.BookingService;
import com.strain.service.impl.BookingServiceImpl;
import com.strain.utility.TrainUtil;

/**
 * Servlet implementation class BookingHistory
 */
@WebServlet("/BookingHistory")
public class BookingHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    BookingService bookingService = new BookingServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		try {

			List<HistoryBean> details = bookingService.getAllBookings();
			if (details != null && !details.isEmpty()) {
				RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Booked Ticket History</p1></div>");
				pw.println("<div class='main' style='text-align: center;'>"
				          + "<p class='menu' style='font-size: 24px;'>Booked Ticket History</p>"
				          + "</div>");

				pw.println("<div class='table	'><table><tr><th>Transaction ID</th><th>UserName</th><th>Train Number</th>"
						+ "<th>From Station</th><th>To Station</th><th>Journey Date</th><th>Seat</th><th>Amount Paid</th></tr>");

				for (HistoryBean trans : details) {

					pw.println("" + "<tr> " + "" + "<td>" + trans.getTransId() + "</td>" + "<td>" + trans.getMailId() + "</td>" + "<td>" + trans.getTr_no()
							+ "</td>" + "<td>" + trans.getFrom_stn() + "</td>" + "<td>" + trans.getTo_stn() + "</td>"
							+ "<td>" + trans.getDate() + "</td>" + "<td>" + trans.getSeats() + "</td><td>"
							+ trans.getAmount() + "</td>" + "</tr>");
				}
				pw.println("</table></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu red'> No any ticket booked, book your first ticket now!!</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
