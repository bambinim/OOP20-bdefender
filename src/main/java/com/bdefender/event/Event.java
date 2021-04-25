package com.bdefender.event;

public interface Event {

    /**
     * @return event type
     */
    EventType<? extends Event> getType();
}
