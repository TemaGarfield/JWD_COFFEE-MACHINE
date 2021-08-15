package by.kotik.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Coffee implements Serializable {
    private int id;
    private String type;
    private BigDecimal cost;
    private int amount;

    public Coffee() {

    }

    public Coffee(int id, String type, BigDecimal cost, int amount) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.amount = amount;
    }

    public Coffee(String type, BigDecimal cost, int amount) {
        this.type = type;
        this.cost = cost;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return id == coffee.id && amount == coffee.amount && Objects.equals(type, coffee.type) && Objects.equals(cost, coffee.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, cost, amount);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", amount=" + amount +
                '}';
    }
}
