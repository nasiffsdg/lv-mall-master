import com.aliyun.oss.OSSClient;
import com.lv.mall.ProductApplication;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = ProductApplication.class)
public class ProductCategoryServiceImplTest {

    @Resource
    OSSClient ossClient;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedissonClient redissonClient;

    @Test
    public void OssTest() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("D:\\workspace\\project\\lv-mall-master\\static\\1ca4eddb880a11ebb6edd017c2d2eca2.jpg");

        ossClient.putObject("lv-mall123", "lv.jpg", inputStream);
        ossClient.shutdown();
    }

    @Test
    public void RedisTest(){

        stringRedisTemplate.opsForValue().set(UUID.randomUUID().toString(), "word");
    }

    @Test
    public void getLock(){
        stringRedisTemplate.opsForValue().setIfAbsent("LOCK", "0", 3, TimeUnit.MINUTES);
    }

    @Test
    public void redissonTest(){
        RLock lock = redissonClient.getLock("LOCK");
        lock.lock();
        try{

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        System.out.println(redissonClient);




    }
}
