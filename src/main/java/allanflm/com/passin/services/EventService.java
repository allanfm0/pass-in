package allanflm.com.passin.services;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;

import allanflm.com.passin.domain.attendee.Attendee;
import allanflm.com.passin.domain.event.Event;
import allanflm.com.passin.dto.event.EventIdDTO;
import allanflm.com.passin.dto.event.EventRequestDTO;
import allanflm.com.passin.dto.event.EventResponseDTO;
import allanflm.com.passin.repositories.AttendeeRepository;
import allanflm.com.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not fount with ID:" + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);

        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO) {
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.deatils());
        newEvent.setMaximumAttendees(eventDTO.maximunAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));
        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
