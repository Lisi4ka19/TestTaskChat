package alisa.aniskina.testtaskchat.dao;

import alisa.aniskina.testtaskchat.entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MessageDAOImp implements MessageDAO{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Message> getAllMessages(int senderId, int recipientId, Date dateBegin, Date dateEnd) {

        Session session = sessionFactory.getCurrentSession();
        Query<Message> query = null;
        String testQuery = "from Message where " +
                "(senderId = :senderId or senderId = :recipientId) " +
                "and (recipientId =  :senderId or recipientId = :recipientId)";

        if(dateBegin==null&&dateEnd==null){
            query = session.createQuery(testQuery, Message.class);
        }else if(dateBegin!=null&&dateEnd==null){
            testQuery +=" and date >= :dateBegin";
            query = session.createQuery(testQuery, Message.class);
            query.setParameter("dateBegin", dateBegin);
        }else if(dateBegin==null&&dateEnd!=null){
            testQuery =" and date<= :dateEnd";
            query = session.createQuery(testQuery, Message.class);
            query.setParameter("dateEnd", dateEnd);
        }else if(dateBegin!=null&&dateEnd!=null) {
            testQuery = " and date >= :dateBegin and date<= :dateEnd";
            query = session.createQuery(testQuery, Message.class);
            query.setParameter("dateBegin", dateBegin);
            query.setParameter("dateEnd", dateEnd);

        }
        query.setParameter("senderId", senderId);
        query.setParameter("recipientId", recipientId);

        List<Message> allMessages = query.getResultList();

        return allMessages;
    }

    @Override
    public void saveMessage(Message message) {
        Session session = sessionFactory.getCurrentSession();


        session.saveOrUpdate(message);
    }

    @Override
    public  List<Message> getNewMessage(int senderId, int recipientId) {
        Session session = sessionFactory.getCurrentSession();

        String testQuery = "from Message where senderId = :senderId " +
                            " and recipientId = :recipientId and statusId = :status";

        Query<Message> query = session.createQuery(testQuery, Message.class);
        query.setParameter("senderId", senderId);
        query.setParameter("recipientId", recipientId);
        query.setParameter("status", "0");

        List<Message> newMessages = query.getResultList();

        if (newMessages.size()!=0){
            newMessages.stream().forEach(element->element.setStatusId("1"));
        }
        return newMessages;
    }
}
