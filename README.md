# Simple Shop API
A simple e-commerce API where its possible to manage users, addresses, products, carts and orders.

## Tools
This project was created with the following tools:

- Java
- SpringBoot
- Spring Security
- JPA
- MySQL
- JUnit
- Mockito
- H2
- Java Faker
- Swagger


## Usage
To use this application, you will need Docker, Docker Compose and IntalliJ installed.

1. Clone this repository:
```bash
git clone git@github.com:vinibortoletto/springboot-mysql-shop.git
```

2. Navigate to the project's directory:
```bash
cd springboot-mysql-shop.git
```

3. Run Docker Compose
```bash
docker-compose up -d
```

4. Open the project in IntelliJ and run it.

5. If you wish, you can also import the endpoints collection in Postman:
https://github.com/vinibortoletto/springboot-mysql-shop/blob/main/simple_shop.postman_collection.json


 ## Endpoints
All requests with exception of `POST /users/login` and `POST /users` require an authentication token, which is generated after login.

#### Users
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `GET`  | `/users/{userId}`| Get user by id      |
| `PUT`  | `/users/{userId}`| Update user by id   |
| `DELETE`  | `/users/{userId}`| Delete user by id   |
| `GET`  | `/users`| Get all users  |
| `POST`  | `/users`| Create new user  |
| `POST`  | `/users/login`| Validate user login  |

#### Products
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `PUT`  | `/products/{productId}`| Update product by id      |
| `GET`  | `/products`| Get all products   |
| `POST`  | `/products`| Create new product   |
| `GET`  | `/products/{productId}`| Get product by id   |
| `DELETE`  | `/products/{productId}`| Delete product by id   |
| `GET`  | `/products/categories/{categoryId}`| Get all products by cateogry id   |

#### Orders
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `PUT`  | `/orders/status`| Update an order status      |
| `GET`  | `/orders`| Get all orders      |
| `POST`  | `/orders`| Create new order      |
| `GET`  | `/orders/{orderId}`| Get order by id      |
| `GET`  | `/orders/customer{customerId}`| Get all orders by user id      |

#### Categories
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `GET`  | `/categories/{categoryId}`| Get category by id      |
| `PUT`  | `/categories/{categoryId}`| Update category by id      |
| `DELETE`  | `/categories/{categoryId}`| Delete category by id      |
| `GET`  | `/categories`| Get all categories      |
| `POST`  | `/categories`| Create new category      |

#### Carts
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `PUT`  | `/carts/{cartId}`| Get cart by id      |
| `GET`  | `/carts`| Get all carts      |
| `GET`  | `/carts/customer/{customerId}`| Get cart by customer id      |

#### Addresses
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `GET`  | `/addresses/{addressId}`| Get address by id      |
| `PUT`  | `/addresses/{addressId}`| Update address by id      |
| `GET`  | `/addresses`| Get all addresses      |
| `POST`  | `/addresses`| Create new address      |
| `GET`  | `/addresses/customer/{customerId}`| Get all addresses by customer id      |

#### Customers
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `GET`  | `/customers`| Get all customers      |
| `GET`  | `/customers/{customerId}`| Get customer by id      |

#### Admins
| Method | Path      | Description                |
| :----  | :-------- | :------------------------- |
| `GET`  | `/admins`| Get all admins      |

## Database

<img src="https://i.imgur.com/U4IulF5.png" alt="" />





<!-- <img src="https://i.imgur.com/zdbAC7V.png" alt="" /> -->
