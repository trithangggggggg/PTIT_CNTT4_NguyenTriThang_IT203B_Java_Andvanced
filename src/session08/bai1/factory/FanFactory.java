package session08.bai1.factory;

import session08.bai1.device.Device;
import session08.bai1.device.Fan;

public class FanFactory extends DeviceFactory {

    @Override
    public Device createDevice() {
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fan();
    }
}