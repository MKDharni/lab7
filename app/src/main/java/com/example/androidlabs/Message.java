package com.example.androidlabs;


public class Message {
    public String message;
    public boolean isSend;
    public long messageID;
    private String action;

    public Message(String message, boolean hasSend )
    {
        this.message = message;
        this.isSend = hasSend;
    }
     public Message()
    {
    }
     public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;

    }
    public void setAction(String action)
    {
        this.action = action;
    }
    public String getAction()
    {
        return this.action;
    }
    public Message(String message, boolean isSend, long messageID)
    {
        this.message=message;
        this.isSend=isSend;
        this.messageID=messageID;
    }
    public boolean isGone()
    {
        return isSend;
    }
    public void setSend(boolean send)
    {
        isSend = send;
    }
     public long getMessageID()
    {
        return messageID;
    }
    public void setMessageID(long messageID)
    {
        this.messageID = messageID;
    }
}