
# Midterm Project: SpringCommerce
## Self-introduce
- **Student name**: Trần Minh Trí
- **Student ID**: 52000815
- **Subject**: Thực hành Công nghệ Java - Nhóm 2 - Tổ 2
- **Lecturer**: Võ Văn Thành

## Table of contents
- [Architecture](#architecture)
- [Code structure](#code-structure)
- [How to run](#how-to-run)
- [Verify APIs (Postman snapshots)](#verify-apis)

## Architecture
- Software development pattern: Use MVC pattern for easier to maintain and modify the code. Allows creation of reusable code and more efficient development.
- Software development princlple: Use TDD (Test Driven Development) principle but still incompleted.
- Software development practices: Use Continuous Delivery/Deployment (CD) to automatically deployed to production after passing through a series of automated tests and approval steps.. 

## Code structure
The project has a total of 4 modules, of which 2 main modules are Admin and Customer, 2 sub modules are Library and API. Two main modules Admin and Customer contains Config (to serve Spring Security) and Controller (to serve requests). Library module is responsible for providing all DTO (Data Transfer Object), Model, Repository, Service and Utils to serve 2 main modules, Admin and Customer. API module contains RestController to return results based on request.

### Entity Relationship Diagram
![ERD](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/erd.png)

## How to run 
Software install requirements: Intellij IDEA (Recommend using Ultimate version), MySQL server (Apache Netbeans or MySQL Workbench).<br> 
Here is steps to run Admin module (others module are same):<br>

![how-to-run](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/how-to-run.png)

## Verify APIs 
Here is some snapshots of CRUD on Product (same for Order):
Get all products: <br>
![get-all-products](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/Postman%20Snapshots/get-all-products.jpg)

Get product by id: <br>
![get-product-by-id](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/Postman%20Snapshots/get-product-by-id.jpg)

Create new product: <br>
![create-new-product](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/Postman%20Snapshots/create-new-product.jpg)

Update product by id: <br>
![update-product-by-id](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/Postman%20Snapshots/update-product-by-id.jpg)

Delete product by id: <br>
![delete-product-by-id](https://github.com/tmt203/SpringCommerce/blob/main/z-support%20data%2C%20images%2C%20etc/Postman%20Snapshots/delete-product-by-id.jpg)
