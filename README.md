# MELI

API de varredura de sequências em matriz para verificação de DNA Símio ou Humano. 



### Documentação de referência

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)
* [MySQL Documentation](https://dev.mysql.com/doc/)
* [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)

### Pré requisitos e configurações

Para que a API do MELI possa ser iniciada será necessária a instalação de algumas dependências: 

 - a máquina virtual do [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html), o [Maven](https://maven.apache.org/download.cgi) e o [Mysql](https://www.mysql.com/downloads/) 
 
Obs.: O Maven não possui instalador e deverá ser configurado nas [variáveis de ambiente](https://www.baeldung.com/install-maven-on-windows-linux-mac) do SO que estiver utilizando.

A conexão com o banco de dados também deverá ser feita configurando o arquivo <b>"/meli/src/main/resources/application.properties"</b>

Após os pré-requisitos instalados, navegar até o diretório do projeto e executar o comando abaixo:

	mvn clean install
	


### End Points da API


[http://ec2-18-218-104-200.us-east-2.compute.amazonaws.com/simian](http://ec2-18-218-104-200.us-east-2.compute.amazonaws.com/simian) 

Faz a pesquisa da sequencia de DNA em uma matriz quadrada (NxN). Este end point deve ser acessado através do verbo <b>POST</b> e a matriz deverá ser enviada no corpo da requisição como no exemplo abaixo:

	{ "dna": [	"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG" ] }

 

[http://ec2-18-218-104-200.us-east-2.compute.amazonaws.com/stats](http://ec2-18-218-104-200.us-east-2.compute.amazonaws.com/stats)

Retorna a quantidade de símios, de humanos e a proporção entre os dois de acordo com o histórico de pesquisas. O acesso a este End point deve ser feito através do verbo <b>GET</b>





