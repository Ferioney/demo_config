# demo_config application shows different behaviour of Spring Cloud 2022.0.4 and 2022.0.3

## Configuration-service responses
Application makes two requests to configuration-service (with `default` profile amd 
`preprod` profile):

- /demo-config-tester/default/demo_label
```json
{
  "name": "demo-config-tester",
  "profiles": [
    "default"
  ],
  "label": "demo_label",
  "version": null,
  "state": null,
  "propertySources": [
    {
      "name": "git@github.com:configuration/application.yml",
      "source": {
        "feign.hystrix.enabled": true
      }
    }
  ]
}
```
- /demo-config-tester/prod/demo_label (We use Spring Cloud Configuration Server 2.2.8.RELEASE. We can not migrate to the new version now.
ConfigServer handle `spring.profiles.include` from `application-prod.yml` and add `application-kafkaPreprod.properties` file to response.)
```json
{
  "name": "demo-config-tester",
  "profiles": [
    "prod"
  ],
  "label": "demo_label",
  "version": null,
  "state": null,
  "propertySources": [
    {
      "name": "git@github.com:configuration/application-kafkaPreprod.properties",
      "source": {
        "tester.proprs.prop5": "app_studioPreprod5",
        "tester.proprs.prop6": "app_studioPreprod6"
      }
    },
    {
      "name": "git@github.com:configuration/application-prod.yml",
      "source": {
        "spring.profiles.include": "kafkaPreprod",
        "tester.proprs.prop1": "app_preprod1",
        "tester.proprs.prop5": "app_preprod5",
        "tester.proprs.prop6": "app_preprod6"
      }
    },
    {
      "name": "git@github.com:configuration/application.yml",
      "source": {
        "feign.hystrix.enabled": true
      }
    }
  ]
}
```

## How to run
- run docker-compose up to start mockServer which simulate configServer
- run DemoConfigApplicationTests.contextLoads test. Application started successfully
- change spring-cloud.version to 2022.0.3 in pom.xml
- run test again. Test failed with exception:
```
Property 'spring.profiles.include' imported from location '[ConfigServerConfigDataResource@6a714237 
uris = array<String>['http://localhost:1080'], optional = false, profiles = list['prod']]' is invalid in a profile specific 
resource [origin: "spring.profiles.include" from property source "configserver:git@github.com:configuration/application-prod.yml"]
