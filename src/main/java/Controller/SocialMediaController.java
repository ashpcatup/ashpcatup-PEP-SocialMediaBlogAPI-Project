package Controller;
// test save 2

import com.fasterxml.jackson.core.JsonProcessingException;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService = new AccountService();
    MessageService messageService = new MessageService();

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::addMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.start(8080);
        return app;
    }

    private void registerHandler(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        Account added = accountService.addAccount(account);
        if(added != null){
            ctx.status(200).json(added);
        } else {
            ctx.status(400);
        }
    }

    private void addMessageHandler(Context ctx) {
        Message message = ctx.bodyAsClass(Message.class);
        Message added = messageService.addMessage(message);
        if(added != null){
            ctx.status(200).json(added);
        } else {
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx){
        List<Message> messageList = messageService.getAllMessages();
        ctx.status(200).json(messageList);
    }

    private void loginHandler(Context ctx) {
        Account account = ctx.bodyAsClass(Account.class);
        if (account == null) {
            ctx.status(400).result("Unable to parse request");
            return;
        }
        Account verifiedAccount = accountService.verifyAccount(account);
        if(verifiedAccount != null){
            ctx.status(200).json(verifiedAccount);
        } else {
            ctx.status(401);
        }
    }








    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
}


