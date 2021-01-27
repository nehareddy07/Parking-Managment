package com.parking.payment;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.parking.booking.Booking;
import com.parking.booking.BookingRepository;
import com.parking.entry.Entry;
import com.parking.entry.EntryRepository;
import com.parking.user.User;
import com.parking.user.UserRepository;
import com.stripe.exception.StripeException;

import groovy.util.logging.Log;

@Log
@Controller
public class ChargeController {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	StripeService paymentsService;

	@Autowired
	EntryRepository entryRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
		Entry entry = entryRepository.findOne(chargeRequest.getEntryId());
		Booking booking = new Booking();
		booking.setAmount(entry.getPrice());
		booking.setCheckIn(entry.getFromDate());
		booking.setCheckOut(entry.getToDate());
		booking.setEntryId(entry.getId());
		booking.setUserId(chargeRequest.getUserId());
		booking = bookingRepository.save(booking);
		int count = entry.getCount() - 1;
		if (count < 0) {
			entryRepository.delete(entry);
		} else {
			entry.setCount(count);
			entryRepository.save(entry);
		}
		sendEmail(booking);
		bookingRepository.flush();
		List<Booking> bookings = bookingRepository.findByUserId(chargeRequest.getUserId());
		model.addAttribute("bookings", bookings);
		return "mybookings";
	}

	@PostMapping("/charge/{entryId}/{userId}")
	public String add(ChargeRequest chargeRequest, @PathVariable Long entryId, @PathVariable Long userId, Model model)
			throws StripeException {
		chargeRequest.setEntryId(entryId);
		chargeRequest.setUserId(userId);
		return charge(chargeRequest, model);
	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}

	private int sendEmail(Booking booking) {

		User user = userRepository.getOne(booking.getUserId());

		try {
			MimeMessage ms = javaMailSender.createMimeMessage();
			MimeMessageHelper msg = new MimeMessageHelper(ms, true);
			msg.setTo(user.getEmail());
			msg.setFrom("parkingmanagmentsys@gmail.com");

			msg.setSubject("Booking Conformation!!");
			msg.setText("Thanks for the Booking! \n Your booking has been confirmed with id: " + booking.getId(), true);

			javaMailSender.send(ms);
		} catch (MessagingException e) {
		}

		return 0;
	}
}
