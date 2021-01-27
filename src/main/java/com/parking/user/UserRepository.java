package com.parking.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLicenseAndPassword(String license, String password);
}
