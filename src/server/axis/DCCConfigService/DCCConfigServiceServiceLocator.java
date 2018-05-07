/**
 * DCCConfigServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server.axis.DCCConfigService;

public class DCCConfigServiceServiceLocator extends org.apache.axis.client.Service implements server.axis.DCCConfigService.DCCConfigServiceService {

	public DCCConfigServiceServiceLocator() {
	}


	public DCCConfigServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public DCCConfigServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for DCCConfigService
	private java.lang.String DCCConfigService_address = "http://localhost:8080/agentProject/services/DCCConfigService";

	public java.lang.String getDCCConfigServiceAddress() {
		return DCCConfigService_address;
	}


	public DCCConfigServiceServiceLocator(String url) {
		this.DCCConfigService_address = url;
	}


	// The WSDD service name defaults to the port name.
	private java.lang.String DCCConfigServiceWSDDServiceName = "DCCConfigService";

	public java.lang.String getDCCConfigServiceWSDDServiceName() {
		return DCCConfigServiceWSDDServiceName;
	}

	public void setDCCConfigServiceWSDDServiceName(java.lang.String name) {
		DCCConfigServiceWSDDServiceName = name;
	}

	public server.axis.DCCConfigService.DCCConfigService getDCCConfigService() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(DCCConfigService_address);
		}
		catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getDCCConfigService(endpoint);
	}

	public server.axis.DCCConfigService.DCCConfigService getDCCConfigService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			server.axis.DCCConfigService.DCCConfigServiceSoapBindingStub _stub = new server.axis.DCCConfigService.DCCConfigServiceSoapBindingStub(portAddress, this);
			_stub.setPortName(getDCCConfigServiceWSDDServiceName());
			return _stub;
		}
		catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setDCCConfigServiceEndpointAddress(java.lang.String address) {
		DCCConfigService_address = address;
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (server.axis.DCCConfigService.DCCConfigService.class.isAssignableFrom(serviceEndpointInterface)) {
				server.axis.DCCConfigService.DCCConfigServiceSoapBindingStub _stub = new server.axis.DCCConfigService.DCCConfigServiceSoapBindingStub(new java.net.URL(DCCConfigService_address), this);
				_stub.setPortName(getDCCConfigServiceWSDDServiceName());
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
		if ("DCCConfigService".equals(inputPortName)) {
			return getDCCConfigService();
		}
		else  {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://server/axis/DCCConfigService   ", "DCCConfigServiceService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://server/axis/DCCConfigService   ", "DCCConfigService"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("DCCConfigService".equals(portName)) {
			setDCCConfigServiceEndpointAddress(address);
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
