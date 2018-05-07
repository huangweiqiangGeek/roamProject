/**
 * DCCConfigService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server.axis.DCCConfigService;

public interface DCCConfigService extends java.rmi.Remote {
    public byte[] dccFilterZip(int mpmax, int cmdcode, java.lang.String[] avpcode1, java.lang.String[] avpvalue1, java.lang.String[] isHave, java.lang.String supper) throws java.rmi.RemoteException;
    public java.lang.Object[] dccFilter(int mpmax, int cmdcode, java.lang.String[] avpcode1, java.lang.String[] avpvalue1, java.lang.String[] isHave) throws java.rmi.RemoteException;
}