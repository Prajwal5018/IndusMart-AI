import { Routes, Route } from "react-router-dom";

import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";

import ProtectedRoute from "./ProtectedRoute";

import BuyerDashboard from "../pages/BuyerDashboard";

import ProductList from "../pages/products/ProductList";
import ProductDetails from "../pages/products/ProductDetails";

import CartPage from "../pages/cart/CartPage";
import WishlistPage from "../pages/wishlist/WishlistPage";

import OrderHistory from "../pages/orders/OrderHistory";
import CheckoutPage from "../pages/orders/CheckoutPage";
import PaymentPage from "../pages/orders/PaymentPage";

/* Seller Pages */
import SellerDashboard from "../pages/seller/SellerDashboard";
import AddProduct from "../pages/seller/AddProduct";
import EditProduct from "../pages/seller/EditProduct";

import SellerProducts from "../pages/seller/SellerProducts";

function AppRoutes() {

    return (

        <Routes>

            {/* Home */}
            <Route
                path="/"
                element={<Home />}
            />

            {/* Authentication */}
            <Route
                path="/login"
                element={<Login />}
            />

            <Route
                path="/register"
                element={<Register />}
            />

            {/* Buyer Dashboard */}
            <Route
                path="/buyer/dashboard"
                element={
                    <ProtectedRoute>
                        <BuyerDashboard />
                    </ProtectedRoute>
                }
            />

            {/* Products */}
            <Route
                path="/products"
                element={<ProductList />}
            />

            <Route
                path="/products/:id"
                element={<ProductDetails />}
            />

            {/* Cart */}
            <Route
                path="/cart"
                element={<CartPage />}
            />

            {/* Wishlist */}
            <Route
                path="/wishlist"
                element={<WishlistPage />}
            />

            {/* Orders */}
            <Route
                path="/orders"
                element={<OrderHistory />}
            />

            <Route
                path="/checkout"
                element={<CheckoutPage />}
            />

            <Route
                path="/payment"
                element={<PaymentPage />}
            />

            {/* Seller */}
            <Route
                path="/seller/dashboard"
                element={
                    <ProtectedRoute>
                        <SellerDashboard />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/seller/add-product"
                element={
                    <ProtectedRoute>
                        <AddProduct />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/seller/edit-product/:id"
                element={
                    <ProtectedRoute>
                        <EditProduct />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/seller/products"
                element={
                    <ProtectedRoute>
                        <SellerProducts />
                    </ProtectedRoute>
                }
            />

        </Routes>

    );

}

export default AppRoutes;