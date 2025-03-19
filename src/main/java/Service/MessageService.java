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
}
