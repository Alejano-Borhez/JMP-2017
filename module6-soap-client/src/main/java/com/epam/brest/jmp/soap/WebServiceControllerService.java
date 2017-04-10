
package com.epam.brest.jmp.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WebServiceControllerService", targetNamespace = "http://soap.jmp.brest.epam.com/", wsdlLocation = "http://localhost:8080/app?wsdl")
public class WebServiceControllerService
    extends Service
{

    private final static URL WEBSERVICECONTROLLERSERVICE_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICECONTROLLERSERVICE_EXCEPTION;
    private final static QName WEBSERVICECONTROLLERSERVICE_QNAME = new QName("http://soap.jmp.brest.epam.com/", "WebServiceControllerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/app?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICECONTROLLERSERVICE_WSDL_LOCATION = url;
        WEBSERVICECONTROLLERSERVICE_EXCEPTION = e;
    }

    public WebServiceControllerService() {
        super(__getWsdlLocation(), WEBSERVICECONTROLLERSERVICE_QNAME);
    }

    public WebServiceControllerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICECONTROLLERSERVICE_QNAME, features);
    }

    public WebServiceControllerService(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICECONTROLLERSERVICE_QNAME);
    }

    public WebServiceControllerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICECONTROLLERSERVICE_QNAME, features);
    }

    public WebServiceControllerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceControllerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebInterface
     */
    @WebEndpoint(name = "WebServiceControllerPort")
    public WebInterface getWebServiceControllerPort() {
        return super.getPort(new QName("http://soap.jmp.brest.epam.com/", "WebServiceControllerPort"), WebInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebInterface
     */
    @WebEndpoint(name = "WebServiceControllerPort")
    public WebInterface getWebServiceControllerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.jmp.brest.epam.com/", "WebServiceControllerPort"), WebInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICECONTROLLERSERVICE_EXCEPTION!= null) {
            throw WEBSERVICECONTROLLERSERVICE_EXCEPTION;
        }
        return WEBSERVICECONTROLLERSERVICE_WSDL_LOCATION;
    }

}