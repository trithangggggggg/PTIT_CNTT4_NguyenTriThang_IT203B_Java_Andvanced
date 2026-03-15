package session06.homeWork;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;
    private Random random = new Random();

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public int getSoldCount() {
        return soldCount;
    }


    public void sellCombo() {

        TicketPool first = roomA;
        TicketPool second = roomB;

        if (roomA.getRoomName().compareTo(roomB.getRoomName()) > 0) {
            first = roomB;
            second = roomA;
        }

        synchronized (first) {
            synchronized (second) {

                Ticket t1 = roomA.sellTicket();
                Ticket t2 = roomB.sellTicket();

                if (t1 != null && t2 != null) {
                    System.out.println(counterName +
                            " bán combo thành công: "
                            + t1.getTicketId() + " & " + t2.getTicketId());

                    soldCount += 2;
                } else {
                    System.out.println(counterName +
                            ": Hết vé phòng A hoặc B, bán combo thất bại");
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            boolean combo = random.nextBoolean();
            if (combo) {
                sellCombo();
            } else {
                boolean chooseA = random.nextBoolean();
                Ticket ticket;
                if (chooseA) {
                    ticket = roomA.sellTicket();
                } else {
                    ticket = roomB.sellTicket();
                }

                if (ticket == null) {
                    break;
                }
                System.out.println(counterName + " đã bán vé " + ticket.getTicketId());
                soldCount++;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counterName + " đã dừng bán vé");
    }
}