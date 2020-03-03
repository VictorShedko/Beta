package by.victor.beta.command;

import by.victor.beta.command.impl.ChangeLocaleCommand;
import by.victor.beta.command.impl.LoginCommand;
import by.victor.beta.command.impl.LogoutCommand;
import by.victor.beta.command.impl.document.CheckDocumentCommand;
import by.victor.beta.command.impl.document.ShowMyDocumentsCommand;
import by.victor.beta.command.impl.document.UploadDocumentCommand;
import by.victor.beta.command.impl.notify.ToNotifyCommand;
import by.victor.beta.command.impl.order.*;
import by.victor.beta.command.impl.redirect.*;
import by.victor.beta.command.impl.user.*;


public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTER(new RegistrationCommand()),
    TO_REGISTRATION(new ToRegistrationCommand()),
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
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    REFRESH(new RefreshSessionCommand()),
    RESEND_EMAIL(new ResendVerificationEmailCommand()),
    EMAIL_VERIFY(new EmailVerifyCommand()),
    SHOW_MY_DOCUMENTS(new ShowMyDocumentsCommand()),
    TO_RESULT(new ToOperationResultCommand()),
    SHOW_ALL_USERS(new ShowAllUserCommand());
    
    private Command command;
    CommandType(Command command){
        this.command=command;
    }
    public Command getCommand(){
        return command;
    }
}
