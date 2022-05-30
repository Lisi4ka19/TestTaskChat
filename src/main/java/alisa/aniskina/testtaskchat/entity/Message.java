package alisa.aniskina.testtaskchat.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="senderId")
    private int senderId;

    @Column(name="recipientId")
    private int recipientId;

    @Column(name="content")
    private String content;

    @Column(name="dateMessage")
    private Date date;

    @Column(name="statusId")
    private String statusId;

    public Message() {
    }

    public Message(int senderId, int recipientId, String content, Date date, String statusId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.date = date;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", statusId='" + statusId + '\'' +
                '}';
    }
}
