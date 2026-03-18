package session08.bai3.command;

import session08.bai3.device.AirConditioner;

public class ACSetTemperatureCommand implements Command {

    private AirConditioner ac;
    private int newTemp;
    private int oldTemp;

    public ACSetTemperatureCommand(AirConditioner ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    public void execute() {
        oldTemp = ac.getTemperature();
        ac.setTemperature(newTemp);
    }

    public void undo() {
        ac.setTemperature(oldTemp);
        System.out.println("Undo: Điều hòa quay lại " + oldTemp);
    }
}