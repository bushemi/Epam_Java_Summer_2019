package com.epam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Captor
    private ArgumentCaptor<String> captor;

    @Mock
    Consumer consumer;

    @Mock
    Producer producer;

    @InjectMocks
    Processor processor;

    @Test
    public void process() {
        //GIVEN
        when(producer.produce()).thenReturn("test");

        //WHEN
        processor.process();

        //THEN
        verify(producer).produce();
        verify(consumer).consume(captor.capture());
        assertThat(captor.getValue(), is(equalTo("test")));
    }

    @Test
    public void processShouldThrowException() {
        //GIVEN
        expectedException.expect(IllegalStateException.class);
        when(producer.produce()).thenReturn(null);

        //WHEN
        processor.process();
    }
}