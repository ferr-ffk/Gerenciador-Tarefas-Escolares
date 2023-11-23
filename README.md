# Gerenciador-Tarefas-Escolares

> Autor: Fernando Freitas de Lira

Projeto final realizado na disciplina de LP1, no curso de informática pelo IFSP. 

Orientador: Prof° Igor de Moraes Sampaio.

<div align="center">

![Intellij IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

</div>

<div align="center">

![Painel principal](https://github.com/ferr-ffk/gerenciador-tarefas-escolares/assets/126774900/558ef9b2-a37a-43d7-b3ec-3c79fb147b56)

![Página de Login](https://github.com/ferr-ffk/gerenciador-tarefas-escolares/assets/126774900/6ceee4a4-6d7d-443d-bd9e-99709845cba1)

</div>

## Descrição do projeto

O sistema visa ser um gerenciador pessoal de tarefas para usuários diferentes em uma mesma máquina. Com a possibilidade de autenticação de cada pessoa. Cada usuário cria suas tarefas e as vizualiza no painel principal, além de ter a capacidade de excluir cada tarefa. O usuário pode também criar categorias diferentes para essas tarefas.

## Funcionalidades

- **Autenticação de usuários**: cada usuários poderá em sua finalidade ser autenticado em um banco de dados com base em MySQL e então entrar em sua conta.

- **Criação de Tarefas**: Cada usuário pode criar tarefas e automaticante monitorá-las no painel principal

- **Criação de Categorias**: Cada usuário pode também criar uma categoria específica no sistema, além das fornecidas automaticamente

- **Criação de Relatórios**: Com suas tarefas cadastradas, o usuário tem métodos de monitorar seu progresso, como ver a porcentagem de conclusão, além do número total de tarefas concluídas até agora
 
![Criando nova tarefa](https://github.com/ferr-ffk/gerenciador-tarefas-escolares/assets/126774900/29dafc17-46c7-4af8-a544-ff98f2393548)

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
