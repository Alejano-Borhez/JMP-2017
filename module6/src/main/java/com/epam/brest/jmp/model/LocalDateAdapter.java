package com.epam.brest.jmp.model;

import static com.epam.brest.jmp.model.Task.DATE_PATTERN;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

/**
 * DateTime custom converter
 * Created by alexander_borohov on 20.3.17.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> implements Handler {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.from(getFormatter().parse(v));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return getFormatter().format(v);
    }

    private DateTimeFormatter getFormatter() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        return builder.appendPattern(DATE_PATTERN).toFormatter();
    }

    @Override
    public boolean handleMessage(MessageContext context) {
        for (Object object: context.values()) {
            System.out.println(object);
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
