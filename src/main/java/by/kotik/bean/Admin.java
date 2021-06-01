package by.kotik.bean;

import java.math.BigDecimal;

public class Admin extends User{
    public Admin() {
        super();
        this.setAdmin(true);
    }

    public Admin(String username, String password, BigDecimal balance) {
        this.setUsername(username);
        this.setPassword(password);
        this.setBalance(balance);
        this.setAdmin(true);
    }
}
