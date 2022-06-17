package com.example.demo.config;

import com.example.demo.domain.Course;
import com.example.demo.domain.CourseCache;
import com.example.demo.persistence.FauxDataSvc;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Configuration
public class DataConfig {
    Logger log = LoggerFactory.getLogger(DataConfig.class);

    @Bean("cacheJson")
    public CourseCache initializeFauxRepository(FauxDataSvc dataSvc,
                                                ApplicationContext ctx, @Value("${data.file.courses.cache}") String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Course> crsList = mapper.readValue(
                Files.newInputStream(ctx.getResource(fileName).getFile().toPath()),
                new TypeReference<List<Course>>() {});
        dataSvc.saveAll(crsList);
        return CourseCache.builder().parsedCourses(crsList).build();
    }

    @Bean("loadJson")
    public CommandLineRunner initialFauxDataSvc(FauxDataSvc dataSvc,
                                                ApplicationContext appCtx, @Value("${data.file.courses}") String fileName) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            List<Course> crsList = null;
            try {
                crsList = mapper.readValue(
                        Files.newInputStream(appCtx.getResource(fileName).getFile().toPath()),
                        new TypeReference<List<Course>>(){});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dataSvc.saveAll(crsList);
        };
    }
}
