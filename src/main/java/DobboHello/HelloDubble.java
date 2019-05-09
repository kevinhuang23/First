package DobboHello;

import com.alibaba.dubbo.config.annotation.Reference;


public class HelloDubble {
	@Reference
    private DemoService demoService;
    public String sayHello() {
        String s = demoService.sayHello("Dubbo学习之路");
        return s;
    }

}
