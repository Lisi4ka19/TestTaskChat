package alisa.aniskina.testtaskchat.service;

import alisa.aniskina.testtaskchat.dao.MessageDAO;
import alisa.aniskina.testtaskchat.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService{

    @Autowired
    private MessageDAO messageDAO;


    @Override
    @Transactional
    public List<Message> getAllMessages(int senderId, int recipientId, Date dateBegin, Date dateEnd) {
        return messageDAO.getAllMessages(senderId, recipientId, dateBegin, dateEnd);
    }

    @Override
    @Transactional
    public void saveMessage(Message message) {
        messageDAO.saveMessage(message);
    }

    @Override
    @Transactional
    public  List<Message> getNewMessage(int senderId, int recipientId) {
        return messageDAO.getNewMessage(senderId,recipientId);
    }
}
