# OAuth2 with Spring Security 5 and JWT

## Testing OAuth2 with Postman

#####  Run and test Oauth project
For Spring Boot project to run app you should execute Maven plugin including ***run*** goal  ```mvn spring-boot:run```.  Plugin quickly compile and run OAuth application.  More information [here](https://docs.spring.io/spring-boot/docs/current/maven-plugin/run-mojo.html)

When OAuth projet is ready to use open Postman to test endpoints. Thanks to this tool you were be able to send request and recive response from REST API. If you don't have Postman just install it from [here](https://www.getpostman.com/downloads/).

##### Step by step to test OAuth 
Open Postman tool and create ***POST*** request method on concrete endpoint ***oauth/token*** to get authorization token. Let's type  [localhost:8080/oauth/token](localhost:8080/oauth/token) .

Then open ***Header*** tab and pass basic authentication with ***Authorization*** key. To generate it you can use  [bsic authentication generator](https://www.blitter.se/utils/basic-authentication-header-generator/) . Paste client credential from ***data.sql***  :
 ```
 client_id: github_client
 cliente_secret: github_secret
```
Configuration for this step should be look like on the picture below.

 ![It should be look like this.](https://user-images.githubusercontent.com/14622491/58766142-7f9d5600-857b-11e9-99c0-a845b4f9661d.png)

Finally, in ***Params*** tab fullfill needed data for authentication on server. Looks at params below:
 ```
client_id: github_client
client_secret: github_secret
grant_type: password
username: witosh
password: test 
```

![Params of POST request](https://user-images.githubusercontent.com/14622491/58766016-f20d3680-8579-11e9-8b38-27605b0a2ba9.png)

 If everything is correct you can try to authentice yourself and get authorization token from OAuth server. Execute ***POST*** request method just use ***Send*** button:

 ![Authorization success](https://user-images.githubusercontent.com/14622491/58766151-9643ad00-857b-11e9-9986-efb560cdcbc4.png)

Now, if everything goes successful you will be authenticate and you get all needed data to authorize on resources server.