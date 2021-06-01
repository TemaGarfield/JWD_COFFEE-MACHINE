package by.kotik.bean;

import java.math.BigDecimal;

public class User {
    private String username;
    private String password;
    private BigDecimal balance;
    private boolean isAdmin;

    public User() {}

    public User(String username, String password, BigDecimal balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.isAdmin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
