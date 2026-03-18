package session08.bai3.remote;

import session08.bai3.command.Command;

import java.util.Stack;

public class RemoteControl {

    private Command[] buttons = new Command[10];
    private Stack<Command> history = new Stack<>();

    public void setCommand(int slot, Command command) {
        buttons[slot] = command;
    }

    public void pressButton(int slot) {

        if (buttons[slot] != null) {

            buttons[slot].execute();
            history.push(buttons[slot]);

        } else {

            System.out.println("Chưa gán lệnh cho nút này");
        }
    }

    public void undo() {

        if (!history.isEmpty()) {

            Command command = history.pop();
            command.undo();

        } else {

            System.out.println("Không có lệnh để undo");
        }
    }
}
