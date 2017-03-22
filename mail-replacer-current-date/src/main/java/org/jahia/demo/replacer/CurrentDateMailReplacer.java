package org.jahia.demo.replacer;

import org.apache.commons.lang.StringUtils;
import org.jahia.bin.Jahia;
import org.jahia.demo.service.api.MailWrapperReplacer;
import org.jahia.services.content.JCRSessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import java.time.LocalDate;

@Component(name = "org.jahia.demo.currentDateMailReplacer", service = MailWrapperReplacer.class, property = {
        Constants.SERVICE_PID + "=org.jahia.demo.currentDateMailReplacer",
        Constants.SERVICE_DESCRIPTION + "=replace current user in message",
        Constants.SERVICE_VENDOR + "=" + Jahia.VENDOR_NAME}, immediate = true)
public class CurrentDateMailReplacer implements MailWrapperReplacer {

    @Override
    public String replaceInMessage(String message) {
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMMM, yyyy");
        String str = fmt.print(dt);

        return StringUtils.replace(message, "{{currentDate}}", str);
    }
}
