import io.netty.buffer.ByteBuf;
import org.junit.Test;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.Packet;
import the.hb.protocol.PacketCodeC;
import the.hb.serialize.Serializer;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 22:11
 */

public class SerializerTest {

    private Serializer serializer = Serializer.DEFAULT;
    private Packet packet = new LoginRequestPacket();
    private PacketCodeC packetCodeC = new PacketCodeC();

    @Test
    public void serializeTest(){
        byte[] bytes = serializer.serialize(packet);
        LoginRequestPacket requestPacket = serializer.deserialize(LoginRequestPacket.class, bytes);
        System.out.println(requestPacket);
    }
    @Test
    public void packetCodeCtest(){
        ByteBuf encode = packetCodeC.encode(packet);
        Packet decode = packetCodeC.decode(encode);
        System.out.println(decode);
    }
}
