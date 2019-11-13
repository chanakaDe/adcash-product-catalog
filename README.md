# Adcash Product Catalog

The public RESTful API for the Product Catalog listing. 

### Tech

Adcash Product Catalog API uses a number of open source projects to work properly:

* Java
* Maven
* Spring framework
* Spring Web
* Spring Security
* MySQL
* JPA
* Project Lombok
* Jackson
* Junit

### Installation

Clone this repository and enter the repository. You need to have `Java` and `MySQL` installed in your working environment. 

```
git clone https://github.com/chanakaDe/adcash-product-catalog
cd adcash-product-catalog
```

Now you need to create a database in your MySQL instance named `Assignment2`.

### Configuration

To change the database connection, please visit `/src/main/resources/application.properties` file and enter your credentials.

```
spring.datasource.url=jdbc:mysql://localhost:3306/Assignment2?useSSL=false
spring.datasource.username=root
spring.datasource.password=chanaka
```

Since this is a maven application, there are many ways to install all the dependencies. It should be quite straightforward to run your application from an IDE with some maven support (Eclipse, IntellIJ , NetBeans).

#### Method 1
To deploy the project manually, please run the following command at the root folder structure of the project.

```
mvn clean install
```

#### Method 2

You can build the project using the following command. 

```
mvn clean build
```

Then in `target` folder, you can see the `JAR` file of the project. 
Navigate into the `JAR` file directory using terminal. Run following command to excute the application.

```
java -jar jarfilename.jar
```

### Optional - How to deploy application into Heroku

Since this is a Spring Boot application, it has it's own embedded server. so it's really easy to deploy into Heroku. Following instructions show how to deploy current application into Heroku using a free account.

```
cd adcash-product-catalog
heroku login
git init
git add .
git commit -m "first commit"
heroku create
git push heroku master
```

### Endpoints

#### Category

Get all categories : `/api/assignment/categories/find-all` <br/>
Getting products of a category : `/api/assignment/categories/find-one/{categoryId}` <br/>
Save a category : `/api/assignment/categories/save` <br/>
Update a category `/api/assignment/categories/save` (Sending same object with ID) <br/>
Delete a category : `/api/assignment/categories/delete/{categoryId}` <br/>

#### Product

Get all products : `/api/assignment/products/find-all` <br/>
Getting one product: `/api/assignment/products/find-one/{prodcutId}` <br/>
Save a product : `/api/assignment/products/save` <br/>
Update a product `/api/assignment/products/save` (Sending same object with ID) <br/>
Delete a product : `/api/assignment/products/delete/{prodcutId}` <br/>

#### Users authorization

Use basic auth with following username and password. 
All the POST, PUT and DELETE requests need to have basic auth. 

Username : chanaka , Password : 123
