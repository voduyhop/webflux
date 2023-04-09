package com.learnreactivespring.learnreactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    //test mono
    @Test
    public void monoTest(){
        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();

    }

    //test mono
    @Test
    public void monoTestWithError(){
//        Mono<String> stringMono = Mono.just("Spring")
//                .concatWith(Mono.error(new RuntimeException("Error")));


        StepVerifier.create(Mono.error(new RuntimeException("Error")).log())
                .expectErrorMessage("Error")
                .verify();
//                .expectError(RuntimeException.class).verify();


    }
}
