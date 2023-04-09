package com.learnreactivespring.learnreactivespring.handler;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
//@WebFluxTest //because cannt scan @component so webfuxtest can not scan complete to test => cannt use
@SpringBootTest
@AutoConfigureWebTestClient

public class SampleHandlerFunctionTest {


    @Autowired
    WebTestClient webTestClient;


    @Test
    public void flux_approach1(){
        Flux<Integer> responseBody = webTestClient.get().uri("/functional/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(responseBody.log())
                .expectNext(1,2,3,4)
                .verifyComplete();
    }


    @Test
    public void mono_approach1(){
       webTestClient.get().uri("/functional/mono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(e->{
                    assertEquals(1,e.getResponseBody());
                });

    }

}
