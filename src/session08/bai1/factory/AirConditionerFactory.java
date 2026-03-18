package session08.bai1.factory;

import session08.bai1.device.AirConditioner;
import session08.bai1.device.Device;

public class AirConditionerFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("AirConditionerFactory: Đã tạo điều hòa mới.");
        return new AirConditioner();
    }
}
