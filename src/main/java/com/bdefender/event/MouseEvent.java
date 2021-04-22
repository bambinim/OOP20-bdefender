package com.bdefender.event;

public class MouseEvent extends Event {

    /**
     * Mouse Clicked Event Type.
     */
    public static final EventType<MouseEvent> MOUSE_CLICKED = new EventType<>("Mouse Clicked");
    private final Object source;

    public MouseEvent(final EventType<MouseEvent> type, final Object source) {
        super(type);
        this.source = source;
    }

    /**
     * @return event source
     */
    public Object getSource() {
        return this.source;
    }

}
