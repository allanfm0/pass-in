package allanflm.com.passin.services;

import org.springframework.stereotype.Service;

import allanflm.com.passin.domain.event.Event;
import allanflm.com.passin.dto.event.EventResponseDTO;
import allanflm.com.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not fount with ID:" + eventId));
        return new EventResponseDTO(event, 0);
    }
}
