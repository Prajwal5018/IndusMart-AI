import { useEffect, useState } from "react";

import sellerService from "../../services/sellerService";

function SellerDashboard() {

    /*
     * Temporary
     * Later get companyId from JWT
     */
    const companyId = 1;

    const [dashboard, setDashboard] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadDashboard();

    }, []);

    const loadDashboard = async () => {

        try {

            const response =
                await sellerService.getDashboard(companyId);

            setDashboard(response);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Seller Dashboard...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto px-8 py-10">

                <div className="mb-10">

                    <h1 className="text-4xl font-bold">

                        Seller Dashboard

                    </h1>

                    <p className="text-gray-600 mt-2">

                        {dashboard.companyName}

                    </p>

                </div>

                <div className="grid lg:grid-cols-4 md:grid-cols-2 gap-6">

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Total Products

                        </h3>

                        <h1 className="text-4xl font-bold text-blue-600 mt-3">

                            {dashboard.totalProducts}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Available Products

                        </h3>

                        <h1 className="text-4xl font-bold text-green-600 mt-3">

                            {dashboard.availableProducts}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Out Of Stock

                        </h3>

                        <h1 className="text-4xl font-bold text-red-600 mt-3">

                            {dashboard.outOfStockProducts}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Low Stock

                        </h3>

                        <h1 className="text-4xl font-bold text-orange-500 mt-3">

                            {dashboard.lowStockProducts}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Total Orders

                        </h3>

                        <h1 className="text-4xl font-bold text-indigo-600 mt-3">

                            {dashboard.totalOrders}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Pending Orders

                        </h3>

                        <h1 className="text-4xl font-bold text-yellow-500 mt-3">

                            {dashboard.pendingOrders}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Confirmed Orders

                        </h3>

                        <h1 className="text-4xl font-bold text-blue-500 mt-3">

                            {dashboard.confirmedOrders}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Shipped Orders

                        </h3>

                        <h1 className="text-4xl font-bold text-cyan-600 mt-3">

                            {dashboard.shippedOrders}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Delivered Orders

                        </h3>

                        <h1 className="text-4xl font-bold text-green-700 mt-3">

                            {dashboard.deliveredOrders}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Cancelled Orders

                        </h3>

                        <h1 className="text-4xl font-bold text-red-700 mt-3">

                            {dashboard.cancelledOrders}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Wishlist Count

                        </h3>

                        <h1 className="text-4xl font-bold text-pink-600 mt-3">

                            {dashboard.wishlistCount}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-6">

                        <h3 className="text-gray-500">

                            Total Reviews

                        </h3>

                        <h1 className="text-4xl font-bold text-purple-600 mt-3">

                            {dashboard.totalReviews}

                        </h1>

                    </div>

                </div>

                <div className="grid md:grid-cols-2 gap-8 mt-10">

                    <div className="bg-white rounded-xl shadow p-8">

                        <h2 className="text-2xl font-bold">

                            Revenue

                        </h2>

                        <h1 className="text-5xl font-bold text-green-700 mt-6">

                            ₹ {dashboard.totalRevenue}

                        </h1>

                    </div>

                    <div className="bg-white rounded-xl shadow p-8">

                        <h2 className="text-2xl font-bold">

                            Average Rating

                        </h2>

                        <h1 className="text-5xl font-bold text-yellow-500 mt-6">

                            ⭐ {dashboard.averageRating}

                        </h1>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default SellerDashboard;