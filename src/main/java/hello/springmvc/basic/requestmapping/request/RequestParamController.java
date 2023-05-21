package hello.springmvc.basic.requestmapping.request;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
        @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
        @RequestParam int age) {

        log.error("username={}, age={}", username, age);
        return "ok";
    }

    // String, int, Integer 등의 단순 타입이면 @RequestParam도 생략가능 하지만 권장하진 않음
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,
        int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(required = false) String username, // 만약 true라고 입력되면은 param이 입력되지 않았을 경우 bad request를 띄워
        @RequestParam(required = false) int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
        @RequestParam(required = false, defaultValue = "guest") String username,
        @RequestParam(required = false, defaultValue = "-1") int age) {

        //빈 문자열이 들어올 경우 해당 값은 default 값을 넣어준다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap Map(key=value) MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds,
     * value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
            paramMap.get("age"));
        return "ok";
    }

}
