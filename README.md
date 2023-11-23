# Gerenciador-Tarefas-Escolares

> Autor: Fernando Freitas de Lira

Projeto final realizado na disciplina de LP1, no curso de informática pelo IFSP. 

Orientador: Prof° Igor de Moraes Sampaio.

<div align="center">

![Intellij IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

</div>

## Descrição do projeto

O sistema visa ser um gerenciador pessoal de tarefas para usuários diferentes em uma mesma máquina. Com a possibilidade de autenticação de cada pessoa. Cada usuário cria suas tarefas e as vizualiza no painel principal, além de ter a capacidade de excluir cada tarefa. O usuário pode também criar categorias diferentes para essas tarefas.

## Funcionalidades

- **Autenticação de usuários**: cada usuários poderá em sua finalidade ser autenticado em um banco de dados com base em MySQL e então entrar em sua conta.

- **Criação de Tarefas**: Cada usuário pode criar tarefas e automaticante monitorá-las no painel principal

- **Criação de Categorias**: Cada usuário pode também criar uma categoria específica no sistema, além das fornecidas automaticamente

- **Criação de Relatórios**: Com suas tarefas cadastradas, o usuário tem métodos de monitorar seu progresso, como ver a porcentagem de conclusão, além do número total de tarefas concluídas até agora

## Utilização e recursos

Ao criar uma conta, o usuário tem a funcionalidade de criar tarefas com base em categorias personalizadas. Por padrão estas são:

- Escolar
- Pessoal
- Lazer

Cada categoria possui um nome e uma cor para melhor reconhecimento. A cor da categoria será mostrada no painel principal.

## Ferramentas e Bibliotecas utilizadas

- Apache Maven
- Java FileReader Library
- Intellij IDEA
- SceneBuilder for Intellij IDEA
- JavaFX
- Hibernate
- MySQL Workben 8.1

## O que será feito?

- [X] Implementação de classes de modelo/entidade
- [X] Armazenamento em banco de dados
- [X] Cadastro e login de usuários
- [X] Categoriazação de Tarefas
- [X] Interface Principal
- [ ] Armazenamento de relatórios em arquivos `.txt`
- [ ] Possiblidade de escolha do diretótorio de armazenamento
