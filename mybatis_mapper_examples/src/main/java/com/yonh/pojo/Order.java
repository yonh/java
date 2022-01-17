package com.yonh.pojo;

public class Order {
    private int id;
    private String orderTime;
    private double total;
    private User user;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertime='" + orderTime + '\'' +
                ", total=" + total +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrdertime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
