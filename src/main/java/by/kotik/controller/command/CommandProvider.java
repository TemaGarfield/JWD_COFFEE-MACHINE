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
        commands.put(CommandName.GOTOEDITCOFFEE, new GoToEditCoffee());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }
}