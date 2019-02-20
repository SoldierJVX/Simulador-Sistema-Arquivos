# Simulador-Sistema-Arquivos-E-Terminal

Sistema para simular o funcionamento de um sistema de arquivos e terminal para navegação e manipulação de arquivos.

Estrutura do Sistema de Arquivos
- HashMap

Modelo de Controle de Acesso aos Arquivos
- Modelo Simular ao Linux - (proprietário, grupo e universo).

Comandos Implementados:

- adduser [nome de usuário]: adiciona um usuário no sistema de arquivos. Deve-se verificar se não existe um usuário com mesmo nome no sistema. Caso não exista, deve-se pedir para que o usuário digite a senha por duas vezes. Caso duas senhas digitadas sejam iguais, cadastre o usuário no sistema. Cada usuário cadastrado deve ter um diretório criado na raiz do sistema de arquivos.
 
- removeuser [nome de usuário]: remove um usuário do sistema. Somente o usuário root pode executar tal comando. Deve-se remover também o diretório do respectivo usuário.
 
- cd [nome do subdiretorio]: altera o diretório corrente para o diretório especificado. Considere a possibilidade de caminho relativo e caminho absoluto neste comando (assim como é comumente feito nos terminais ou prompts).
 
 - crfile [nome do arquivo]: cria um arquivo com o nome especificado. Serão considerados no sistema apenas arquivos no formato texto. O arquivo deve ser criado com permissões de leitura e escrita para o proprietário do arquivo e nenhuma dessas permissões para os demais usuários do sistema.

- mkdir [nome do diretorio]: cria um subdiretório com o nome especificado.

- ren [nome arquivo/diretório origem] [nome arquivo/diretório destino]: renomeia um arquivo ou diretório conforme os parâmetros especificados.

- rm [nome do arquivo/diretório]: remove um arquivo ou diretório especificado.

- append [nome do arquivo] [conteúdo]: adiciona o conteúdo no arquivo especificado.

- chmod [espeficicação de acesso] [usuário]: altera as propriedades de acesso ao arquivo. A especificação de acesso deve ser composta por um único dígito na qual 0 significa que o usuário não pode ler nem escrever, 1 significa somente leitura e 2 significa leitura e escrita.

- properties [nome do arquivo/diretório]: lista as propriedades do arquivo/diretório especificado.

- cat [nome do arquivo]: exibe o conteúdo do arquivo.

- format: realiza uma formatação lógica no sistema de arquivos.

- exit: encerra o terminal.

