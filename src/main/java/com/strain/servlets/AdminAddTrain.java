package com.strain.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.strain.beans.TrainBean;
import com.strain.beans.TrainException;
import com.strain.constant.ResponseCode;
import com.strain.constant.UserRole;
import com.strain.service.TrainService;
import com.strain.service.impl.TrainServiceImpl;
import com.strain.utility.TrainUtil;

@WebServlet("/adminaddtrain")
public class AdminAddTrain extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TrainService trainService = new TrainServiceImpl();

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		try {
			TrainBean train = new TrainBean();
			train.setTr_no(Long.parseLong(req.getParameter("trainno")));
			train.setTr_name(req.getParameter("trainname").toUpperCase());
			train.setFrom_stn(req.getParameter("fromstation").toUpperCase());
			train.setTo_stn(req.getParameter("tostation").toUpperCase());
			train.setSeats(Integer.parseInt(req.getParameter("available")));
			train.setFare(Double.parseDouble(req.getParameter("fare")));
			//pw.println(train.getTr_no()+" "+train.getTr_name()+" "+train.getFrom_stn()+" "+train.getTo_stn()+" "+train.getSeats()+" "+train.getFare());
			String message = trainService.addTrain(train);
			if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(message)) {
				RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Train Added Successfully!</p1></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Error in filling the train Detail</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}

	}

}
