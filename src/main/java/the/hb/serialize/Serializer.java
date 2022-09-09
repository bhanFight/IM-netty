package the.hb.serialize;

import the.hb.serialize.impl.JSONSerializer;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 20:43
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializeAlgorithm();

    byte[] serialize(Object packet);

    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
