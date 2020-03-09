# Propósito
Trata-se de um exercício prático da disciplina de Sistemas Distribuídos onde o propósito é exemplificar uma comunicação via **sockets TCP** entre cliente e Servidor (ambos Java) com o uso de **criptografia**. 

# Método de Criptografia
Foi usado um método bem simples e bastante conhecido: **Cifra de César**. Segundo Wobst 2001, em criptografia, a Cifra de César, também conhecida como cifra de troca, código de César ou troca de César, é uma das mais simples e conhecidas técnicas de criptografia. É um tipo de cifra de substituição na qual cada letra do texto é substituída por outra, que se apresenta no alfabeto abaixo dela um número fixo de vezes (esse número é definido pela chave). Por exemplo, com uma troca de três posições, A seria substituído por D, B se tornaria E, e assim por diante. O nome do método é em homenagem a Júlio César, que o usou para se comunicar com os seus generais.
Assim, foram criados dois métodos (ambos usados por Cliente e Servidor): codificar e decodificar, que recebem como parâmetro a mensagem a ser codificada/decodificada e a respectiva chave, retornando uma String (que será a mensagem criptografada ou descriptografada).
