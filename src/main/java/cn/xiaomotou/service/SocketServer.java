package cn.xiaomotou.service;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;

/**
 * @desc：
 * @name：niko
 * @Date：2024/4/1 12:33
 */
public class SocketServer {

    public static void main(String[] args) {

        SocketServer socketServer = new SocketServer();
        socketServer.start();

    }


    @SneakyThrows
    private void start(){

        ServerBootstrap serverBootstrap = new ServerBootstrap().group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class).childHandler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {

                                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                                     //   .addLast(new StringDecoder())
                                  /*      .addLast(new ChannelInboundHandlerAdapter(){
                                            @Override
                                            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//                                                log.debug("channelRegistered");
                                                System.out.println("channelRegistered");
                                                super.channelRegistered(ctx);
                                            }

                                            @Override
                                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

                                                System.out.println("exceptionCaught");
                                                super.exceptionCaught(ctx, cause);
                                            }

                                            @Override
                                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                                System.out.println(msg);


                                            }
                                        });

                                   */
                            }
                        }
                );
        ChannelFuture cf = serverBootstrap.bind(8080).sync();
        cf.channel().closeFuture().sync();

    }


}
