package orders.entry;

import java.io.Serializable;

public class AccountEntryRmi implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name,//账号名
                        password,//密码
                        accounType,//账号类型  1:核查    2:配置/下发
                        loginScript,//登录脚本
                        loginType,//登录方式
                        successWaitfor;//成功提示符
    /**
     * 账号名
     * @return
     */
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * 账号密码
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * 账号类型  1:核查  2:配置/下发
     * @return
     */
    public String getAccounType()
    {
        return accounType;
    }

    public void setAccounType(String accounType)
    {
        this.accounType = accounType;
    }

    /**
     * 登录等待脚本
     * @return
     */
    public String getLoginScript()
    {
        return loginScript;
    }

    public void setLoginScript(String loginScript)
    {
        this.loginScript = loginScript;
    }
   
    /**
     * 登录方式 
     * @return
     */
    public String getLoginType()
    {
        return loginType;
    }
    
    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }
   
    /**
     * 登录成功后的提示符
     * @return
     */
    public String getSuccessWaitfor()
    {
        return successWaitfor;
    }

    public void setSuccessWaitfor(String successWaitfor)
    {
        this.successWaitfor = successWaitfor;
    }

}
