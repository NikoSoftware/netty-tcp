package cn.xiaomotou.enbedden;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.ByteBuffer;

/**
 * @desc：
 * @name：niko
 * @Date：2024/4/1 15:12
 */
public class TestEnbeDecode {

    public static void main(String[] args) {
        EmbeddedChannel embeddedChannel =  new EmbeddedChannel(new LengthFieldBasedFrameDecoder(1024,0,4,0,4)
        ,new LoggingHandler(LogLevel.DEBUG));

        //4个字节长度，后面是内容

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();

        send(buffer,"hello world");

        embeddedChannel.writeAndFlush(buffer);
    }


    public static void send(ByteBuf buffer,String content){
        byte[] bytes = content.getBytes();

        int length = bytes.length;

        buffer.writeInt(length);
        buffer.writeBytes(bytes);
    }






}
