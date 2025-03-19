package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Service.AccountService;
import Model.Message;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::newUserRegistrationHandler);
        app.post("/login", this::userLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIDHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIDHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    /**
     * This is the handler for registering a new user.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void newUserRegistrationHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount==null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
    }

    /**
     * This is the handler for registering a user login.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void userLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        boolean loginFlag = accountService.loginIsSuccessful(account);
        Account returnAccount = accountService.getAccount(account);
        if(loginFlag){
            ctx.json(mapper.writeValueAsString(returnAccount));
        }else{
            ctx.status(401);
        }
    }

    /**
     * This is the handler for posting a new message.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.newMessage(message);
        if(addedMessage==null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(addedMessage));
        }
    }

    /**
     * This is the handler for getting all Messages.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    /**
     * This is the handler for getting a message by its ID
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessageByIDHandler(Context ctx) throws JsonProcessingException {
        Integer id = Integer.parseInt(ctx.pathParam("message_id"));
        Message returnMessage = messageService.getMessage(id);
        if (returnMessage != null) {
            ctx.json(returnMessage);
        }
   }

    /**
     * This is the handler for deleting a message by its ID.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessageByIDHandler(Context ctx) {

    }

    /**
     * This is the handler updating a message.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessageHandler(Context ctx) {

    }
    
    /**
     * This is the handler for getting all messages posted by a specified user.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessagesByUserHandler(Context ctx) {

    }
}