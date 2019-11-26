package com.b5wang.microservice.easymall.easymalltransaction.model;

import java.util.Objects;

public class Transaction {

    private long id;
    private int fromUserId;
    private int toUserId;
    private long due;
    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public long getDue() {
        return due;
    }

    public void setDue(long due) {
        this.due = due;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                fromUserId == that.fromUserId &&
                toUserId == that.toUserId &&
                due == that.due;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUserId, toUserId, due);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", due=" + due +
                '}';
    }
}
