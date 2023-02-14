package microchat;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableCreateCacheAnnotation  //开启缓存
@EnableMethodCache(basePackages = "microchat")  //激活@Cached
public class MicroChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroChatApplication.class, args);
    }

}
