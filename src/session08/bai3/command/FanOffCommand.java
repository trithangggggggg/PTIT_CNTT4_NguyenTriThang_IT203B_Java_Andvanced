package session08.bai3.command;

import session08.bai3.device.Fan;

public class FanOffCommand implements Command {

    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.off();
    }

    public void undo() {
        fan.on();
    }
}