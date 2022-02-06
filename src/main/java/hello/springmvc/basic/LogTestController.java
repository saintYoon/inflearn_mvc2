package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j  //lombok에서 사용하는 로그 라이브러(로그객체를 자동으로 넣어주기 때문에 주석처리 가능)
@RestController //문자를 반환하면 그대로 반환시켜줌 REST API나 간단한 테스트용
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    private String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        log.trace("trace my log= "+name);  //+연산으로 인해 성능이 낮아진다. 이런식으로 사용하는것은 지양한다.

        log.trace("trace log={}",name); //위의 trace와 비교했을 때 연산이 이루어지지 않는다.
        log.debug("debug log={}",name); //디버그할때(개발서버) 보는거
        log.info("info log={}",name);  //중요한정보
        log.warn("warn log={}",name);  //경고
        log.error("error log = {}",name);   //에러

        return "ok";
    }
}
