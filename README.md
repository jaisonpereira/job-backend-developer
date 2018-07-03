# Spring Cloud - Login Storm System

### Análise do cenário atual

>  1- Imagine que hoje tenhamos um sistema de login e perfis de usuários. O sistema conta com mais de 10 milhões de usuários, sendo que temos um acesso concorrente de cerca de 5 mil usuários. Hoje a tela inicial do sistema se encontra muito lenta. Nessa tela é feita uma consulta no banco de dados para pegar as informações do usuário e exibi-las de forma personalizada. Quando há um pico de logins simultâneos, o carregamento desta tela fica demasiadamente lento. Na sua visão, como poderíamos iniciar a busca pelo problema, e que tipo de melhoria poderia ser feita?
>  
R: Iniciaremos a busca pelos problemas através de métricas de uso da CPU/Memory utilizando Jmeter,  constatado uso excessivo partiríamos para a arquitetura de comunicação com o Banco ,ferramentas de métricas de queries como Hibernate Statistics (caso esteja utilizando JPA) pode nos auxiliar a determinar pontos de melhoria no sistema.
Por se tratar de uma aplicação que recebe muitos acessos concorrentes em determinados horários,  adotar uma arquitetura escalavél é solução mais viável ,após o desenvolvimento da estrutura base de login escalável podemos hospedar em um serviço que possua um auto-scalling automático efetuando um decrease nos momentos de baixo uso viabilizando o custo 
Pontos com uma enorme frequência de acesso podem ser elevados a um cache de segundo nível .


> 2 - Com base no problema anterior, gostaríamos que você codificasse um novo sistema de login para muitos usuários simultâneos e carregamento da tela inicial. Lembre-se que é um sistema web então teremos conteúdo estático e dinâmico. Leve em consideração também que na empresa existe um outro sistema que também requisitará os dados dos usuários, portanto, este sistema deve expor as informações para este outro sistema de alguma maneira

A Solução viabilizada para o login com uma carga alta de uso foi desenvolver um sistema que tenha possibilidade de se escalar  conforme a necessidade de uso .


#### A Stack adotada foi Spring Cloud + Netflix OSS:

* #### Eureka Server
A palavra eureka foi supostamente  pronunciada pelo  cientista
grego Arquimedes (287 a.C. – 212 a.C.), quando descobriu como
resolver um complexo dilema. No	nosso contexto, ela procura todos os serviços do Spring Cloud e permite que	se registrem	nela
para tornarem-se disponíveis para uso.
* #### Zuul Gateway
No filme Caça Fantasmas, de 1984, Zuul era o	 porteiro do
portal de Gozer, um	 deus	 antigo que queria trazer todos os
demônios de	 seu universo	 para a cidade de New York. No nosso
contexto, ele	 que receberá todas	 as requisições e enviará aos
servidores disponíveis, consultado o eureka server

* ####  Login-intelipost-microservice
Serviço desenvolvido para que possa receber cada request de forma distribuída , serviço stateless facilita escalonamento do mesmo por não guardar estado.
Stack
* Spring boot
* Docker
* Apache Maven
* Angular JS
* JWT
* Spring Data JPA - Hibernate
* Spring Data + Redis Cache
* #### Evolução de estratégia de Consulta aos  usuários Postgres 

A princípio a solução adotada foi realizar consulta utilizando Hibernate Stateless Session (Uma sessão sem estado que não implementa um cache de primeiro nível  do hibernate nem interage com nenhum cache de segundo nível  do hibernate) 
porém por se tratar de um sistema com uma base de usuários na casa dos milhares
a solução ideal constatada por um benchmark realizado com hibernate statistics 
foi utilizar jdbc , independente da estrategia que se utilize com hibernate o número de acesso simultâneos degrada a performance exponencialmente.
Com o propósito de melhorar a performance  dos acessos  ao postgres foi implementado a estratégia de cacheamento utilizando spring-cache + spring-data-redis.

* Estratégia de Autenticação
Para autenticação e autorização foi utilizado +Spring-Security-JWT  utilizando a especificação RFC 7519 (https://jwt.io/) - Facilitando a escalabilidade e dispensando  a necessidade de se trocar cookies entre aplic  e browser ou utilizar estratégias como  sessão distribuída ,Session Migration , Sticky Session (Aws), 
Adotamos uma abordagem que nos beneficia com uma facilidade de escalonamento  e compatível com a maioria das aplicações(Principalmente com dispositivos móveis) existentes no mercado.em um cenário futuro o secret-token e o expiration-time podem ser compartilhado utilizando-se o config server dessa forma podemos alterar a estratégia de autenticação sem afetar a aplicação em runtime
De modo geral poderemos não se preocupar com uma proteção  CSRF (Cross-Site Request Forgery
), pois estaremos  trabalhando com um  sistema  “stateless”.

* Para a disponibilizar os usuários logados  persistimos o histórico dos tokens utilizados,email de login ,data de autenticação e tempo de expiração do token  usando spring-data-redis ,dessa forma os micro-serviços podem consultar o histórico de  usuários logados na aplicação usando o critério de data de expiração disponível no momento da geração do token além de disponibilizar uma forma de consultar os usuários logados(Usando o critério de timeout do token) de quebra obteremos dados que podem ser utilizados para formalizarmos dados  estatísticos  de uso como métricas de frequência e etc.
Caso o load-balancer caia e o usuário seja redirecionado para um server geograficamente distinto que ainda não recebeu a distribuição dos dados entre os cluster  e o JWT do usuário ainda esteja válido não há queda de sessão. 


### Build and Deploy 
Premissas Git , Linux,Java 8,docker/docker-compose,JAVA_HOME

1.  clonar este repositório
2.  dar permissão ao script de deploy caso necessário 
sudo chmod +x deploy-and-run.sh
3. executar script 
./deploy-and-run.sh

4. acessar logins http://localhost:8080/
consultar serviços disponíveis http://localhost:8761/eureka

> * OBS:
> - Aguardar término “register-server” dos serviços executados.
> - docker ter permissão de execução sem o sudo
> - clonar em pasta que nao necessite de permissões adicionais

Users disponíveis

> user: jaison@intelipost
> pass: recrutado

> user: admin@admin
> senha: admin