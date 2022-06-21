Feature: API que retorna a previsao do tempo por cidade
  Scenario: cliente faz um GET para /weather/{cityName}
    When cliente consulta a cidade <cityName>
    Then cliente deve receber status <httpStatus>
    Examples:
    | cityName                      | httpStatus |
    | Toronto           	        |        200 |