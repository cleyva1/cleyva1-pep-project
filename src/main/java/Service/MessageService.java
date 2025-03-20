package Service;

import Model.Message;
import DAO.MessageDAO;
import Service.AccountService;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message newMessage(Message message) {
        if (message.getMessage_text().length() <= 255 && message.getMessage_text().length() > 0) {
            return messageDAO.insertMessage(message);
        } else {
            return null;
        }
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessage(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessage(int id) {
        if (messageDAO.getMessageById(id) != null) {
            Message returnMessage = messageDAO.getMessageById(id);
            messageDAO.deleteMessage(id);
            return returnMessage;
        }
        return null;
    }

    public Message updateMessage(int id, String messageString) {
        if (messageDAO.getMessageById(id) != null && messageString != "" && messageString.length() <= 255) {
            messageDAO.updateMessage(id, messageString);
            return messageDAO.getMessageById(id);
        }
        return null;
    }

    public List<Message> getAllMessagesByUser(int id) {
        return messageDAO.getAllMessagesByAccountID(id);
    }
}
