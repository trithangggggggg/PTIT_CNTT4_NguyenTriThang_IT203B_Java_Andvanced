package session07.homework;

public class OrderCalculator {
    public double calculateTotal(Order order) {

        double total = 0;

        for (OrderItem item : order.getItems()) {
            total += item.getTotal();
        }

        return total;
    }
}
