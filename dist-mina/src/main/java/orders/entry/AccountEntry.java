package orders.entry;

import java.io.Serializable;

public class AccountEntry implements Serializable
{
    private String user, password,suScript,prompt;

    private Integer type;

    public String getUser()
    {
        return user;
    }
     public AccountEntry(){}
    
    public AccountEntry(String user, String password, Integer type)
    {
        super();
        this.user = user;
        this.password = password;
        this.type = type;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }
    public String getSuScript()
    {
        return suScript;
    }
    public void setSuScript(String suScript)
    {
        this.suScript = suScript;
    }
    public String getPrompt()
    {
        return prompt;
    }
    public void setPrompt(String prompt)
    {
        this.prompt = prompt;
    }

}
