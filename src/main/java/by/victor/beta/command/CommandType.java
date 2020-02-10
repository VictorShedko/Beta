package by.victor.beta.command;

import by.victor.beta.command.impl.ChangeLocaleCommand;
import by.victor.beta.command.impl.DebugCommand;
import by.victor.beta.command.impl.LoginCommand;
import by.victor.beta.command.impl.LogoutCommand;
import by.victor.beta.command.impl.document.CheckDocumentCommand;
import by.victor.beta.command.impl.document.UploadDocumentCommand;
import by.victor.beta.command.impl.notify.ToNotifyCommand;
import by.victor.beta.command.impl.order.*;
import by.victor.beta.command.impl.redirect.ToCreditFormCommand;
import by.victor.beta.command.impl.redirect.ToUserMenuCommand;
import by.victor.beta.command.impl.redirect.ToAddInfoCommand;
import by.victor.beta.command.impl.user.*;
import by.victor.beta.command.impl.redirect.ToOrderCreateFormCommand;
import by.victor.beta.command.impl.redirect.ToRegistrationRedirectCommand;


public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTER(new RegistrationCommand()),
    TO_REGISTRATION(new ToRegistrationRedirectCommand()),
    TO_NOTIFY(new ToNotifyCommand()),
    TO_USER_MENU(new ToUserMenuCommand()),
    TO_CREDIT_FORM(new ToCreditFormCommand()),
    CREDIT_ACCOUNT(new CreditAccountCommand()),
    SHOW_AVAILABLE_ORDERS(new ShowAvailableOrdersCommand()),
    TO_ADD_INFO(new ToAddInfoCommand()),
    UPLOAD_PHOTO(new UploadPhotoCommand()),
    UPLOAD_DOCUMENT(new UploadDocumentCommand()),
    LOGOUT(new LogoutCommand()),
    VERIFY_USER(new VerifyExecutorCommand()),
    CHECK_DOCUMENT(new CheckDocumentCommand()),
    TO_ADD_NOTIFY_FORM(new DebugCommand()),
    ADD_NOTIFY(new DebugCommand()),
    DELETE_USER(new DeleteUserCommand()),
    SHOW_ORDERS_CUSTOMER(new ShowCustomerOrderHistory()),
    SHOW_ORDERS_EXECUTOR(new ShowExecutorOrderHistory()),
    TO_CREATE_ORDER_FORM(new ToOrderCreateFormCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    ACCEPT_ORDER(new AcceptOrderCommand()),
    DELETE_ORDER(new CancelOrderCustomerCommand()),
    REFUSE_ORDER(new RefuseOrderExecutorCommand()),
    SHOW_USER_PROFILE(new ShowUserProfileCommand()),
    SHOW_USER_BY_ROLE(new ShowUserByRoleCommand()),
    SHOW_USER_BY_STATUS(new ShowUserByStatusCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand());

    private AbstractCommand command;
    CommandType(AbstractCommand command){
        this.command=command;
    }
    public AbstractCommand getCommand(){
        return command;
    }
}
