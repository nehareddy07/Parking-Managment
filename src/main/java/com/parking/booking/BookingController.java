package com.parking.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entry.EntryRepository;

@RestController
@RequestMapping("booking")
public class BookingController {

	@Autowired
	private BookingRepository repository;

	@Autowired
	private EntryRepository entryRepository;

	@GetMapping
	public ResponseEntity<List<Booking>> get() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> get(@PathVariable Long id) {
		return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<List<Booking>> getByUse(@PathVariable Long id) {
		return new ResponseEntity<>(repository.findByUserId(id), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Map<String, Long>> count() {
		Map<String, Long> count = new HashMap<String, Long>();
		List<Booking> d = repository.findAll();
		count.put("bookings", Long.valueOf(d.size()));
		long count1 = 0;
		for (Booking ele : d) {
			count1 = ele.getAmount() + count1;
		}
		count.put("amount", count1);
		count.put("slots", Long.valueOf(entryRepository.findAll().size()));
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Booking> add(@RequestBody Booking entry) {
		return new ResponseEntity<>(repository.save(entry), HttpStatus.OK);
	}

}
