package session08.bai5.command;

import session08.bai5.device.AirConditioner;
import session08.bai5.device.Fan;
import session08.bai5.device.Light;

public class SleepModeCommand implements Command {

    private Light light;
    private Fan fan;
    private AirConditioner ac;

    public SleepModeCommand(Light light, Fan fan, AirConditioner ac) {

        this.light = light;
        this.fan = fan;
        this.ac = ac;
    }

    @Override
    public void execute() {

        System.out.println("SleepMode: Tắt đèn");
        System.out.println("SleepMode: Điều hòa set 28°C");
        System.out.println("SleepMode: Quạt tốc độ thấp");

        light.off();
        ac.setTemperature(28);
        fan.lowSpeed();
    }
}