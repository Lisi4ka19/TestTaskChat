package alisa.aniskina.testtaskchat.dao;

import alisa.aniskina.testtaskchat.entity.Message;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;


public interface MessageDAO {

    public List<Message> getAllMessages(int senderId, int recipientId, Date dateBegin, Date dateEnd);

    public void saveMessage(Message message);

    public  List<Message> getNewMessage(int senderId, int recipientId);
}
