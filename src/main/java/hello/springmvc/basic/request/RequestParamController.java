package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age{}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody   //response body에 문자를 그대로 응답한다. 이게 없으면ok라는 뷰이름을 찾게 된다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username={}, age{}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody   //response body에 문자를 그대로 응답한다. 이게 없으면ok라는 뷰이름을 찾게 된다.
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody   //response body에 문자를 그대로 응답한다. 이게 없으면ok라는 뷰이름을 찾게 된다.
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ //이름 같으면 RequestParam을 생략하지만 권장하진 않음(비 직관적)
        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody   //response body에 문자를 그대로 응답한다. 이게 없으면ok라는 뷰이름을 찾게 된다.
    @RequestMapping("/request-param-required")
    public String requestParamV5(
            @RequestParam(required = true) String username, //url에 해당 변수가 꼭 들어와야한다 없으면 에러
            @RequestParam(required = false) Integer age){ //int일 경우 에러가 난다 (자료형에는 무조건 값이 들어가야한다, but객체는 null을 넣을 수 있다)
        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody   //response body에 문자를 그대로 응답한다. 이게 없으면ok라는 뷰이름을 찾게 된다.
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //url에 해당 변수가 꼭 들어와야한다 없으면 에러
            @RequestParam(required = false, defaultValue = "-1") int age){ //default일 경우 값이 없어도 에러가 나지 않는다
        log.info("username={}, age{}", username, age);
        return "ok";
    }

    @ResponseBody   //response body에 문자를 그대로 응답한다. 이게 없으면ok라는 뷰이름을 찾게 된다.
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){ //default일 경우 값이 없어도 에러가 나지 않는다
        //String username = (String)paramMap.get("username");   //에러발생... 왜지...?
        //int age = (int) paramMap.get("age");

        log.info("username={}, age{}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        log.info("username={}, age{}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}",helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData){   //객체의 변수이름과 같은값을 받는다

        log.info("username={}, age{}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}",helloData);
        return "ok";
    }
}
