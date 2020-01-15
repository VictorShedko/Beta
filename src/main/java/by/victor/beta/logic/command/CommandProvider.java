package by.victor.beta.logic.command;


import by.victor.beta.logic.entity.Role;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CommandProvider {
    private EnumMap<Role, List<CommandType>> roleCommandMapping;

    public CommandProvider() {
        roleCommandMapping = new EnumMap<Role, List<CommandType>>(Role.class);
        List<CommandType> defaultUserCommand = List.of(CommandType.TO_REGISTRATION, CommandType.LOGIN, CommandType.REGISTER);
        roleCommandMapping.put(Role.DEFAULT, defaultUserCommand);
    }

    public Optional<AbstractCommand> findCommand(Role userRole, String commandName) {
        return roleCommandMapping.get(userRole).stream().filter(t -> t.name().equalsIgnoreCase(commandName)).findFirst().map(t -> t.getCommand());
    }

}
