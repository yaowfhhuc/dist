package orders.protocol.ssh2;

class UserAuthNone extends UserAuth{
  private String methods=null;
  private UserInfo userinfo;
  UserAuthNone(UserInfo userinfo){
   this.userinfo=userinfo;
  }

  public boolean start(Session session) throws Exception{
    super.start(session);
//System.out.println("UserAuthNone: start");
    Packet packet=session.packet;
    Buffer buf=session.buf;
    final String username=session.username;

    byte[] _username=null;
    try{ _username=username.getBytes("UTF-8"); }
    catch(java.io.UnsupportedEncodingException e){
      _username=username.getBytes();
    }

    // send
    // byte      SSH_MSG_USERAUTH_REQUEST(50)
    // string    user name
    // string    service name ("ssh-connection")
    // string    "none"
    packet.reset();
    buf.putByte((byte)Session.SSH_MSG_USERAUTH_REQUEST);
    buf.putString(_username);
    buf.putString("ssh-connection".getBytes());
    buf.putString("none".getBytes());
    session.write(packet);

    loop:
    while(true){
      // receive
      // byte      SSH_MSG_USERAUTH_SUCCESS(52)
      // string    service name
      buf=session.read(buf);
//System.out.println("UserAuthNone: read: 52 ? "+    buf.buffer[5]);
      if(buf.buffer[5]==Session.SSH_MSG_USERAUTH_SUCCESS){
	return true;
      }
      if(buf.buffer[5]==Session.SSH_MSG_USERAUTH_BANNER){
	buf.getInt(); buf.getByte(); buf.getByte();
	byte[] _message=buf.getString();
	@SuppressWarnings("unused")
	byte[] lang=buf.getString();
	String message=null;
	try{ message=new String(_message, "UTF-8"); }
	catch(java.io.UnsupportedEncodingException e){
	  message=new String(_message);
	}
	if(userinfo!=null){
	  userinfo.showMessage(message);
	}
	continue loop;
      }
      if(buf.buffer[5]==Session.SSH_MSG_USERAUTH_FAILURE){
	buf.getInt(); buf.getByte(); buf.getByte(); 
	byte[] foo=buf.getString();
	@SuppressWarnings("unused")
	int partial_success=buf.getByte();
	methods=new String(foo);
//System.out.println("UserAuthNONE: "+methods+
//		   " partial_success:"+(partial_success!=0));
//	if(partial_success!=0){
//	  throw new JSchPartialAuthException(new String(foo));
//	}
        break;
      }
      else{
//      System.out.println("USERAUTH fail ("+buf.buffer[5]+")");
	throw new SshException("USERAUTH fail ("+buf.buffer[5]+")");
      }
    }
   //throw new JSchException("USERAUTH fail");
    return false;
  }
  String getMethods(){
    return methods;
  }
}
