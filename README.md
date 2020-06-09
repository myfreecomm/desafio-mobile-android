Teste de Android Developer Nexaas
===================

Este teste é apresentado aos candidatos as vagas de Android Developer para avaliar os quesitos técnicos.

----------


O Desafio
-------------

Seu objetivo é criar um app com duas telas, uma que exibe uma lista que exibe o resultado de uma API e outra mostra os dados detalhados do item selecionado

> **Notas:**

> - A ideia é avaliar a criação de telas com o padrão material design usando todos os componentes do androidx
> - Lembre-se de seguir toda guideline material design (https://material.io/)
> - Para o desenho das telas, não precisa se preocupar com a interface na horizontal;
> - Material de ajuda segue no link (https://material.io/components/lists/)


#### <i class="icon-file"></i> Telas
<table>
<tbody>
<tr><th>Listagem</th>
  <th>Detalhes</th>
</tr>
<tr>
<td><img src="https://github.com/myfreecomm/desafio-mobile-android/blob/master/screens/two-line-example-2.png?raw=true" style="height:300px">
</td>
<td><img src="https://github.com/myfreecomm/desafio-mobile-android/blob/master/screens/01-list-parentchild.gif?raw=true" height="650" width="1060" style="max-width:100%;">
</td>
</tr>
</tbody>
</table>


#### <i class="icon-folder-open"></i> Consumindo Serviço

Para consumir o serviço da API Rest

**Como usar:**

> - **URL** https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/data.json
> - **Método**: GET

Exemplo de resposta
>  [{
        "name": "Pencil",
        "quantity": 1,
        "stock": 5,
        "image_url": "https://github.com/myfreecomm/desafio-mobile-android/blob/master/assets/pencil.png?raw=true",
        "price": 150,
        "tax": 162,
        "shipping": 50,
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."
    }]

#### <i class="icon-pencil"></i> Pré-requisitos

- Versão mínima do SDK: 21
- Tela deve ajustar em devices menores.
- Utilizar Kotlin

#### <i class="icon-folder-open"></i> O que esperamos
- Boa arquitetura, pode ser  (mvc, mvp, mvvm, clean etc)
- RxJava ou Coroutines
- Testes unitários
- Cache de imagens
- Tratamentos de erros
- Padrão de Projeto e boas práticas de Orientação a Objetos.
- Google AAC (Android Architecture Components) 

#### <i class="icon-hdd"></i> Plus
- Construir layouts com Constraints
- Trabalhar offline (cache dos dados)
- Injeção de dependência (dagger, koin, kodein)


Publicação
-------------

Crie um **Fork** do repositório para realizar o teste, e depois de finalizado envie um **Pull Request** para nossa equipe interna avaliar
