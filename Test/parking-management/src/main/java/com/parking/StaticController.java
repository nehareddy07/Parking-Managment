package com.parking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.parking.booking.Booking;
import com.parking.booking.BookingRepository;
import com.parking.entry.Entry;
import com.parking.entry.EntryRepository;
import com.parking.payment.ChargeRequest;

@Controller
public class StaticController {
	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@RequestMapping("/")
	public String indexHTML() {
		return "index";
	}

	@RequestMapping("/login")
	public String loginHTML() {
		return "login";
	}

	@RequestMapping("/signup")
	public String signupHTML() {
		return "signup";
	}

	@RequestMapping("/help")
	public String helpHTML() {
		return "help";
	}

	@RequestMapping("/about")
	public String aboutHTML() {
		return "about";
	}

	@RequestMapping("/add")
	public String addtHTML() {
		return "addEntry";
	}

	@RequestMapping("/userhome")
	public ModelAndView usertHTML() {
		ModelAndView model = new ModelAndView();
		List<Entry> entries = entryRepository.findAll();
		model.addObject("entries", entries);
		model.setViewName("userHome");
		return model;
	}

	@RequestMapping("/mybookings/{id}")
	public String userMyBookingstHTML(@PathVariable Long id, Model model) {
		List<Booking> bookings = bookingRepository.findByUserId(id);
		model.addAttribute("bookings", bookings);
		return "mybookings";
	}

	@RequestMapping("/book/{id}")
	public String bookHTML(@PathVariable Long id, Model model) {
		Entry entry = entryRepository.findOne(id);
		model.addAttribute("entry", entry);
		model.addAttribute("amount", entry.getPrice());
		model.addAttribute("stripePublicKey", stripePublicKey);
		model.addAttribute("currency", ChargeRequest.Currency.USD);
		return "checkout";
	}

	@Value("${STRIPE_PUBLIC_KEY}")
	private String stripePublicKey;

	@RequestMapping("/entries")
	public ModelAndView entriesHTML() {
		ModelAndView model = new ModelAndView();
		List<Entry> entries = entryRepository.findAll();
		model.addObject("entries", entries);
		model.setViewName("parkingList");
		return model;
	}

	@RequestMapping("/bookings")
	public ModelAndView bookingsHTML() {
		ModelAndView model = new ModelAndView();
		List<Booking> bookings = bookingRepository.findAll();
		model.addObject("bookings", bookings);
		model.setViewName("bookings");
		return model;
	}
}
