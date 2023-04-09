package com.learnreactivespring.learnreactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {


    @Test
    public void fluxTest(){
        Flux<String> stringFlux = Flux.just("Spring", "SpringBoot", "Reactive Spring")
//                .concatWith(Flux.error(new RuntimeException("Exception flux")))
                .concatWith(Flux.just("After error"))
                .log();

        stringFlux.subscribe(System.out::println, e ->{
            System.err.println(e);
        }, () ->{
            System.out.println("Completed");
        });
    }


    //Test flux
    @Test
    public void fluxTestElementWithoutError(){
        Flux<String> stringFlux = Flux.just("Spring", "SpringBoot", "Reactive Spring").log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("SpringBoot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    //Test flux
    @Test
    public void fluxTestElementWithError(){
        Flux<String> stringFlux = Flux.just("Spring", "SpringBoot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception flux")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("SpringBoot")
                .expectNext("Reactive Spring")
                .expectErrorMessage("Exception flux")
                .verify();
//                .verifyError(RuntimeException.class);
//                .verifyComplete();
    }


    //Test flux
    @Test
    public void fluxTestElementCountWithError(){
        Flux<String> stringFlux = Flux.just("Spring", "SpringBoot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception flux")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Exception flux")
                .verify();
    }


    //Test flux
    @Test
    public void fluxTestElementWithError1(){
        Flux<String> stringFlux = Flux.just("Spring", "SpringBoot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception flux")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "SpringBoot", "Reactive Spring")
                .expectErrorMessage("Exception flux")
                .verify();
    }


}
