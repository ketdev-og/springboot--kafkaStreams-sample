package com.ketdev.kafka_stream.processor;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EventStreamProcessor {
    @Autowired
    private StreamsBuilder streamsBuilder;

    @PostConstruct
    public void streamTopology() {
        KStream<String, String> kStream = streamsBuilder
                .stream("spring.boot.kafka.stream.input",
                        Consumed.with(
                                Serdes.String(),
                                Serdes.String()));
        kStream.filter((key, value) ->
                        value.startsWith("Message_"))
                .mapValues((key, value) ->
                        value.toUpperCase())
                .peek((key, value) ->
                        System.out.println("key:   " + key + "     value:    " + value))
                .to("spring.boot.kafka.stream.output",
                        Produced.with(
                                Serdes.String(),
                                Serdes.String()));
    }
}
