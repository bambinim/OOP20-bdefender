package com.bdefender.event;

public interface Event {

    /**
     * @return event type
     */
    public EventType<? extends Event> getType();
}
