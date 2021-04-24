package com.bdefender.event;

public class EventImpl implements Event {

    private final EventType<? extends Event> type;

    public EventImpl(final EventType<? extends Event> type) {
        this.type = type;
    }

    @Override
    public EventType<? extends Event> getType() {
        return this.type;
    }

}
