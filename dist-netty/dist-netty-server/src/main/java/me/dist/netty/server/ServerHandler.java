package me.dist.netty.server;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class ServerHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg);
			ctx.write(msg);
			ctx.flush();
	}
	
	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		System.out.println("server accept "+remoteAddress.toString());
		super.connect(ctx, remoteAddress, localAddress, promise);
	}
}