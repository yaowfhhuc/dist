/**
 * JSNWEOMSServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package me.test.dist.webservice.util;

public class JSNWEOMSServiceServiceLocator extends org.apache.axis.client.Service implements me.test.dist.webservice.util.JSNWEOMSServiceService {

    public JSNWEOMSServiceServiceLocator() {
    }


    public JSNWEOMSServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public JSNWEOMSServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for JSNWEOMSServicePort
    private java.lang.String JSNWEOMSServicePort_address = "http://10.40.94.97:8888/axis2/services/JSNWEOMSService";

    public java.lang.String getJSNWEOMSServicePortAddress() {
        return JSNWEOMSServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String JSNWEOMSServicePortWSDDServiceName = "JSNWEOMSServicePort";

    public java.lang.String getJSNWEOMSServicePortWSDDServiceName() {
        return JSNWEOMSServicePortWSDDServiceName;
    }

    public void setJSNWEOMSServicePortWSDDServiceName(java.lang.String name) {
        JSNWEOMSServicePortWSDDServiceName = name;
    }

    public me.test.dist.webservice.util.IJSNWEOMSService getJSNWEOMSServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(JSNWEOMSServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getJSNWEOMSServicePort(endpoint);
    }

    public me.test.dist.webservice.util.IJSNWEOMSService getJSNWEOMSServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            me.test.dist.webservice.util.JSNWEOMSServiceServiceSoapBindingStub _stub = new me.test.dist.webservice.util.JSNWEOMSServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getJSNWEOMSServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setJSNWEOMSServicePortEndpointAddress(java.lang.String address) {
        JSNWEOMSServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (me.test.dist.webservice.util.IJSNWEOMSService.class.isAssignableFrom(serviceEndpointInterface)) {
                me.test.dist.webservice.util.JSNWEOMSServiceServiceSoapBindingStub _stub = new me.test.dist.webservice.util.JSNWEOMSServiceServiceSoapBindingStub(new java.net.URL(JSNWEOMSServicePort_address), this);
                _stub.setPortName(getJSNWEOMSServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("JSNWEOMSServicePort".equals(inputPortName)) {
            return getJSNWEOMSServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service/", "JSNWEOMSServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service/", "JSNWEOMSServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("JSNWEOMSServicePort".equals(portName)) {
            setJSNWEOMSServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
