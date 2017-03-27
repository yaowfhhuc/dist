/**
 * IJSNWEOMSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package me.test.dist.webservice.util;

public interface IJSNWEOMSService extends java.rmi.Remote {
    public java.lang.String alarmOrder(java.lang.String eomsOrderId, java.lang.String flowId, java.lang.String topic, java.lang.String orderStartTime, java.lang.String stateFlag, java.lang.String customerNo, java.lang.String customerName, java.lang.String customerServiceLevel, java.lang.String lineName, java.lang.String specialType, java.lang.String productId, java.lang.String businessServiceLevel, java.lang.String city, java.lang.String area, java.lang.String alarmNe, java.lang.String alarmName, java.lang.String currentStep, java.lang.String otherInfo) throws java.rmi.RemoteException;
    public java.lang.String complainOrder(java.lang.String eomsOrderId, java.lang.String flowId, java.lang.String topic, java.lang.String orderStartTime, java.lang.String stateFlag, java.lang.String customerNo, java.lang.String customerName, java.lang.String customerServiceLevel, java.lang.String specialType, java.lang.String city, java.lang.String currentStep, java.lang.String currentDept, java.lang.String currentManager, java.lang.String currentManagerPhone, java.lang.String otherInfo) throws java.rmi.RemoteException;
    public java.lang.String networkCutover(java.lang.String eomsOrderId, java.lang.String topic, java.lang.String orderStartTime, java.lang.String sendUser, java.lang.String sendDept, java.lang.String sendUserPhone, java.lang.String orderNo, java.lang.String isCallMonitor, java.lang.String isSendCustomerservice, java.lang.String isMedia, java.lang.String isBreak, java.lang.String isInfluenceCustomer, java.lang.String customerServiceLevel, java.lang.String customerName, java.lang.String specialType, java.lang.String productId, java.lang.String lineName, java.lang.String title, java.lang.String projectType, java.lang.String isSendDuty, java.lang.String influenceArea, java.lang.String projectContent, java.lang.String projectStartTime, java.lang.String projectEndTime, java.lang.String projectManager, java.lang.String projectManagerPhone, java.lang.String sceneManager, java.lang.String sceneManagerPhone) throws java.rmi.RemoteException;
}
