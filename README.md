Esta é uma aplicação que demonstra o uso de logs em uma aplicação spring boot 3 containerizada junto com postgresql containerizado.

Além disso, demonstra o uso do prometheus, grafana e o grafana loki.

Para os alertas foi utilizado o próprio grafana em vez do alertmanager.

## Para testar os alertas

1. subir os containers:

```bash
docker compose up -d
```

2. logar no grafana, mudar a senha, cadastrar os datasources 'prometheus' e 'grafana-loki' com suas respectivas URLs dos containers.

3. importar o dashboard do arquivo api-cursos-dashboard.json no grafana;

4. corrigir o datasource referenciado nas variáveis do dashboard após importação;

5. cadastrar o alerta no grafana. As queries LogQL utilizadas foram as abaixo:

> sum(count_over_time({app="api-cursos",class="SqlExceptionHelper",level="ERROR",method="logExceptions"}[1m])) >= 1

> sum(count_over_time({app="api-cursos",class="DataSourceHealthIndicator",level="WARN"}[1m])) >= 1

6. Slack.

6.1 criar conta no slack;

6.2 criar um canal no slack para receber o alert;

6.3 criar um app;

6.4 adicionar um 'incoming webhooks';

6.5 selecionar o canal criado;

6.6 copiar o webhook url para o grafana em Alerting -> Contact Points

7. adicionar notification policies para o slack.

8. parar o container do banco de dados e verificar os alertas, o dashboard e o slack;

```bash
docker container stop database-api-cursos
```

Sobre os containers:
- database-api-cursos: banco de dados postgresql da aplicação;
- api-cursos: aplicação spring boot 3 de exemplo;
- prometheus-api-cursos: prometheus;
- grafana-api-cursos: grafana;
- loki-api-cursos: grafana-loki;
- client-api-cursos: script bash que dispara requests simulando requests com erros, requests normais e etc para geração de métricas e alertas nos componentes

Observação 1: o objetivo deste README não é descrever o exato *howto* para fazer o teste, são instruções em alto nível. 
Os passos relacionados ao Slack e Grafana mudam conforme as versões mudam, sendo necessárias devidas adaptações. 
Não foram abordados **TODOS** os detalhes desses passos.

Observação 2: fiz pequenas alterações com relação a versão deste projeto no curso.
Atualizei a aplicação para o Spring Boot 3.1.x (no curso era 2.6.x) e defini versões fixas no docker-compose.yml para melhor reprodutibilidade do projeto.

