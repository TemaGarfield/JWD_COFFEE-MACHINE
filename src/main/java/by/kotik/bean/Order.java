package by.kotik.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {
    private int id;
    private int idUser;
    private List<Coffee> coffeeList;
    private List<Ingredient> ingredientList;
    private BigDecimal cost;

    private Map<Integer, Integer> coffeeMap;
    private Map<Integer, Integer> ingredientMap;

    public Order() {
        coffeeList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        cost = new BigDecimal(0);

        coffeeMap = new HashMap<>();
        ingredientMap = new HashMap<>();
    }

    public void addCoffee(Coffee coffee) {
        coffeeList.add(coffee);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getIdUser() { return idUser; }

    public void setIdUser(int idUser) { this.idUser = idUser; }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<Coffee> getCoffeeList() { return coffeeList; }

    public void setCoffeeList(List<Coffee> coffeeList) { this.coffeeList = coffeeList; }

    public List<Ingredient> getIngredientList() { return ingredientList; }

    public void setIngredientList(List<Ingredient> ingredientList) { this.ingredientList = ingredientList; }

    public Map<Integer, Integer> getCoffeeMap() { return coffeeMap; }

    public void setCoffeeMap(Map<Integer, Integer> coffeeMap) { this.coffeeMap = coffeeMap; }

    public Map<Integer, Integer> getIngredientMap() { return ingredientMap; }

    public void setIngredientMap(Map<Integer, Integer> ingredientMap) { this.ingredientMap = ingredientMap; }

}
