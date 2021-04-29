package com.endava.hr.event.listener;


import com.endava.hr.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Slf4j
public class AppEventsListener {

    @EventListener(Event.class)
    public void listen(Event event) {
        log.info("aha");

        Stream.of("aa", "bb", "etc").peek(System.out::println);
    }

}
