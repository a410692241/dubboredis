package com.wei.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.wei.provider.dao.MiaoshaDao;
import com.wei.provider.dao.OrderDao;
import com.wei.service.bo.Miaosha;
import com.wei.service.bo.MiaoshaExample;
import com.wei.service.bo.Order;
import com.wei.service.service.MiaoshaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MiaoshaDao miaoshaDao;
    @Autowired
    private OrderDao orderDao;

    private static RedisConnection connection;
    private static RedisConnectionFactory connectionFactory;

    public void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
    }

    @Override
    public long countByExample(MiaoshaExample example) {
        return miaoshaDao.countByExample(example);
    }

    @Override
    public int deleteByExample(MiaoshaExample example) {
        return miaoshaDao.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return miaoshaDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Miaosha record) {
        return miaoshaDao.insert(record);
    }

    @Override
    public int insertSelective(Miaosha record) {
        return miaoshaDao.insertSelective(record);
    }

    @Override
    public List<Miaosha> selectByExample(MiaoshaExample example) {
        return miaoshaDao.selectByExample(example);
    }

    @Override
    public Miaosha selectByPrimaryKey(Integer id) {
        return miaoshaDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Miaosha record, MiaoshaExample example) {
        return miaoshaDao.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Miaosha record, MiaoshaExample example) {
        return miaoshaDao.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Miaosha record) {
        return miaoshaDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Miaosha record) {
        return miaoshaDao.updateByPrimaryKey(record);
    }

    @Override
    public void redisMiaosha() {
        connectionFactory = redisTemplate.getConnectionFactory();
        connection = connectionFactory.getConnection();
        String pathname = this.getClass().getResource("/").getPath() + "/redis.lua";
        String decode = URLDecoder.decode(pathname);
        File file = new File(decode);


        ByteOutputStream byteOutputStream = null;
        byte[] byteArray = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byteOutputStream = new ByteOutputStream();
            int len;
            byte[] bytes = new byte[1024 * 10];
            while ((len = fileInputStream.read(bytes)) != -1) {
                byteOutputStream.write(bytes, 0, len);
            }
            byteArray = byteOutputStream.toByteArray();
            byteOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String luaCache = connection.scriptLoad(byteArray);
        //miaosha对象
        Order order = new Order();
        order.setUserid(1);
        String miaoshaObject = new Gson().toJson(order);
        long  result = connection.evalSha(luaCache, ReturnType.VALUE, 1, (1 + "").getBytes(), (1 + "").getBytes(), miaoshaObject.getBytes());
        if ("2".equals(result)) {
            //秒杀结束,同步到数据库
//            this.Async();
        }


    }

    public void Async() {
        String count = (String) redisTemplate.opsForHash().get("order1","count");
        Order record = new Order();
        record.setCount(Integer.parseInt(count));
        int i = orderDao.updateByPrimaryKey(record);
        //订单保存数据库
        List<Order> orders = (List<Order>) redisTemplate.opsForList().leftPop("miaosha1");
        Miaosha miaosha = new Miaosha();
        for (Order order : orders) {
            Miaosha miaosha1 = new Miaosha();
            miaosha1.setCount(1);
            int insert = miaoshaDao.insert(miaosha1);
        }
        if (i > 0) {
            System.out.println("------------------同步完成------------------");
        }
    }
}
