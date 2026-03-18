package session08.bai1.factory;

import session08.bai1.device.Device;
import session08.bai1.device.Light;

public class LightFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("LightFactory: Đã tạo đèn mới.");
        return new Light();
    }
}