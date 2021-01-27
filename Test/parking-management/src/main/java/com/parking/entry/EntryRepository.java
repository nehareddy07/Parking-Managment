package com.parking.entry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

	public List<Entry> findByCountGreaterThan(Integer count);

}
