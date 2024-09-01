package com.example.ewe;

import static java.util.regex.Pattern.*;
import static org.zalando.logbook.json.JsonPathBodyFilters.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.Sink;
import org.zalando.logbook.json.JsonHttpLogFormatter;
import org.zalando.logbook.json.JsonPathBodyFilters;
import org.zalando.logbook.logstash.LogstashLogbackSink;

@Configuration
public class LogbookConfig {
    @Bean
    public Sink logbookLogStash() {
        HttpLogFormatter formatter = new JsonHttpLogFormatter();
        LogstashLogbackSink sink = new LogstashLogbackSink(formatter);
        return sink;
    }

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .bodyFilter(BodyFilter.merge(
                        JsonPathBodyFilters.jsonPath("$.characters[*]").replace(compile("^(\\w).+"), "$1."),
                        JsonPathBodyFilters.jsonPath("$.url").replace("****")))
                .bodyFilter(jsonPath("password").replace("****"))
                .sink(logbookLogStash())
                .build();
    }
}
