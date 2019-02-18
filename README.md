# Cliente Rest API

* [Como usar](#como-usar)
* [Quais ferramentas foram usadas](#quais-ferramentas-foram-usadas)
* [Infraestrutura adicional](#infraestrutura-adicional)
* [Como executar, testar, empacotar e entregar o projeto](#como-executar-testar-empacotar-e-entregar-o-projeto)
* [Instruções para ambiente de produção](#instruções-para-ambiente-de-produção)

## Como usar 

* [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

ou


| O quê você deseja fazer | Comando curl   | Resposta |
|-------------------------|----------------|----------|
| Verificar se o serviço está ativo | curl -X GET "http://localhost:8080/manage/health" | Mensagem json em caso de sucesso, caso demore mais de 5 segundos o retorno o serviço está fora do ar |
|Criar um cliente (o id é gerado na saída do comando) | curl -X POST -H "Content-Type: application/json" -d '{"nome":"Pablo Silva", "idade" : 39 }' "http://localhost:8080/cliente" | cliente em formato json com id gerado automaticamente |
| Atualizar um cliente (é necessário informar o id) | curl -X PUT -H "Content-Type: application/json" -d '{"id" : 30, "nome":"Almerita Silva", "idade" : 79 }' "http://localhost:8080/cliente" | cliente em formato json atualizado |
| Consultar um cliente (é necessário informar o id gerado pelo POST) | curl -X GET -H "Content-Type: application/json" "http://localhost:8080/cliente/5b2ff494c5518e628ca0d381" | cliente em formato json |
| Apagar um cliente (é necessário informar o id) | curl -X DELETE -H "Content-Type: application/json" "http://localhost:8080/cliente/30" | mensagem "ok" de confirmacao de cliente excluido da base | 
| Listar todos os clientes da base | curl -X GET -H "Content-Type: application/json" "http://localhost:8080/cliente/listall" | array de todos os clientes em formato json |
 



## Quais ferramentas foram usadas

| Ferramenta | Link para download |
|------------|--------------------|
|Java Development Kit 8 64 bits para Windows 7 | (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) |
| Maven 3.3.9 para Windows | (https://maven.apache.org/download.cgi) |
| IDE Spring STS 64 bits para Windows 7 | (https://spring.io/tools/sts/all) |
| Spring Boot 2.0.3.RELEASE | (https://start.spring.io/) |
| Curl 64 bits para Windows 7 | (https://curl.haxx.se/download.html#Win64) |
| Redis 3.0.503 64 bits para Windows 7 | (https://github.com/MicrosoftArchive/redis/releases) |
| MongoDB 3.4 Community para Windows 7 | (https://www.mongodb.com/download-center?#atlas)|

> (Redis é utilizado como cache e como fila pub/sub para posterior processamento das informações do clima)  

## Infraestrutura adicional

### Porta do serviço

* A porta padrão para rodar este serviço é 8080. (http://localhost:8080/cliente)
* Para alterar basta informar a porta desejada no arquivo de propriedade src/main/resources/application.properties, conforme abaixo:
```
	server.port=8080
```

### Redis
* É necessário baixar e instalar o Redis em qualquer máquina.
* O IP e a porta da máquina em que será instalado o Redis deverá ser incluido no arquivo de propriedade src/main/resources/application.properties, conforme abaixo:

```
	spring.redis.host=127.0.0.1
	spring.redis.port=6379
```
		
### MongoDB

* É necessário baixar e instalar o MongoDB em qualquer máquina.
* O IP e a porta da máquina em que será instalado o MongoDB deverá ser incluido no arquivo de propriedade src/main/resources/application.properties, conforme abaixo:

```
	spring.data.mongodb.host=127.0.0.1
	spring.data.mongodb.port=27017
```

## Como executar, testar, empacotar e entregar o projeto

1. Baixar e instalar o Java 1.8 ou superior
2. Baixar e instalar o Maven 3.3.9 ou superior
3. Iniciar o Redis 3.0 ou superior
4. Iniciar o MongoDB 3.4 ou superior
5. Baixar os fontes
```
git clone https://github.com/p5f8/clienterest.git
cd clienterest
```
6. Verificar arquivo src/main/resources/application.properties

	> Confirmar a porta do Serviço (porta padrão é 8080)
	 
	> Confirmar IP e Porta do Redis (IP padrão 127.0.0.1 e porta 6379)
	
	> Confirmar IP e Porta do MongoDB (IP padrão 127.0.0.1 e porta 27017)

7. Gerar o pacote Java em formato Jar
```
mvn clean install
```

	> Dentro da pasta target, verificar a existência do arquivo com nome: platcorp-0.0.1.jar

8. Para executar

	> Entrar na pasta target criada no passo anterior e executar o comando abaixo
``` 
	java -jar platcorp-0.0.1.jar
``` 
	

## Instruções para ambiente de produção

- [ ]  JDK 8 ou superior deve estar instalado na máquina que a aplicacao platcorp irá rodar
- [ ]  Redis deve estar rodando
- [ ]  MongoDB deve estar rodando
- [ ]  Liberar portas no firewall, conforme [Infraestrutura adicional](#infraestrutura-adicional)
- [ ]  Criar uma pasta com nome "**platcorp**" 
- [ ]  Copiar o arquivo gerado na seção [Como executar, testar, empacotar e entregar o projeto](#como-executar-testar-empacotar-e-entregar-o-projeto)
- [ ]  Executar o comando: 
```
java -jar platcorp-0.0.1.jar
```
- [ ]  Para verificar se o serviço está rodando execute:
```
curl -X GET "http://localhost:8080/manage/health"
```
- [ ]  Retornou '{"status":"UP"}' no passo anterior? Tudo Ok e funcionando, pode utilizar todas as urls descritas em na seção [Como usar](#como-usar). 
- [ ]  Não funcionou? mande um e-mail com a mensagem de erro para p5f8@hotmail.com. Obrigado.

## Instruções para monitoramento

- [ ] Executar o comando abaixo para verificar se o serviço está no ar e deve retornar '{"status":"UP"}':
```
curl -X GET "http://localhost:8080/manage/health"
```
- [ ] Logs de INFO são gerados no stdout padrão (console)
- [ ] Logs de ERROR são gerados no stderr padrão (console)
- [ ]  Não funcionou? mande um e-mail com a mensagem de erro para p5f8@hotmail.com. Obrigado.

## TODO

- Incluir Docker no projeto
- Aumentar cobertura de testes unitários
- Incluir teste integrado dos componentes services