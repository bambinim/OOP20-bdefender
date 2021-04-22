package com.bdefender.event;

public abstract class Event {

    private final EventType type;

    public Event(final EventType type) {
        this.type = type;
    }

    /**
     * @return event type
     */
    public EventType getType() {
        return this.type;
    }
}
