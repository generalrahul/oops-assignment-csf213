package com.bits.csf213.roombooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bits.csf213.roombooking.controller", "com.bits.csf213.roombooking.service", "com.bits.csf213.roombooking.repository", "com.bits.csf213.roombooking.model"})
public class RoomBookingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(RoomBookingSystemApplication.class, args);
	}
}
