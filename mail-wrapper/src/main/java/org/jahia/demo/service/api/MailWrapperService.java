package org.jahia.demo.service.api;

/**
 * Created by hagon on 22/03/2017.
 */
public interface MailWrapperService {

    /**
     * Wrap and send the mail
     * @param message
     */
    void wrapAndSendMessage(String message);
}
