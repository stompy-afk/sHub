package lol.stompy.shub.queue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueuePriority {

    private String permission;
    private int priority;

    public QueuePriority(String permission, int priority) {
        this.permission = permission;
        this.priority = priority;
    }

}
