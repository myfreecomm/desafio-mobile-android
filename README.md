# Raphael Bernardo

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
- Mapeador para separa modelos do Android e Modelos do banco, com isso é possível trocar o banco para qualquer outra fonte de dados.
- Teste unitário da camada Presenter.
- Teste instrumentado para todas as telas.


## Qual foi o foco

- Preocupação em manter o banco de dados local bem normalizado para futura escalabilidade. Por isso foram criadas 3 tabelas.
- Fazer um app estável, sem leaks de memória e com o lifecycle apenas de Acvivities sem Fragments.
- Linguagem ubíquia para o melhor entendimento do código.


## O que poderia melhorar

- Não foi possível separar toda a camada de dados no módulo de dados, pois o banco de dados Room é dependente direto de contexto (Android).
- As queries de partidas poderiam ter JOIN com a tabela de times, mas por questões de tempo decidi não fazer.
- Utilização do Proguard ou R8 para ofuscamento de código (não deu tempo).
- Design: tenho noção do material design mas foquei muito mais em arquitetura e lógica.


## O que seria legal ter

- Deeplink que levam para uma partida específica com stack de navegação caso o usuário volte (muitos apps não tem stack).


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

# Raphael Bernardo
Android Produtos

**Globo.com: coding challenge**

## O Desafio

Você deverá criar um aplicativo, chamado de "Brasileirão", para acompanhamento de jogos de futebol.

O aplicativo deverá funcionar em dispositivos iOS, Android ou em browsers desktop (com javascript), dependendo da vaga para a qual você está aplicando.

Você também deverá fornecer a API que provê os dados para o aplicativo com a tecnologia de sua escolha. Essa API não será avaliada, podendo ser implementada utilizando soluções prontas para backend. Apenas a implementação do aplicativo (incluindo a comunicação com o backend escolhido) será avaliada.

## Considerações Gerais

Você deverá usar este repositório como o principal do projeto. Todos os seus commits devem estar registrados aqui.

**Registre tudo**: Ideias que gostaria de implementar se tivesse mais tempo (explique como você as resolveria), decisões tomadas e seus porquês, arquiteturas testadas e os motivos de terem sido modificadas ou abandonadas.

Sinta-se livre para incluir ferramentas e bibliotecas open source.

Avaliaremos sua submissão como se fosse um produto mínimo viável (MVP), pronto para ser publicado em produção, mas que continuará sendo expandido e mantido no futuro. A avaliação terá como foco as seguintes características:

* se atende aos requisitos básicos.
* a qualidade do código, do projeto de software, da arquitetura.
* os testes.
* a automação.
* a escalabilidade.

Em caso de dúvidas, pergunte.

## O Aplicativo

O aplicativo possui duas telas: uma listagem de jogos e o detalhe de cada jogo.

### Tela Inicial

- Lista de jogos
  - Nome dos times
  - Escudos
  - Placar
  - Data e horário

Considere as seguintes condições:

- A lista de jogos deve estar disponível offline

- O usuário pode querer atualizar a lista de jogos

- Clicar sobre um jogo leva o usuário para a tela de detalhe daquele jogo

Exemplo de uma lista de jogos na web (lado direito): [brasileirão série a](http://globoesporte.globo.com/futebol/brasileirao-serie-a/).

### Detalhe do Jogo

- Detalhe do jogo
  - Nome dos times
  - Escudos
  - Placar
  - Data e horário
  - Local

- Lance a Lance
  - Lista de momentos importantes do jogo
    - Tempo no jogo
    - Descrição

- Atalho para voltar para a tela inicial

## Imagens

Escudos dos times

```
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/vasco_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/avai_60x60_.png
http://s.glbimg.com/es/sde/f/equipes/2014/09/15/sport_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/internacional_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/palmeiras_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2015/05/06/chapecoense_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/atletico_mg_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/coritiba_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/gremio_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2015/04/29/cruzeiro_65.png
http://s.glbimg.com/es/sde/f/equipes/2015/06/24/atletico-pr_2015_65.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/sao_paulo_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/flamengo_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/corinthians_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/ponte_preta_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/figueirense_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/goias_60x60.png
http://s.glbimg.com/es/sde/f/equipes/2015/05/05/fluminense-escudo-65x65.png
http://s.glbimg.com/es/sde/f/equipes/2014/04/14/santos_60x60.png
```


