package com.java.demo.datastore.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "securebox_chat_message")
public class ChatMessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cm_id")
    private BigInteger id;

    @Column(name = "cm_sender")
    private BigInteger sender;

    @Column(name = "cm_content")
    private String content;

    @Column(name = "cm_type")
    private String type;

    @Column(name = "cm_cr_id")
    private BigInteger chatRoomId;

    @Column(name = "cm_created_date")
    private Timestamp createdDate;

    @Column(name = "cm_is_deleted")
    private boolean isDeleted;

    @Column(name = "cm_is_seen")
    private boolean isSeen;

    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public BigInteger getSender()
    {
        return sender;
    }

    public void setSender(BigInteger sender)
    {
        this.sender = sender;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BigInteger getChatRoomId()
    {
        return chatRoomId;
    }

    public void setChatRoomId(BigInteger chatRoomId)
    {
        this.chatRoomId = chatRoomId;
    }

    public Timestamp getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate)
    {
        this.createdDate = createdDate;
    }

    public boolean isDeleted()
    {
        return isDeleted;
    }

    public void setDeleted(boolean deleted)
    {
        isDeleted = deleted;
    }

    public boolean isSeen()
    {
        return isSeen;
    }

    public void setSeen(boolean seen)
    {
        isSeen = seen;
    }
}
