package com.epam.singleton;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class LazySingletonTest {
    private static LazySingleton expectedSingleton;

    @Test
    public void validateThatAllSingletonsAreTheOneObject() throws InterruptedException {
        //GIVEN
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        //WHEN
        for (int i = 0; i < 1000; i++) {
            fixedThreadPool.submit(LazySingletonTest::creatingAndValidatingSingleton);
        }
        fixedThreadPool.awaitTermination(3, TimeUnit.SECONDS);
    }

    private static void creatingAndValidatingSingleton() {
        LazySingleton singleton = LazySingleton.getInstance();
        checkLazySingleton(singleton);
    }

    private static void checkLazySingleton(LazySingleton singletonUnderTest) {
        if (isNull(expectedSingleton)) {
            expectedSingleton = singletonUnderTest;
        } else {
            assertThat(singletonUnderTest, is(equalTo(expectedSingleton)));
        }
    }
}