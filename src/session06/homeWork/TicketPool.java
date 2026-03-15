package session06.homeWork;
import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;
    private int ticketCounter = 1;

    public TicketPool(String roomName, int totalTickets) {
        this.roomName = roomName;
        tickets = new ArrayList<>();
        for (int i = 0; i < totalTickets; i++) {
            String id = roomName + "-" + String.format("%03d", ticketCounter++);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public String getRoomName() {
        return roomName;
    }

    public synchronized int getRemainingTickets() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                count++;
            }
        }
        return count;
    }

    public synchronized Ticket sellTicket() {
        while (true) {
            for (Ticket t : tickets) {
                if (!t.isSold()) {
                    t.setSold(true);
                    return t;
                }
            }
            if (TicketSupplier.finished) {
                return null;
            }

            System.out.println(Thread.currentThread().getName()
                    + ": Hết vé phòng " + roomName + ", đang chờ...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            String id = roomName + "-" + String.format("%03d", ticketCounter++);
            tickets.add(new Ticket(id, roomName));

        }
        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        notifyAll();
    }
}