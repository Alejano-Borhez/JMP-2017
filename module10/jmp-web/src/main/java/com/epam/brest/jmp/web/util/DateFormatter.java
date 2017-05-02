package com.epam.brest.jmp.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Simple String to Date formatter
 * Created by alexander_borohov on 2.5.17.
 */
@Component
public class DateFormatter implements Formatter<Date> {
    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        final SimpleDateFormat format = createDateFormat(locale);
        return format.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        final SimpleDateFormat format = createDateFormat(locale);
        return format.format(object);
    }

    private SimpleDateFormat createDateFormat(final Locale locale) {
        final String format = this.messageSource.getMessage("date.format", null, locale);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat;
    }
}
