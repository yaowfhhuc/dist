package orders.protocol.ssh2;

abstract class UserAuth{
  boolean start(Session session) throws Exception{
    Packet packet=session.packet;
    Buffer buf=session.buf;
    // send
    // byte      SSH_MSG_SERVICE_REQUEST(5)
    // string    service name "ssh-userauth"
    packet.reset();
    buf.putByte((byte)Session.SSH_MSG_SERVICE_REQUEST);
    buf.putString("ssh-userauth".getBytes());
    session.write(packet);

    // receive
    // byte      SSH_MSG_SERVICE_ACCEPT(6)
    // string    service name
    buf=session.read(buf);
    //System.out.println("read: 6 ? "+buf.buffer[5]);
    return buf.buffer[5]==6;
  }
}
