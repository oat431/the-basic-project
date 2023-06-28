# the-basic-project
- blog website
- todo list application

for practice the real framework

## if it is a blog website

### frontend
- any meta framework likes Nuxt, Next
- (any good website much have..., try to learn frontend framework concept if possible)

### backend
- clean architecture
- clean code
- monolith (because focus only blogs feature, will change if there are possible way to do a microservice)
- any meta fraemwork likes Spring-boot3 (java), Gin-Gonic, ASP.NET7, NestJS(typescript)
- Open Document API (Swagger)
- GraphQL and RESTful API (gRPC if there is a possible way to do a micorservice)
- Unit testing

### Database
- PostgreSQL (for authentication purpose)
- Neo4J (for Graph Algorithm, Following feature)
- MongoDB (for Blogs feature, ??)
- Redis (for Cache)

### DevOps
- kong API gateway (maybe) or implement own API gate way
- automate deploy to (I don't know yet which one is suit) heroku or onrender
- can be switch to manual deploy if need to be rolled back
- here some process : Build App -> Test (at least 85% cov) -> Upload to DockerHub -> Deploy Using Docker (if there a auto deploy)
- all the process happend mostly on GitHub

### Cross Platform
- if frontend use Next, there is a React Native
- Flutter


### core feature
- the blog in the platform need to be approved from admin first
- the blog can be reported from admin (more detail later...)
- the blog has social status (love = 2 points, like = 1 point, dislike = -1 point)
- the blog are commentable
- the blog can save as favorite by user
- when user come to the platform they will saw the recommended blog first (can be promoted by super admin, or get score more than 20 points)
- (more feature will come later)


### Supaer Administration
- can select which user to be an admin
- can promote blogs in the platform
- can do everything as normal admin
- (will add more feature soon)

### Administration
- can approve the blog from users
- can delete blogs
- can add remark to any blogs on the platform
- can do everyting as user did

### user
- can CRUD blogs
- can follows other user


## if it is a todolist project
### frontend
- any meta framework likes Nuxt, Next
- (any good website much have..., try to learn frontend framework concept if possible)

### backend
- clean architecture
- clean code
- monolith
- any meta fraemwork likes Spring-boot3 (java), Gin-Gonic, ASP.NET7, NestJS(typescript)
- Open Document API (Swagger)
- RESTful API
- GraphQL
- Unit testing

### Database
- PostgreSQL

### DevOps
- automate deploy to (I don't know yet which one is suit) heroku or onrender
- can be switch to manual deploy if need to be rolled back
- here some process : Build App -> Test (at least 85% cov) -> Upload to DockerHub -> Deploy Using Docker (if there a auto deploy)
- all the process happend mostly on GitHub

### Cross Platform
- if frontend use Next, there is a React Native
- Flutter

### core feature
- CRUD Todo list
- CRUD Task in todo list

---

#### code name for my project

I have some cool code name for my future project it will bease on river around the world, something like
- Nile
- Amazon
- Danube
- Yangtze
- Mississippi
- Ganges
- Volga
- Mekong
- Rhine
- Zambezi
