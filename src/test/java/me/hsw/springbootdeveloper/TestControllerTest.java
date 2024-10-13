package me.hsw.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        //given
        final String url = "/test";
        Member saveMember = memberRepository.save(new Member(1L, "홍길동"));
        System.out.println("Test saveMember: " + saveMember);
        Member saveMember2 = memberRepository.save(new Member(2L, "홍길은"));
        System.out.println("Test saveMember2: " + saveMember2);
        Member saveMember3 = memberRepository.save(new Member(3L, "홍길천"));
        System.out.println("Test saveMember3: " + saveMember3);

        //when
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(saveMember.getId()))
                .andExpect(jsonPath("$[0].name").value(saveMember.getName()))
                .andExpect(jsonPath("$[1].id").value(saveMember2.getId()))
                .andExpect(jsonPath("$[1].name").value(saveMember2.getName()))
                .andExpect(jsonPath("$[2].id").value(saveMember3.getId()))
                .andExpect(jsonPath("$[2].name").value(saveMember3.getName()))
        ;
    }
}