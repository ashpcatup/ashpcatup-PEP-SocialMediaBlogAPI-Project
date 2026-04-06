package Service;

import Model.Message;
import java.util.*;
import DAO.MessageDAO;

public class MessageService {
  private MessageDAO messageDAO;

  public MessageService(){
    messageDAO = new MessageDAO();
  }

  public MessageService(MessageDAO messageDAO){
    this.messageDAO = messageDAO;
  }

  public Message addMessage(Message message){
    if (message.getMessage_text() == null || message.getMessage_text().isBlank()) return null;
    if (message.getMessage_text().length() > 255) return null;
    int id = message.getPosted_by();
    if (messageDAO.getAccountById(id) == null) return null;
    return messageDAO.insertMessage(message);
  }

  public List<Message> getAllMessages(){
    return messageDAO.getAllMessages();
  }

}

