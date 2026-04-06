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

  public Message getMessageById(int id){
    return messageDAO.getMessageById(id);
  }

  public Message deleteMessageById(int id){
    Message existing = messageDAO.getMessageById(id);
    if(existing == null) return null;
    messageDAO.deleteMessageById(id);
    return existing;
  }

  public Message patchMessageById(int id, String text){
    if(text == null || text.isBlank() || text.length() > 255) return null;
    if(messageDAO.getMessageById(id) == null) return null;

    if (messageDAO.patchMessageById(id, text) == 1) {
      return messageDAO.getMessageById(id);
    }else return null;
  }

  public List<Message> getAllMessagesByAccountId(int id){
    return messageDAO.getAllMessagesByAccountId(id);
}

}

