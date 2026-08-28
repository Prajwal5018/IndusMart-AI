# 🚀 IndusMart AI

### AI-Powered Industrial Marketplace

IndusMart AI is a full-stack industrial marketplace designed to connect buyers, sellers, manufacturers, and equipment providers through a centralized digital platform.

The platform allows businesses to buy, sell, and rent industrial equipment while providing secure authentication, seller subscriptions, product management, shopping cart, wishlist, orders, payments, reviews, and dedicated buyer and seller dashboards.

---

## 📌 Project Status

> **Core Project Completed**

The current release contains the complete core marketplace workflow including authentication, product management, subscriptions, cart, wishlist, orders, payments, reviews, buyer dashboard, seller dashboard, and company management.

The project can continue to receive additional AI, analytics, UI, and optimization enhancements in future versions.

---

# 🎯 Objectives

Traditional industrial equipment procurement can be difficult because businesses often have to search across multiple suppliers and platforms.

IndusMart AI aims to provide a centralized marketplace where users can:

- Discover industrial equipment
- Compare products
- Purchase equipment
- Rent equipment
- Manage products as sellers
- Manage companies
- Track orders
- Make payments
- Save products to wishlist
- Manage shopping cart
- Review products
- Subscribe to seller plans
- Access personalized dashboards

---

# ✨ Features

## 🔐 Authentication

- User registration
- User login
- JWT-based authentication
- Role-based access
- Protected routes
- Buyer and seller roles

---

## 🛒 Buyer Features

- Buyer dashboard
- Browse products
- Product search
- Search by category
- Search by brand
- Search by product name
- Product details
- Shopping cart
- Wishlist
- Checkout
- Payment
- Order history
- Product reviews

---

## 🏭 Seller Features

- Seller dashboard
- Product management
- Add products
- Edit products
- Delete products
- View company products
- Product availability management
- Sale availability
- Rental availability
- Company management
- Seller subscription management
- Product upload limits

---

## 🏢 Company Management

Sellers can manage their company information including:

- Company name
- GST number
- PAN number
- Email
- Phone
- Website
- Address
- City
- State
- Country
- PIN code
- Description
- Company logo
- Verification status

---

## 💳 Subscription System

The platform provides seller subscription plans.

### BASIC

- ₹5,999
- 10 products
- 365 days

### PROFESSIONAL

- ₹7,999
- 20 products
- 365 days
- Featured listing
- AI recommendation

### PREMIUM

- ₹12,999
- Unlimited products
- 365 days
- Featured listing
- AI recommendation
- AI chatbot
- Priority support

Subscription functionality includes:

- Buy subscription
- Upgrade subscription
- Renew subscription
- Subscription expiry
- Product upload limits
- Product upload count
- Subscription payment records
- Invoice numbers

---

## 💰 Payment System

The project includes payment processing architecture with:

- Payment orders
- Payment IDs
- Payment signatures
- Payment status
- Payment history
- Invoice numbers
- Subscription payments
- Order payments

Razorpay integration is used for the payment workflow.

---

# 📦 Product Management

Each product supports:

- Product name
- Description
- Purchase price
- Rental price per day
- Quantity
- Category
- Brand
- Model number
- Product image
- Sale availability
- Rental availability
- Company association

---

# 🔎 Product Search

Products can be searched using:

- Product name
- Brand
- Category
- Company

---

# 📋 Order Management

The platform supports:

- Cart checkout
- Order creation
- Order items
- Payment processing
- Order history
- Order status
- Payment status
- Order cancellation where applicable

---

# ❤️ Wishlist

Buyers can:

- Add products to wishlist
- View wishlist
- Remove products from wishlist

---

# ⭐ Reviews

The marketplace supports product reviews and ratings so buyers can provide feedback about purchased products.

---

# 📊 Dashboards

## Buyer Dashboard

Provides buyers with access to:

- Products
- Cart
- Wishlist
- Orders
- Account information

## Seller Dashboard

Provides sellers with access to:

- Products
- Product statistics
- Company information
- Subscription information
- Product management

---

# 🏗️ System Architecture

```text
                    ┌─────────────────────────┐
                    │       User / Buyer      │
                    └────────────┬────────────┘
                                 │
                                 ▼
                    ┌─────────────────────────┐
                    │    React Frontend       │
                    │       Vite + UI         │
                    └────────────┬────────────┘
                                 │
                              REST API
                                 │
                                 ▼
                    ┌─────────────────────────┐
                    │   Spring Boot Backend   │
                    │                         │
                    │ Controllers             │
                    │ Services                │
                    │ Repositories            │
                    │ JWT Authentication      │
                    └────────────┬────────────┘
                                 │
                                 ▼
                    ┌─────────────────────────┐
                    │        MySQL             │
                    │                         │
                    │ Users                   │
                    │ Companies               │
                    │ Products                │
                    │ Cart                    │
                    │ Wishlist                │
                    │ Orders                  │
                    │ Reviews                 │
                    │ Subscriptions           │
                    │ Payments                │
                    └─────────────────────────┘

```

---

# 🛠️ Technology Stack

## Frontend

- React.js
- Vite
- JavaScript
- React Router
- Axios
- Tailwind CSS

## Backend

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- Bean Validation
- Lombok
- Maven

## Database

- MySQL

## API Documentation

- Swagger UI
- OpenAPI

## Payment Gateway

- Razorpay

---

# 📁 Project Structure

```text
IndusMart-AI/
│
├── indusmart-backend/
│   │
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── indusmart/
│   │       │           ├── config/
│   │       │           ├── controller/
│   │       │           ├── dto/
│   │       │           ├── email/
│   │       │           ├── entity/
│   │       │           ├── enums/
│   │       │           ├── exception/
│   │       │           ├── pdf/
│   │       │           ├── repository/
│   │       │           ├── security/
│   │       │           └── service/
│   │       │
│   │       └── resources/
│   │
│   ├── pom.xml
│   └── README.md
│
├── indusmart-frontend/
│   │
│   ├── public/
│   ├── src/
│   │   ├── api/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── context/
│   │   ├── hooks/
│   │   ├── layouts/
│   │   ├── pages/
│   │   ├── routes/
│   │   ├── services/
│   │   └── utils/
│   │
│   ├── package.json
│   └── vite.config.js
│
├── .gitignore
└── README.md
```

---

# 🗄️ Database

The application uses MySQL.

Main database tables include:

```text
users
companies
products
categories
product_images
cart
wishlist
orders
order_items
reviews
subscription_plans
seller_subscriptions
subscription_payments
invoices
```

---

# 🔑 User Roles

The application supports:

```text
SELLER
BUYER
```

### Buyer

Can:

- Browse products
- Search products
- View product details
- Add products to cart
- Add products to wishlist
- Place orders
- Make payments
- View order history
- Cancel eligible orders
- Review products

### Seller

Can:

- Manage company
- Add products
- Edit products
- Delete products
- View company products
- Manage subscriptions
- Monitor uploaded products
- Access seller dashboard

---

# 🔄 Core Marketplace Workflow

```text
User Registration
       ↓
User Login
       ↓
JWT Authentication
       ↓
       ├───────────────────┐
       ↓                   ↓
     BUYER               SELLER
       │                   │
       ↓                   ↓
Browse Products        Company
       │                   │
       ↓                   ↓
Product Details       Subscription
       │                   │
       ↓                   ↓
Cart                  Add Products
       │                   │
       ↓                   ↓
Checkout              Product Management
       │                   │
       ↓                   ↓
Payment               Seller Dashboard
       │
       ↓
Orders
       │
       ↓
Reviews
```

---

# 🔐 Security

The application uses:

- JWT authentication
- Password hashing
- Protected API endpoints
- Protected frontend routes
- Role-based access
- Authenticated API requests
- Bean validation

Passwords are stored using secure password hashing rather than plain text.

---

# 🚀 Running the Project

## Backend

Navigate to the backend directory:

```bash
cd indusmart-backend
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

Backend:

```text
http://localhost:8081
```

Swagger UI:

```text
http://localhost:8081/swagger-ui/index.html
```

---

## Frontend

Open another terminal:

```bash
cd indusmart-frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Frontend:

```text
http://localhost:5173
```

---

# 🤖 AI Vision

IndusMart AI is designed to evolve into an intelligent industrial marketplace with features such as:

- AI-powered product recommendations
- AI chatbot assistance
- Intelligent product discovery
- Personalized marketplace experiences
- Business analytics
- Seller insights

---

# 📈 Future Enhancements

The core marketplace is completed. Future versions may include:

- Advanced AI recommendation engine
- AI chatbot
- Advanced analytics
- Admin dashboard
- Notifications
- Invoice PDF generation
- Advanced product filtering
- Improved mobile experience
- Real-time communication
- Advanced seller analytics
- Additional UI improvements

---

# 👨‍💻 Developer

**Prajwal Shet**

GitHub:

**Prajwal5018**

---

# 📜 License

This project is currently intended for educational, portfolio, and demonstration purposes.

---

# ⭐ IndusMart AI

IndusMart AI brings industrial equipment buying, selling, and rental into one centralized digital marketplace.

If you find this project interesting, consider giving the repository a ⭐ on GitHub.
