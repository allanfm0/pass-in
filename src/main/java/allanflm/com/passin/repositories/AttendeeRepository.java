package allanflm.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import allanflm.com.passin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {

}
