package the.hb.serialize.impl;

import com.alibaba.fastjson.JSON;
import the.hb.serialize.SerializeAlgorithm;
import the.hb.serialize.Serializer;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 21:14
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializeAlgorithm() {
        return SerializeAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object packet) {
        return JSON.toJSONBytes(packet);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
