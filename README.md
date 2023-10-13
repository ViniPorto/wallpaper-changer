# wallpaper-changer
Programa em Java que altera o wallpaper do Windows todos os dias. A imagem de fundo é o retorno da API utilizada. No caso, a API da NASA que retorna a imagem planetária do dia em: "https://api.nasa.gov/planetary/apod" + o parâmetro api_key. 

Para rodar em seu computador (Windows apenas) faça um clone do projeto. Após, entre na pasta:

``` bash
git clone git@github.com:ViniPorto/wallpaper-changer.git

cd wallpaper-changer

```

Dentro da pasta execute o comando:

``` bash

mvn clean package

```
Entre na pasta "target" e recorte o arquivo .jar que foi gerado para uma nova pasta no C:// do seu computador:

``` bash

cd target

```

Com o arquivo dentro da nova pasta em C:// (sugiro chamar a pasta de wallpaper-changer para localizar melhor) dê 2 cliques no mesmo.

Pronto, o arquivo já está rodando em sua máquina. De hora em hora será verificado a imagem planetária do dia e alterado o wallpaper. Caso não atualize no momento em que rodou o comando basta aguardar.

Dica: Crie um atalho para este arquivo dentro da pasta Startup do Windows para que o programa sempre seja inicializado junto à maquina e o wallpaper seja alterado todos os dias.
