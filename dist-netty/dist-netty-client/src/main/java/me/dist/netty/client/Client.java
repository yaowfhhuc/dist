package me.dist.netty.client;

import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

public class Client {

	private static String host="localhost";
	private static int port=4000;
	
	public static void setHost(String host) {
		Client.host = host;
	}
	public static void setPort(int port) {
		Client.port = port;
	}
	public static void main(String[] args) throws Exception {
		
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new NioEventLoopGroup())
		.channel(NioSocketChannel.class)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ByteToMessageDecoder() {
					
					@Override
					protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
						out.add(in.readByte());
					}
				},new ClientHandler());
			}
		});
		
		
		ChannelFuture f = bootstrap.connect(host, port).sync(); // (5)
		
	}
}
