package session09.miniProject.entity;

/*
 Vehicle là lớp cha của tất cả phương tiện
 Mỗi Vehicle sẽ chạy trên 1 thread
 */

public abstract class Vehicle implements Runnable {

    protected String id;      // mã xe
    protected int speed;      // tốc độ
    protected int priority;   // mức ưu tiên

    public Vehicle(String id, int speed, int priority) {
        this.id = id;
        this.speed = speed;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    /*
     hành động khi xe đi qua giao lộ
     các loại xe khác nhau có thể override
     */
    public abstract void crossIntersection();

}