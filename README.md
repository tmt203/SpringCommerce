
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
![ERD](https://github.com/tmt203/SpringCommerce/blob/main/ERD.png)

## How to run 
Software install requirements: Intellij IDEA (Recommend using Ultimate version), MySQL server (Apache Netbeans or MySQL Workbench). Here is steps to run Admin module (others module are same):
![how-to-run](https://github.com/tmt203/SpringCommerce/blob/main/how-to-run.png)
