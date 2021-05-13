# Currencies

O objetivo deste projeto é aplicar os conhecimentos obtidos em Orientação a Objetos em nossa disciplina, aplicando-o em um projeto pratico. Para tal, vamos consumir uma API de cotação de moedas, a [HGBrasil](https://console.hgbrasil.com/documentation/finance).
Cnsumindo os dados desta API, nós vamos aplicas os conceitos de Abstracão, Herança, Encapsulamento, Polimorfismo e Composição.

## Steps

Vaos criar a aplicação `Step-By-Step`, ou seja passo-a-passo para podermos acompanhar o máximo possível o desenvolvimento e a aplicação dos conceitos.

### Step 01 - Criar as Model Classes

Para podermos coletar os dados da API precisaos ter uma estrutura organizada de dados para poder abstrair e anter estes dados. 
Observe a estrutura de dados que é retornada pela API. Ela é uma resposta em JSON que contem varias informações, mas o mais importante pra gente é node currencies; ele comtem as cotações de moedas que iremos utilizar em nosso projeto.
Observe que o node `currencies` possui várias cotações de moedas coo Dolar, Euro, Yen, etc. Todas as cotações tem caracteristicas em comum, para esta caracteristicas comuns poder utilizar a **Herança**.
O processo e observar e definir a classe e definir os dados(atributos), é processo de **Abstração**.
Para abstrair todas as classes que iremos utilizar, vamos realmente precisar utilizar a Herança.

```json
{
  "by": "default",
  "valid_key": false,
  "results": {
    "currencies": {
      "source": "BRL",
      "USD": {
        "name": "Dollar",
        "buy": 5.306,
        "sell": 5.3045,
        "variation": 1.58
      },
      "EUR": {
        "name": "Euro",
        "buy": 6.407,
        "sell": 6.4047,
        "variation": 0.98
      },
      "GBP": {
        "name": "Pound Sterling",
        "buy": 7.457,
        "sell": null,
        "variation": 1.14
      },
      "ARS": {
        "name": "Argentine Peso",
        "buy": 0.057,
        "sell": null,
        "variation": 1.56
      },
      "CAD": {
        "name": "Canadian Dollar",
        "buy": 4.3705,
        "sell": null,
        "variation": 1.275
      },
      "AUD": {
        "name": "Australian Dollar",
        "buy": 4.0969,
        "sell": null,
        "variation": 0.062
      },
      "JPY": {
        "name": "Japanese Yen",
        "buy": 0.0484,
        "sell": null,
        "variation": 0.526
      },
      "CNY": {
        "name": "Renminbi",
        "buy": 0.822,
        "sell": null,
        "variation": 1.193
      },
      "BTC": {
        "name": "Bitcoin",
        "buy": 286210.294,
        "sell": 286210.294,
        "variation": -10.315
      }
    },
    "stocks": {
      "IBOVESPA": {
        "name": "BM&F BOVESPA",
        "location": "Sao Paulo, Brazil",
        "points": 119710.03,
        "variation": -2.65
      },
      "NASDAQ": {
        "name": "NASDAQ Stock Market",
        "location": "New York City, United States",
        "points": 13031.68,
        "variation": -2.67
      },
      "CAC": {
        "name": "CAC 40",
        "location": "Paris, French",
        "variation": -2.36
      },
      "NIKKEI": {
        "name": "Nikkei 225",
        "location": "Tokyo, Japan",
        "variation": -2.61
      }
    },
    "available_sources": [
      "BRL"
    ],
    "taxes": [
      
    ]
  },
  "execution_time": 0.0,
  "from_cache": true
}
```
#### Model Classes

1. Adicione a dependencia Jackson para manipulação (deserialização de JSON em classes Java) no arquivo `pom.xml`, adicione o trecho abaixo.
```xml
    <dependencies>
        <!-- outras dependencias acima -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.2</version>
        </dependency>
    </dependencies>
```

2. Crie o pacote `model`;
3. Crie a classe abstrata Moeda. Esta será a classe base para as demais moedas utilizadas, as anotações `@JsonProperty` servem para compatibilizar nosso modelo co os dados obtidos pela API.
```java
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Moeda {

    @JsonProperty("name")
    private String nome;
    @JsonProperty("buy")
    private Double compra;
    @JsonProperty("sell")
    private Double venda;
    @JsonProperty("variation")
    private Double variacao;
    private LocalDate localDate;

    public Moeda(String nome, Double compra, Double venda, Double variacao) {
        this.nome = nome;
        this.compra = compra;
        this.venda = venda;
        this.variacao = variacao;
        this.localDate = LocalDate.now();
    }

    public Moeda() {
    }

    public String getNome() {
        return nome;
    }

    public Moeda setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Double getCompra() {
        return compra;
    }

    public Moeda setCompra(Double compra) {
        this.compra = compra;
        return this;
    }

    public Double getVenda() {
        return venda;
    }

    public Moeda setVenda(Double venda) {
        this.venda = venda;
        return this;
    }

    public Double getVariacao() {
        return variacao;
    }

    public Moeda setVariacao(Double variacao) {
        this.variacao = variacao;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Moeda setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "nome='" + nome + '\'' +
                ", compra=" + compra +
                ", venda=" + venda +
                ", variacao=" + variacao +
                '}';
    }
}
```
5. Crie a classe Moedas para manter todas cotações de moedas recebidas. Nesta classe aplicamos o princípio da **Composição**.
```java
@JsonIgnoreProperties(ignoreUnknown = true)
public class Moedas {
    private String source;
    @JsonProperty("USD")
    private Dolar dolar;
    @JsonProperty("ARS")
    private PesoArgentino pesoArgentino;
    @JsonProperty("EUR")
    private Euro euro;
    @JsonProperty("JPY")
    private Yen yen;

    public Moedas(String source, Dolar dolar, PesoArgentino pesoArgentino, Euro euro) {
        this.source = source;
        this.dolar = dolar;
        this.pesoArgentino = pesoArgentino;
        this.euro = euro;
    }

    public Moedas() {
    }

    public String getSource() {
        return source;
    }

    public Moedas setSource(String source) {
        this.source = source;
        return this;
    }

    public Dolar getDolar() {
        return dolar;
    }

    public Moedas setDolar(Dolar dolar) {
        this.dolar = dolar;
        return this;
    }

    public PesoArgentino getPesoArgentino() {
        return pesoArgentino;
    }

    public Moedas setPesoArgentino(PesoArgentino pesoArgentino) {
        this.pesoArgentino = pesoArgentino;
        return this;
    }

    public Euro getEuro() {
        return euro;
    }

    public Moedas setEuro(Euro euro) {
        this.euro = euro;
        return this;
    }

    public Yen getYen() {
        return yen;
    }

    public Moedas setYen(Yen yen) {
        this.yen = yen;
        return this;
    }
    
    //Adicione as demais moedas aqui...

    @Override
    public String toString() {
        return "Moedas{" +
                "source='" + source + '\'' +
                ", dolar=" + dolar +
                ", pesoArgentino=" + pesoArgentino +
                ", euro=" + euro +
                ", yen=" + yen +
                '}';
                //Atualize o toString() também
    }
}
```

5. Criando as **Classes Filhas**. **Para cada uma das moedas**; você deverá criar uma classe semelhante à classe Dolar criada no trecho abaixo; lembre-se que todo o código necessario já foi implementado na classe abstrata `Moeda`.
```java
public class Dolar extends Moeda{
    public Dolar() {
    }
}

```

### Step 02 - Criar o Controller para realizar as requisições à API HGBrasil

Agora iremos criar uma classe que será responsável por realizar uma solicitação à API e recuperar os dados obtidos no formato `JSON`; esta será a única responsabilidade desta classe.


1. Crie o pacote controllers.
2. Dentro do pacote controllers, crie a classe ``
```java
public class RequisicaoController {

    public HttpResponse<String> enviarRequisicao(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
```

### Step 03 - Criar o Serviço de Ctação de Moedas

Vamos criar uma classe que será responsável por realizar requisições periódicas à API, exibindo as cotações à cada 5 minutos.

1. Crie o pacote services.
2. Crie a classe `CotacaoService`.
3. Na classe `CotacaoService` implemente o método `converterCotacaoParaMoedas` para converter o resultado da requisição de cotação seja convertido para a classe `Moedas`.
```java
    public Moedas converterCotacaoParaMoedas(HttpResponse<String> response) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.readValue(response.body(), ObjectNode.class);
        String nodeCurrencies = jsonNodes.get("results").get("currencies").toString();

        return mapper.readValue(nodeCurrencies, Moedas.class);
    }
```
4. Crie um métdo de inicialização e execução do serviço que orquestre a execução à cada 5 minutos.
```java
    public void iniciar() {
        RequisicaoController controller = new RequisicaoController();

        TimerTask cotacaoTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    HttpResponse<String> response = controller.enviarRequisicao(URL);
                    Moedas moedas = converterCotacaoParaMoedas(response);
                    exibirCotacaoCompleta(moedas.getDolar());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer("CotacaoService");
        timer.scheduleAtFixedRate(cotacaoTask, 0, 5000);
    }
```
5. Crie um metodo para exibir a cotação de forma mais legível
```java
    private void exibirCotacaoCompleta(Moeda moeda) {
        System.out.println(moeda.getNome());
        System.out.println(moeda.getCompra());
        System.out.println(moeda.getVenda());
        System.out.println(moeda.getVariacao());
        System.out.println("\n----------------------------------------------\n");
    }
```

### Inicialização do Serviço de Cotação

Agora que todo o nosso códig estruturado em camadas e obedecendo os principios da Orientação à Objetos; vamos configurar a inicialização na classe principal do projeto, adicionando o trecho abaixo ao método `main`.

```java
        CotacaoService service = new CotacaoService();
        service.iniciar();
```

O método main deverá estar parecido com este:
```java
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CotacaoService service = new CotacaoService();
        service.iniciar();

    }
}
```

# Finalizamos!
