import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = Math.class)
public class PWDtaEDIT {

    @Test
    public void enCode(){

        String encode = new BCryptPasswordEncoder().encode("lxq061611");
        System.out.println(new BCryptPasswordEncoder().matches("lxq061611",encode));
        System.out.println(encode);
    }
}