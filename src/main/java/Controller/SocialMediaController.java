package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import java.util.List;


public class SocialMediaController {
    AccountService accountService = new AccountService();
    MessageService messageService = new MessageService();

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::addMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);
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

    private void getMessageByIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(id);
    
        if(message != null){
            ctx.json(message);
        } else {
            ctx.result("");
        }
    }

    private void deleteMessageByIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deleted = messageService.deleteMessageById(id);
    
        if(deleted != null){
            ctx.json(deleted); 
        } else {
            ctx.result("");
        }
    }

    private void patchMessageByIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message update = ctx.bodyAsClass(Message.class);
 
        Message newMessage = messageService.patchMessageById(id, update.getMessage_text());
        if(newMessage != null){
            ctx.json(newMessage);
        }else{
            ctx.status(400);
        }
    }

    private void getAllMessagesByAccountIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messageService.getAllMessagesByAccountId(id);
        ctx.json(messages);
    }
}


