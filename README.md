# Raphael Bernardo - Desafio Hexacta
## Proposta de desafio que contempla por completo e além os requisitos pedidos pela Hexacta, porém com dados diferentes.
- A idéia é economizar tempo e conseguir entregar este desafio com agilidade.

# O que foi feito

- App feito todo em linguagem Kotlin.
- Foi utilizado o Kotlin Coroutines, com injeção de escopo via Dagger2 para poder executa-los em testes unitários.
- A persistência usa o RoomDb e os dados estão separadas em três tabelas que se relacionam: uma de Partidas, outra de Times e outra de Lances.
- A arquitetura do app usa MVP e está separada em módulos, uma do app em sí e outra de dados.
- Para injeção de dependência utilizei o Dagger2.
- Foi utilizado o picasso, retrofit e okhttp para gerenciamento de imagens, e download de dados.
- Foi utilizado o Apiary para simular o backend.
- Telas redimensionáveis utilizando ConstraintLayout para todos os tamanhos de tela.
- Suporte a rotação de tela com salvamento temporário de dados (onSavestate())
- Mapeador para que modelos do Android e Modelos do banco, com isso é possível trocar o banco para qualquer outra fonte de dados.
- Teste unitário da camada Presenter.
- Teste instrumentado para todas as telas.


## Qual foi o foco

- Preocupação em manter o banco de dados local bem normalizado para futura escalabilidade. Por isso foram criadas 3 tabelas onde em cada uma há uma coluna de chave estrangeira para as demais.
- Fazer um app estável, sem leaks de memória e com o lifecycle apenas de Acvivities sem Fragments.
- Linguagem ubíquia para o melhor entendimento do código.
- Simplicidade e escalabilidade da arquitetura.


## O que poderia melhorar

- Não foi possível separar toda a camada de dados no módulo de dados, pois o banco de dados Room é dependente direto de contexto (Android).
- As queries de partidas poderiam ter JOIN com a tabela de times, mas por questões de tempo decidi não fazer.
- Utilização do Proguard ou R8 para ofuscamento de código (não deu tempo).
- Design: tenho noção do material design mas foquei muito mais em arquitetura e lógica.


## O que seria legal ter

- Deeplink que levam para uma partida específica com stack de navegação caso o usuário volte (muitos apps não tem stack).
- Notificações de jogos que estão próximos de acontecer.


## O app obedece a seguinte estrutura de dados vinda de uma API em JSON

	{
		"match_list": [
			{
				"id": "1",
				"match_date": "2020-02-05T08:40:51.620Z",
				"match_place": "Maracanã, Rio - RJ",
				"match_is_over": true,
				"match_teams": {
					"home": {
						"id": 1,
						"team_name": "Flamengo",
						"team_shield": "http://s.glbimg.com/es/sde/f/equipes/2014/04/14/flamengo_60x60.png",
						"match_score": 2
					},
					"away": {
						"id": 2,
						"team_name": "Vasco",
						"team_shield": "http://s.glbimg.com/es/sde/f/equipes/2014/04/14/vasco_60x60.png",
						"match_score": 0
					}
				},
				"match_highlights": [
					{
						"id": 1,
						"match_id": 1,
						"type": "match_start",
						"description": "Início de partida",
						"match_time": "00m 00s"
					},
					{
						"id": 2,
						"match_id": 1,
						"type": "goal",
						"description": "Gol de Gabriel Barbosa",
						"match_time": "35m 17s",
						"favored_team_id": 1
					},
					{
						"id": 3,
						"match_id": 1,
						"type": "goal",
						"description": "Gol de Bruno Henrique",
						"match_time": "77m 23s",
						"favored_team_id": 1
					},
					{   
						"id": 4,
						"match_id": 1,
						"type": "var_lookout",
						"description": "Consulta ao VAR",
						"match_time": "80m 03s"
					},
					{
						"id": 5,
						"match_id": 1,
						"type": "penalty",
						"description": "Penalty de Rodrigo Caio em cima de Pikachu",
						"match_time": "82m 13s",
						"favored_team_id": 2
					},
					{
						"id": 6,
						"match_id": 1,
						"type": "match_end",
						"description": "Fim de jogo",
						"match_time": "90m 00s"
					}
				]
			}
		]
	}

===================================================================================

# (README ORIGINAL)

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
