# University Identity Management System

## About
This project is written in Java and uses the [Spring](https://spring.io/) framework.

In addition, this project hosts its data on a [MySQL](https://www.mysql.com/) database.

This database is run on a [Raspberry Pi 5](https://www.raspberrypi.com/products/raspberry-pi-5/)
running [Debian 12](https://www.debian.org/releases/stable/releasenotes).

## Prerequisites
To run this project, you will need
[Java 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
installed on your machine.

1. Please ensure that you have `git` by running the following command in a terminal: `git --version`.
2. Please ensure that you have `java 20` by running the following command in a terminal: `java --version`.
3. Please ensure that you are connected to the same network as the server. You can test this by running `ping [host address]` in a terminal and seeing if there's a response.

## Host Server & Database Setup
Firstly, please ensure that you have a Linux server running that can access the Internet.
From there, you can install `MySQL` by running `sudo apt install mysql-server` in the terminal.
Please finish the setup guide found [here](https://www.geeksforgeeks.org/how-to-install-mysql-on-linux/).

Run the following command as a MySQL root user: `CREATE USER 'new_user'@'%' IDENTIFIED BY 'password';`. By using `%`, you will allow the user to 


Lastly, run the following commands:
- `GRANT ALL PRIVILEGES ON ProductionDatabase.* To 'user'@'%' IDENTIFIED BY 'password';`
- `GRANT ALL PRIVILEGES ON ProductionDatabase.* To 'user'@'%' IDENTIFIED BY 'password';`

This will ensure that remote users cannot access every database.


## Installation
1. Open a terminal and run `git clone https://github.com/usd-cs/comp305-final-project-sp24-s01s02-jj.git`
2. Open the newly created folder, and navigate to `src/main/resources`. From there, create a file named `config.properties` and enter the following information:
   1. ```properties
      database_name=
      database_username=
      database_password=
      database_host=
      ```
3. Open the newly created folder, and navigate to `src/test/resources`. From there, create a file named `config.properties` and enter the following information:
   1. ```properties
      database_name=
      database_username=
      database_password=
      database_host=
      ```

4. Open a terminal in the root folder and run the command:
   1. `./gradlew build` on macOS/Linux
   2. `gradlew build` on Windows

5. Navigate to `build/libs/`, and execute the JAR file that is created.
6. Visit `localhost:8080`

## Contact
- aalbizati@sandiego.edu
- johnphillips@sandiego.edu
- nhuang@sandiego.edu