package com.bdefender.event;

public class EventType<T extends Event> {

    private final String name;

    public EventType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
