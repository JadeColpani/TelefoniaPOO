# TelefoniaPOO

Projeto de cadastro de assinantes
Este projeto tem como objetivo implementar um sistema de cadastro de assinantes de uma operadora de telefonia.

# Classes

# Assinante
A classe Assinante é a classe principal do projeto e representa um assinante da operadora. Seus atributos incluem CPF, nome e número de telefone. Além disso, a classe possui um atributo protegido numChamadas, que representa o número de chamadas feitas pelo assinante. O construtor da classe deve inicializar os seus atributos com os argumentos do construtor e instanciar o vetor chamadas.

# PrePago
A classe PrePago herda da classe Assinante e representa um assinante pré-pago. Além dos atributos herdados, a classe PrePago possui um atributo creditos, que representa o número de créditos do assinante, e um atributo numRecargas, que representa o número de recargas feitas pelo assinante. A classe também possui os métodos recarregar, fazerChamada e imprimirFatura.

# Recarga
A classe Recarga representa uma recarga de crédito feita pelo assinante pré-pago. Seus atributos incluem a data da recarga e o valor da recarga.

# Chamada
A classe Chamada representa uma chamada feita pelo assinante. Seus atributos incluem a data da chamada e a duração da chamada em minutos.

# Como utilizar o projeto
Para utilizar o projeto, basta instanciar objetos das classes Assinante, PrePago, Recarga e Chamada e utilizar os métodos disponíveis em cada uma delas. É importante seguir as regras descritas nas orientações do projeto para garantir o funcionamento correto do sistema.

# Autor
Este projeto foi desenvolvido por Felipe Nascimento, Jade Colpani e Mari Diniz.
