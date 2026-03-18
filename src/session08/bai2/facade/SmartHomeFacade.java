package session08.bai2.facade;

import session08.bai2.device.AirConditioner;
import session08.bai2.device.Fan;
import session08.bai2.device.Light;
import session08.bai2.sensor.OldThermometer;
import session08.bai2.sensor.TemperatureSensor;
import session08.bai2.sensor.ThermometerAdapter;

public class SmartHomeFacade {

    private Light light;
    private Fan fan;
    private AirConditioner airConditioner;
    private TemperatureSensor sensor;

    public SmartHomeFacade() {

        light = new Light();
        fan = new Fan();
        airConditioner = new AirConditioner();

        OldThermometer oldThermometer = new OldThermometer();
        sensor = new ThermometerAdapter(oldThermometer);
    }

    public void leaveHome() {

        light.turnOff();
        fan.turnOff();
        airConditioner.turnOff();
    }

    public void sleepMode() {

        light.turnOff();
        airConditioner.setTemperature(28);
        fan.lowSpeed();
    }

    public void getCurrentTemperature() {

        double temp = sensor.getTemperatureCelsius();

        System.out.println("Nhiệt độ hiện tại: " + temp + "°C (chuyển đổi từ 78°F)");
    }
}