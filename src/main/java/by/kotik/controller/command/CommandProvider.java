package by.kotik.controller.command;

import by.kotik.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.LOGINATION, new GoToLoginationPage());
        commands.put(CommandName.REGISTRATION, new GoToRegistrationPage());
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
        commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
        commands.put(CommandName.LOGIN, new Logination());
        commands.put(CommandName.GOTOWELCOMEPAGE, new GoToWelcomePage());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GOTOADMINPAGE, new GoToAdminPage());
        commands.put(CommandName.SAVENEWCOFFEE, new SaveNewCoffee());
        commands.put(CommandName.DELETECOFFEE, new DeleteCoffee());
        commands.put(CommandName.EDITCOFFEE, new EditCoffee());
        commands.put(CommandName.GOTOEDITCOFFEEPAGE, new GoToEditCoffee());
        commands.put(CommandName.SAVENEWINGREDIENT, new SaveNewIngredient());
        commands.put(CommandName.DELETEINGREDIENT, new DeleteIngredient());
        commands.put(CommandName.EDITINGREDIENT, new EditIngredient());
        commands.put(CommandName.GOTOEDITINGREDIENT, new GoToEditIngredient());
        commands.put(CommandName.GOTOTOPUPBALANCE, new GoToTopUpBalance());
        commands.put(CommandName.TOPUPBALANCE, new TopUpBalance());
        commands.put(CommandName.ADDTOCART, new AddToCart());
        commands.put(CommandName.GOTOCART, new GoToCart());
        commands.put(CommandName.CARTDELETECOFFEE, new CartDeleteCoffee());
        commands.put(CommandName.CARTDELETEINGREDIENT, new CartDeleteIngredient());
        commands.put(CommandName.SAVENEWORDER, new SaveNewOrder());
        commands.put(CommandName.GOTOSHOWALLORDERS, new GoToShowAllOrders());
        commands.put(CommandName.SWITCHLANGUAGE, new SwitchLanguage());
        commands.put(CommandName.GOTOEDITUSERS, new GoToEditUsers());
        commands.put(CommandName.DELETEUSER, new DeleteUser());
        commands.put(CommandName.GOTOEDITUSER, new GoToEditUser());
        commands.put(CommandName.EDITUSER, new EditUser());
        commands.put(CommandName.SAVEUSERFROMADMIN, new SaveUserFromAdmin());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}
