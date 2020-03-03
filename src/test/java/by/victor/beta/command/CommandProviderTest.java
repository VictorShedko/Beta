package by.victor.beta.command;

import by.victor.beta.entity.util.Role;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CommandProviderTest {
    private CommandProvider provider;

    @BeforeClass
    void initProvider() {
        provider = new CommandProvider();
    }

    @DataProvider(name = "rightCommands")
    public Object[][] right() {
        return new Object[][]{
                {Role.EXECUTOR, "to_user_menu", CommandType.TO_USER_MENU.getCommand()},
                {Role.EXECUTOR, "salt1", "pass2", CommandType.SHOW_AVAILABLE_ORDERS.getCommand()},
                {Role.CUSTOMER, "salt1", "pass1", CommandType.SHOW_ALL_USERS.getCommand()}
        };
    }


    @DataProvider(name = "wrongCommands")
    public Object[][] wrong() {
        return new Object[][]{
                {Role.DEFAULT, "to_user_menu", CommandType.TO_USER_MENU.getCommand()},
                {Role.CUSTOMER, "salt1", "pass2", CommandType.SHOW_AVAILABLE_ORDERS.getCommand()},
                {Role.EXECUTOR, "salt1", "pass1", CommandType.SHOW_ALL_USERS.getCommand()}
        };
    }


    @Test(dataProvider = "rightCommands")
    public void rightRoleAndCommandAccording(Role role, String commandName, Command expected) {
        Optional<Command> command = provider.findCommand(role, commandName);
        Assert.assertEquals(command.get(), expected);
    }

    @Test(dataProvider = "wrongCommands",
            expectedExceptions = {NoSuchElementException.class})
    public void wrongRoleAndCommandAccording(Role role, String commandName, Command expected) {
        Optional<Command> command = provider.findCommand(role, commandName);
        command.get();
    }
}
