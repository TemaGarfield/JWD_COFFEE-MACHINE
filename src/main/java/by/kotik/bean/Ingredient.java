package by.kotik.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Ingredient implements Serializable {
    private int idIngredient;
    private BigDecimal cost;
    private String type;
    private int amount;

    public Ingredient() {}

    public Ingredient(BigDecimal cost, String type, int amount) {
        this.cost = cost;
        this.type = type;
        this.amount = amount;
    }

    public Ingredient(int idIngredient, BigDecimal cost, String type, int amount) {
        this.idIngredient = idIngredient;
        this.cost = cost;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return idIngredient;
    }

    public void setId(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Ingredient that = (Ingredient) o;
        return idIngredient == that.idIngredient && amount == that.amount && Objects.equals(cost, that.cost) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngredient, cost, type, amount);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
