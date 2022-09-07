package the.hb.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import the.hb.protocol.command.Command;
import the.hb.protocol.request.CreateGroupRequestPacket;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.request.LogoutRequestPacket;
import the.hb.protocol.request.MessageRequestPacket;
import the.hb.protocol.response.CreateGroupResponsePacket;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.protocol.response.LogoutResponsePacket;
import the.hb.protocol.response.MessageResponsePacket;
import the.hb.serialize.SerializeAlgorithm;
import the.hb.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 21:17
 */
public class PacketCodeC {

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public static final int MAGIC_NUMBER = 0x12345678;
    private final Map<Byte, Serializer> serializerMap;
    private final Map<Byte, Class<? extends Packet>> requestTypeMap;

    private PacketCodeC(){
        serializerMap = new HashMap<>();
        serializerMap.put(SerializeAlgorithm.JSON, Serializer.DEFAULT);

        requestTypeMap = new HashMap<>();
        this.packetTypeMapPut(Command.LOGIN_REQUEST, LoginRequestPacket.class)
            .packetTypeMapPut(Command.LOGIN_RESPONSE, LoginResponsePacket.class)
            .packetTypeMapPut(Command.MESSAGE_REQUEST, MessageRequestPacket.class)
            .packetTypeMapPut(Command.MESSAGE_RESPONSE, MessageResponsePacket.class)
            .packetTypeMapPut(Command.LOGOUT_REQUEST, LogoutRequestPacket.class)
            .packetTypeMapPut(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class)
            .packetTypeMapPut(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class)
            .packetTypeMapPut(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
    }

    public ByteBuf encode(Packet packet){
        return encode(ByteBufAllocator.DEFAULT, packet);
    }

    public ByteBuf encode(ByteBufAllocator allocator, Packet packet){
        ByteBuf byteBuf = allocator.buffer();

        encode(packet, byteBuf);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte serializeAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Serializer serializer = getSerializer(serializeAlgorithm);
        Class<? extends Packet> requestType = getRequestType(command);
        return serializer.deserialize(requestType, bytes);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return requestTypeMap.get(command);
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    public void encode(Packet packet, ByteBuf byteBuf) {

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }
    private PacketCodeC packetTypeMapPut(Byte command, Class<? extends Packet> clazz){
        this.requestTypeMap.put(command, clazz);
        return this;
    }
}
