package com.bdefender.event;

public class EventType<T extends Event> {

    private final String name;

    public EventType(final String name) {
        this.name = name;
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
