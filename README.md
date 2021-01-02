**API de cadastro de conta**

Vamos desenvolver uma API REST que precisa suportar o processo de abertura de nova conta no banco. O primeiro passo desse fluxo é cadastrar os dados pessoais de uma pessoa. Precisamos de apenas algumas informações obrigatórias:

- Nome
- E-mail
- CPF
- Data de nascimento

Caso os dados estejam corretos, é necessário gravar essas informações no banco de dados relacional e retornar o status adequado para a aplicação cliente, que pode ser uma página web ou um aplicativo mobile.

Esta API deverá devolver a resposta adequada para o caso de falha de validação.

Para este desenvolvimento usaremos a IDE Spring Tool Suite for Eclipse, pois trata-se de um ambiente de desenvolvimento personalizado para aplicações em Spring, além de fornecer um ambiente pronto para usar.

**Configuração**

Primeiramente precisamos configurar nosso projeto Maven (Será nosso gerenciador de dependências), Linguagem Java na versão 11, grupo br.com.banco, pacote br.com.banco.conta e nome do projeto banco-api. Veja imagem abaixo:

![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_515cf5edffd3ef3c.jpg)  
 ![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_3fd1e5b5ffbc39dc.jpg)  
 
 

Na próxima tela informaremos a última versão do Spring Boot (neste momento 2.4.1)

Também nessa tela incluiremos as dependências do projeto, conforme imagem abaixo:

![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_738ad98ddd1f520b.jpg)  
 
 

Segue detalhes das dependências necessárias para nosso projeto:

- **Spring Boot DevTools:** É uma biblioteca que auxilia muito no desenvolvimento pois é composto por várias “ferramentas” muito uteis para o desenvolvimento de aplicações, como, por exemplo, recarregamento automático após alguma modificação do código.  
- **Lombok:** É uma biblioteca Java focada em produtividade e redução de código que por meio de anotações adicionadas ao código elimina a necessidade de criarmos códigos default como por exemplo os métodos getters e setters, pois o **lombok** os geram automaticamente.
- **Validation:** Permite validar, de forma fácil, objetos de classes que representam o domínio de uma aplicação.  
- **Spring Data JPA:** Precisamos adicionar essa dependência para conseguirmos usar o Spring Data JPA com Hibernate.  
- **H2 Database:** Fornece um banco de dados na memória rápido que suporta acesso JDBC API e R2DBC, com uma pegada pequena (2 MB). Suporta modos integrados e de servidor, bem como um aplicativo de console baseado em navegador.  
- **Spring Web:** Inicializador para criar aplicativos da Web, incluindo RESTful. Usa o Tomcat como o contêiner incorporado padrão.

Ao finalizar, será criado o projeto de acordo com os parâmetros que foi informado e o Maven faz o download das dependências para seu repositório local.

**Mapeando Entidades**

O mapeamento das Entidades é feito usando anotações. Nós utilizaremos a anotação @Entity para informar ao JPA que se trata de uma classe de entidade. Nessa classe vamos mapear a tabela que será criada no banco de dados com todos os campos pertencentes a ela, para que seja possível persistir os dados no banco de dados com o auxílio do Spring Data, para isso vamos criar uma classe com o nome Conta no pacote br.com.banco.model.

Segue abaixo todas as anotações usadas nessa classe:

**@*****\*Data\****: Gera todo o padrão que normalmente teríamos que criar na mão como por exemplo os getters e setters da nossa classe, o método toString() entre outros.

**@Entity:** É utilizada para informar que a classe também é uma entidade. 

**@Id:** É utilizada para informar ao JPA qual atributo de uma entidade estará relacionado à chave primária da respectiva tabela no banco de dados. 

**@GeneratedValue:** É utilizada para informar que a geração do valor do identificador único da entidade será gerenciada pelo provedor de persistência. Essa anotação deve ser adicionada logo após a anotação @Id.

**@NotEmpty:** Valida se a propriedade não é nula ou vazia; pode ser aplicado aos valores String, Collection, Map ou Array;

**@NotNull:** Valida se o valor da propriedade anotada não é nulo;

**@Email:** Valida se a propriedade anotada é um endereço de e-mail válido;

**@CPF:** Verifica se a sequência de caracteres anotados representa um contribuinte individual brasileiro número de registro (Cadastro de Pessoa Física)

Também nessa classe, será tratado as mensagens, caso algum campo obrigatório esteja vazio ou nulo.

Segue abaixo o código dessa classe:


 


 


 


 


 


 


 

![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_50f044d0fee340d.jpg)  
 
 

**Criando um Repositório**

Nossa API precisará de um repositório para persistir os dados da entidade Conta no banco de dados, para isso vamos criar uma interface com nome ContaRepository no pacote br.com.banco.repository.

O Repository será implementado pelo Spring Data JPA e criaremos dois métodos para nossa aplicação, um para verificar se já existe CPF e outro para verificar se já existe E-mail. 

Ah! Não podemos esquecer da anotação **@Repository**, pois é ela que informa que a interface é um repositório. Veja nosso código abaixo:

**C![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_c6064d3d109bd4ea.jpg)  
 riando o Serviço**

Nessa Interface informaremos apenas a assinatura dos métodos que deverão ser implementados. Vamos criar uma interface com o nome ContaService no pacote br.com.banco.service.

Veja abaixo o código da Interface:

 ![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_9fd2e34034ed2b6e.jpg)  
 **Criando a Implementação do Serviço**

Nossa lógica de negócios ficará contida nessa classe que implementará a interface ContaService. Veja logo abaixo na imagem que é nessa classe que tratamos a verificação de e-mail e CPF duplicado. Para isso vamos criar uma classe com o nome ContaServiceImpl no pacote br.com.banco.service.impl. 

Abaixo se encontra as anotações dessa classe:

**@Service:** Para informar que a classe é um serviço;

**@Autowired:** Será responsável para injeção de dependência;

**@Override:** Usaremos para sobrescrever um método;

**@Transactional:** Será usado para termos certeza que a operação será realizada por completo em caso de sucesso ou ser abortada por completo em caso de erro. 

Veja na imagem abaixo o código dessa classe:

**C**![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_8ee161dcea729935.jpg)  
 **riando os Controladores**

Essa classe é responsável por controlar os dados enviados e recebidos. Precisamos apenas anotar com @RestController, mapear a rota da API com @RequestMapping, @PostMapping e também incluir a anotação @ResponseStatus devolvendo o status de quando for criado com sucesso.

Anotações usadas na Classe:

**@RestController:** Informa que a classe é um manipulador de solicitações. Cuida do mapeamento dos dados da solicitação para o método definido do manipulador de solicitações.

**@RequestMpping:** Mapeia um caminho ou padrão de solicitação específico para um controlador ou método.

**@PostMapping:** Método responsável por salvar os dados.

**@****ResponseStatus:** Usado para definir o código de status HTTP.

Veja abaixo o código da classe:

![img](file:///C:/Users/Peter/AppData/Local/Temp/lu154409og70u.tmp/lu154409og970_tmp_3e4d2d8c0ba943b.jpg)  
 
 

Com essa última classe finalizamos a criação de nossa API! Espero ter atendido tudo que foi proposto e consiga passar para a próxima fase. Obrigado!


 

Peter Oliveira Freitas

E-mail: [freitaspeteroliveira@gmail.com](mailto:freitaspeteroliveira@gmail.com)

https://www.linkedin.com/in/peteroliveirafreitas/