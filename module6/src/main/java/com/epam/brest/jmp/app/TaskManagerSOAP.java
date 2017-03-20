package com.epam.brest.jmp.app;

import com.epam.brest.jmp.model.LocalDateAdapter;
import com.epam.brest.jmp.soap.WebServiceController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.ws.Endpoint;

/**
 *
 * Created by alexander_borohov on 20.3.17.
 */
public class TaskManagerSOAP {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();

        Endpoint endpoint = Endpoint.create(new WebServiceController());
        logger.info("Created Endpoint");
        endpoint.getBinding().getHandlerChain().add(0, new LocalDateAdapter());
        endpoint.publish("http://localhost:8080/app");
    }
}
