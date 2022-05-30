package alisa.aniskina.testtaskchat.service;

import alisa.aniskina.testtaskchat.entity.Message;

import java.util.Date;
import java.util.List;

public interface MessageService {

    public List<Message> getAllMessages(int senderId, int recipientId, Date dateBegin, Date dateEnd);

    public void saveMessage(Message message);

    public  List<Message> getNewMessage(int senderId, int recipientId);

}
