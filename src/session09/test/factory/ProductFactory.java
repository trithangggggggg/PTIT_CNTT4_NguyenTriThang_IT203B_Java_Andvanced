package session09.test.factory;

import session09.test.entity.*;

public class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extra) {
        if (type == 1) {
            return new PhysicalProduct(id, name, price, extra);
        }

        if (type == 2) {
            return new DigitalProduct(id, name, price, extra);
        }
        return null;
    }
}