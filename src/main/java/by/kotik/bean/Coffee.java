package by.kotik.bean;

import java.math.BigDecimal;
import java.util.Objects;

public class Coffee {
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
        return amount == coffee.amount && Objects.equals(type, coffee.type) && Objects.equals(cost, coffee.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, cost, amount);
    }
}
