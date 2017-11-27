package me.dist.netty.server;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	private static int pSize = 0;
	private static int cSize = 0;
	
	public static void setpSize(int pSize) {
		Server.pSize = pSize;
	}
	public static void setcSize(int cSize) {
		Server.cSize = cSize;
	}
	private static ChannelFuture channelFuture = null;
	
	private void run() throws Exception{
		
		NioEventLoopGroup pEventLoopGroup = new NioEventLoopGroup(pSize);
		NioEventLoopGroup cEventLoopGroup = new NioEventLoopGroup(cSize);
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(pEventLoopGroup,cEventLoopGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ServerHandler());
				}
			})
			.option(ChannelOption.SO_BACKLOG, 128)		//连接数
			.childOption(ChannelOption.SO_KEEPALIVE, true);  		//长连接
		
		channelFuture = bootstrap.bind(8000).sync();
		
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.run();
	}
	
	public void closeServer() throws Exception{
		channelFuture.channel().closeFuture().sync();
	}
	
}
