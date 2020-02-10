package by.victor.beta.command;


import by.victor.beta.entity.Role;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger(CommandProvider.class);
    private EnumMap<Role, List<CommandType>> roleCommandMapping;

    public CommandProvider() {
        roleCommandMapping = new EnumMap<>(Role.class);

        List<CommandType> defaultUserCommands = List.of(CommandType.TO_REGISTRATION,
                CommandType.LOGIN, CommandType.REGISTER);
        roleCommandMapping.put(Role.DEFAULT, defaultUserCommands);

        List<CommandType> adminCommands = List.of(CommandType.DELETE_USER,CommandType.LOGOUT,
                CommandType.CHANGE_LOCALE,CommandType.SHOW_USER_BY_STATUS,CommandType.SHOW_USER_PROFILE,
                CommandType.CHECK_DOCUMENT,CommandType.VERIFY_USER,
                CommandType.SHOW_USER_BY_ROLE,CommandType.DELETE_USER);
        roleCommandMapping.put(Role.ADMIN, adminCommands);

        List<CommandType> customerCommands = List.of(CommandType.CREATE_ORDER,CommandType.TO_ADD_INFO,
                CommandType.SHOW_ORDERS_CUSTOMER, CommandType.TO_CREATE_ORDER_FORM,
                CommandType.UPLOAD_PHOTO,CommandType.CREDIT_ACCOUNT,CommandType.TO_CREDIT_FORM,
                CommandType.TO_NOTIFY,  CommandType.CHANGE_LOCALE,
                CommandType.LOGOUT, CommandType.DELETE_ORDER, CommandType.TO_USER_MENU);
        roleCommandMapping.put(Role.CUSTOMER, customerCommands);

        List<CommandType> executorCommands = List.of(CommandType.ACCEPT_ORDER,
                CommandType.LOGOUT, CommandType.TO_USER_MENU, CommandType.ACCEPT_ORDER,
                CommandType.SHOW_AVAILABLE_ORDERS,CommandType.TO_ADD_INFO,  CommandType.CHANGE_LOCALE,
                CommandType.UPLOAD_PHOTO,CommandType.CREDIT_ACCOUNT,CommandType.TO_CREDIT_FORM,
                CommandType.TO_NOTIFY,CommandType.REFUSE_ORDER,CommandType.UPLOAD_DOCUMENT,
                CommandType.SHOW_ORDERS_EXECUTOR);
        roleCommandMapping.put(Role.EXECUTOR, executorCommands);
    }

    public Optional<AbstractCommand> findCommand(Role userRole, String commandName) {
        logger.log(Level.DEBUG, " role " + userRole + " " + commandName);
        return roleCommandMapping.get(userRole)
                .stream()
                .filter(t -> t.name().equalsIgnoreCase(commandName))
                .findFirst()
                .map(CommandType::getCommand);
    }

}
