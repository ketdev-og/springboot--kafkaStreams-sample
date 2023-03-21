package com.ketdev.kafka_stream.kafka;


import com.ketdev.kafka_stream.model.OrderModel;
import org.apache.kafka.streams.kstream.KStream;


public interface StreamBinding {


    KStream<String, OrderModel> inputStream();
    KStream<String, OrderModel> takeAwayStream();
    KStream<String, OrderModel> homeDeliveryStream();
}
