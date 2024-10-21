package com.strain.service;

import java.util.List;

import com.strain.beans.HistoryBean;
import com.strain.beans.TrainException;

public interface BookingService {

	public List<HistoryBean> getAllBookingsByCustomerId(String customerEmailId) throws TrainException;

	public HistoryBean createHistory(HistoryBean bookingDetails) throws TrainException;
	
	public List<HistoryBean> getAllBookings() throws TrainException;

}
