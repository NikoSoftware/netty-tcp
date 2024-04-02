package cn.xiaomotou.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @desc：
 * @name：niko
 * @Date：2024/4/1 12:40
 */
public class SocketClient {
    @SneakyThrows
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap().channel(NioSocketChannel.class)
                .group(new NioEventLoopGroup())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline()
                              //  .addLast(new StringEncoder())
                                .addLast(new ChannelInboundHandlerAdapter(){

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {


                                for (int i = 0; i < 10; i++) {
                                    ByteBuf buffer = ctx.alloc().buffer(16);
                                    buffer.writeBytes(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16});
                                   ctx.writeAndFlush(buffer);
                                }

                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                super.channelRead(ctx, msg);
                            }
                        });


                    }
                });

        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8080))
                .sync();

        channelFuture.channel().closeFuture().sync();
    }
}
