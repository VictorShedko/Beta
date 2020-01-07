package by.victor.beta.command;


import by.victor.beta.entity.Role;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public class CommandProvider {
    private EnumMap<Role, List<CommandType>> roleCommandMapping;
    private EnumMap<CommandType,AbstractCommand>

    public Optional<AbstractCommand> findCommand(Role userRole, String commandName){
        return roleCommandMapping.get(userRole).stream().filter(t->t.name().equalsIgnoreCase(commandName)).findFirst().map(t->t.getCommand());
    }

}
