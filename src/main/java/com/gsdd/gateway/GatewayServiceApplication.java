package com.gsdd.gateway;

import java.util.ArrayList;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

  private static final String EMPTY = "";
  private static final String SVC_PREFIX = "-svc";
  // To be removed after some services get renamed
  private static final String APP_PREFIX = "-app";

  @Autowired
  RouteDefinitionLocator locator;

  public static void main(String[] args) {
    SpringApplication.run(GatewayServiceApplication.class, args);
  }

  @Bean
  List<GroupedOpenApi> apis() {
    List<GroupedOpenApi> groups = new ArrayList<>();
    List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
    assert definitions != null;
    definitions.stream()
        .filter(routeDefinition -> routeDefinition.getId().matches(".*" + SVC_PREFIX))
        .filter(routeDefinition -> routeDefinition.getId().matches(".*" + APP_PREFIX))
        .forEach(routeDefinition -> {
          String name = routeDefinition.getId().replaceAll(SVC_PREFIX, EMPTY);
          name = routeDefinition.getId().replaceAll(APP_PREFIX, EMPTY);
          groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
        });
    return groups;
  }
}
