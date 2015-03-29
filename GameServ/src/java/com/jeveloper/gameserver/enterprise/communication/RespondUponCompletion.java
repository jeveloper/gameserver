/*
 * RespondUponCompletion.java
 *
 * Created on April 13, 2007, 4:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.enterprise.communication;

import com.jeveloper.gameserver.core.BaseGame;
import com.jeveloper.gameserver.enterprise.mdb.Utility;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author serge
 */
public class RespondUponCompletion {
    
    /** Creates a new instance of RespondUponCompletion */
    public RespondUponCompletion() {
    }
    public static void sendReply(BaseGame obj){
        
        Queue queue = null;
        QueueConnection connection = null;
        QueueSession session = null;
        MessageProducer messageProducer = null;
        try {
            
            InitialContext ctx = Utility.getSenderInitialContext();
            queue = (Queue) ctx.lookup("queue/gameComplete");
            QueueConnectionFactory factory =
                    (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
            connection = factory.createQueueConnection();
            session = connection.createQueueSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            
            ObjectMessage message = session.createObjectMessage();            
            message.setObject(obj);
            messageProducer.send(message);
            messageProducer.close();
            connection.close();
            
            
        } catch (JMSException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
}
