package com.example.demo.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by vano on 2/19/16.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonConverter());
		//for xml response
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	@Bean
	public MappingJackson2HttpMessageConverter jacksonConverter() {
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setObjectMapper(objectMapper());
		return jacksonConverter;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		mapper.setTimeZone(TimeZone.getDefault());
//		mapper.registerModule(new Hibernate4Module());

		//		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		SimpleModule simpleModule = new SimpleModule();
//		simpleModule.addDeserializer(Date.class, new CustomDateDeserializer());
//		simpleModule.addSerializer(Date.class, new CustomDateSerializer());
//		simpleModule.addSerializer(Page.class, new CustomPageDeserializer());
		mapper.registerModule(simpleModule);
		return mapper;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(Collections.singletonList(jacksonConverter()));
	}
}
