package inflearn.hello_spring.controller;

import inflearn.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 이 어노테이션 사용하면 스프링 컨테이너에 MemberService와 연관하여 자동으로 걸어서(wired) 빈 객체 생성 -> 이게 DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}


