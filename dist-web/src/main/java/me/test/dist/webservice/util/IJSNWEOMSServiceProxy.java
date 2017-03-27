package me.test.dist.webservice.util;

public class IJSNWEOMSServiceProxy implements me.test.dist.webservice.util.IJSNWEOMSService {
  private String _endpoint = null;
  private me.test.dist.webservice.util.IJSNWEOMSService iJSNWEOMSService = null;
  
  public IJSNWEOMSServiceProxy() {
    _initIJSNWEOMSServiceProxy();
  }
  
  public IJSNWEOMSServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIJSNWEOMSServiceProxy();
  }
  
  private void _initIJSNWEOMSServiceProxy() {
    try {
      iJSNWEOMSService = (new me.test.dist.webservice.util.JSNWEOMSServiceServiceLocator()).getJSNWEOMSServicePort();
      if (iJSNWEOMSService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iJSNWEOMSService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iJSNWEOMSService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iJSNWEOMSService != null)
      ((javax.xml.rpc.Stub)iJSNWEOMSService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public me.test.dist.webservice.util.IJSNWEOMSService getIJSNWEOMSService() {
    if (iJSNWEOMSService == null)
      _initIJSNWEOMSServiceProxy();
    return iJSNWEOMSService;
  }
  
  public java.lang.String alarmOrder(java.lang.String eomsOrderId, java.lang.String flowId, java.lang.String topic, java.lang.String orderStartTime, java.lang.String stateFlag, java.lang.String customerNo, java.lang.String customerName, java.lang.String customerServiceLevel, java.lang.String lineName, java.lang.String specialType, java.lang.String productId, java.lang.String businessServiceLevel, java.lang.String city, java.lang.String area, java.lang.String alarmNe, java.lang.String alarmName, java.lang.String currentStep, java.lang.String otherInfo) throws java.rmi.RemoteException{
    if (iJSNWEOMSService == null)
      _initIJSNWEOMSServiceProxy();
    return iJSNWEOMSService.alarmOrder(eomsOrderId, flowId, topic, orderStartTime, stateFlag, customerNo, customerName, customerServiceLevel, lineName, specialType, productId, businessServiceLevel, city, area, alarmNe, alarmName, currentStep, otherInfo);
  }
  
  public java.lang.String complainOrder(java.lang.String eomsOrderId, java.lang.String flowId, java.lang.String topic, java.lang.String orderStartTime, java.lang.String stateFlag, java.lang.String customerNo, java.lang.String customerName, java.lang.String customerServiceLevel, java.lang.String specialType, java.lang.String city, java.lang.String currentStep, java.lang.String currentDept, java.lang.String currentManager, java.lang.String currentManagerPhone, java.lang.String otherInfo) throws java.rmi.RemoteException{
    if (iJSNWEOMSService == null)
      _initIJSNWEOMSServiceProxy();
    return iJSNWEOMSService.complainOrder(eomsOrderId, flowId, topic, orderStartTime, stateFlag, customerNo, customerName, customerServiceLevel, specialType, city, currentStep, currentDept, currentManager, currentManagerPhone, otherInfo);
  }
  
  public java.lang.String networkCutover(java.lang.String eomsOrderId, java.lang.String topic, java.lang.String orderStartTime, java.lang.String sendUser, java.lang.String sendDept, java.lang.String sendUserPhone, java.lang.String orderNo, java.lang.String isCallMonitor, java.lang.String isSendCustomerservice, java.lang.String isMedia, java.lang.String isBreak, java.lang.String isInfluenceCustomer, java.lang.String customerServiceLevel, java.lang.String customerName, java.lang.String specialType, java.lang.String productId, java.lang.String lineName, java.lang.String title, java.lang.String projectType, java.lang.String isSendDuty, java.lang.String influenceArea, java.lang.String projectContent, java.lang.String projectStartTime, java.lang.String projectEndTime, java.lang.String projectManager, java.lang.String projectManagerPhone, java.lang.String sceneManager, java.lang.String sceneManagerPhone) throws java.rmi.RemoteException{
    if (iJSNWEOMSService == null)
      _initIJSNWEOMSServiceProxy();
    return iJSNWEOMSService.networkCutover(eomsOrderId, topic, orderStartTime, sendUser, sendDept, sendUserPhone, orderNo, isCallMonitor, isSendCustomerservice, isMedia, isBreak, isInfluenceCustomer, customerServiceLevel, customerName, specialType, productId, lineName, title, projectType, isSendDuty, influenceArea, projectContent, projectStartTime, projectEndTime, projectManager, projectManagerPhone, sceneManager, sceneManagerPhone);
  }
  
  
}