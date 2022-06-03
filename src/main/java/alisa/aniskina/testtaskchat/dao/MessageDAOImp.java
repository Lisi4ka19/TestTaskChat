package alisa.aniskina.testtaskchat.dao;

import alisa.aniskina.testtaskchat.entity.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        HashMap<String, Date> parameters = new HashMap<>();


        if (dateBegin != null && dateEnd == null) {
            testQuery += " and date >= :dateBegin";

            parameters.put("dateBegin", dateBegin);

        } else if (dateBegin == null && dateEnd != null) {
            testQuery += " and date<= :dateEnd";

            parameters.put("dateEnd", dateEnd);
        } else if (dateBegin != null && dateEnd != null) {
            testQuery += " and date >= :dateBegin and date<= :dateEnd";

            parameters.put("dateBegin", dateBegin);
            parameters.put("dateEnd", dateEnd);
        }

        query = session.createQuery(testQuery, Message.class);
        query.setParameter("senderId", senderId);
        query.setParameter("recipientId", recipientId);

        for (Map.Entry<String, Date> entry: parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

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
