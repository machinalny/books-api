<p align="center">
	<img src="https://raw.githubusercontent.com/Project-Books/book-project/master/media/banner/book_project_newlogo_2x.png" alt="Logo"/>
</p>

<p align="center">
  <a href="https://github.com/Project-Books/books-api/actions/workflows/build.yml">
    <img src="https://github.com/Project-Books/books-api/actions/workflows/build.yml/badge.svg" alt="Build Status" />
  </a>
	
  <a href="https://sonarcloud.io/dashboard?id=Project-Books_books-api">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=Project-Books_books-api&metric=coverage" alt="Code coverage" />
  </a>
	
  <a href="https://join.slack.com/t/teambookproject/shared_invite/zt-punc8os7-Iz9PTCAkYcO_0S~XwtO5_A">
    <img src="https://img.shields.io/badge/slack-teambookproject-4A154B?logo=slack" alt="Slack" />
  </a>
</p>

GraphQL books API made using Spring Boot and [DGS](https://netflix.github.io/dgs/). This is a sibling project of the [Book Project](https://github.com/Project-Books/book-project).

## Setup

Prerequisites: 
- JDK 11 or higher
- Configure [Lombok](https://github.com/Project-Books/book-project/wiki/Troubleshooting#cannot-find-log-statements-or-the-entities-do-not-have-constructors-lombok-errors)
- MySQL 8.0.* or (better) Docker

Recommended IntelliJ plugin: [JS GraphQL](https://plugins.jetbrains.com/plugin/8097-js-graphql)

## Running the app

1. Import as a Maven project into your favourite IDE
2. Start the MySQL Database or run the docker-compose file `docker-compose up -d` (you may need to add `sudo` to this command)
3. Run `BooksApiApplication.java`
4. Go to `localhost:8080/graphiql`

Sample query:
```graphql
{
  findAllBooks {
    title
    authors {
      fullName
    }
    genre
    isbn13
    yearOfPublication
    format
  }
}
```

### Access database

To access the MySQL database when docker-compose is running:

1. Go to `http://localhost:8081`
2. Log in with the details below:
    - Username: `root`
    - Password: `rootpassword`

Alternatively, see how to do this in [Intellij](https://project-books.github.io/docs/development/how-to/database-intellij/). Note that you'll need to use `root` as the username, `rootpassword` as the password and port 3307.

## Contributing

If you wish to contribute (thanks!), please first see the [contributing document](https://github.com/Project-Books/books-api/blob/main/CONTRIBUTING.md).
