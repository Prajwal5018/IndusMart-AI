import { useEffect, useState } from "react";
import buyerDashboardService from "../services/buyerDashboardService";

function BuyerDashboard() {

    const [dashboard, setDashboard] = useState(null);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    useEffect(() => {

        loadDashboard();

    }, []);

    const loadDashboard = async () => {

        try {

            /*
             Temporary buyer id.
             Replace with JWT user id later.
            */

            const response =
                await buyerDashboardService.getBuyerDashboard(1);

            setDashboard(response);

        }

        catch (err) {

            console.error(err);

            setError("Unable to load dashboard.");

        }

        finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl">

                    Loading Dashboard...

                </h1>

            </div>

        );

    }

    if (error) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-2xl text-red-600">

                    {error}

                </h1>

            </div>

        );

    }

    if (!dashboard) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-2xl">

                    No dashboard data found.

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100 p-10">

            <h1 className="text-4xl font-bold mb-10">

                Buyer Dashboard

            </h1>

            <div className="grid md:grid-cols-3 gap-8">

                <DashboardCard
                    title="Total Orders"
                    value={dashboard.totalOrders}
                    color="text-blue-600"
                />

                <DashboardCard
                    title="Wishlist"
                    value={dashboard.wishlistItems}
                    color="text-pink-600"
                />

                <DashboardCard
                    title="Cart Items"
                    value={dashboard.cartItems}
                    color="text-green-600"
                />

                <DashboardCard
                    title="Reviews"
                    value={dashboard.reviewsWritten}
                    color="text-orange-600"
                />

                <DashboardCard
                    title="Total Spent"
                    value={`₹ ${dashboard.totalSpent}`}
                    color="text-purple-600"
                />

                <DashboardCard
                    title="Delivered Orders"
                    value={dashboard.deliveredOrders}
                    color="text-teal-600"
                />

            </div>

        </div>

    );

}

function DashboardCard({

    title,

    value,

    color

}) {

    return (

        <div className="bg-white rounded-xl shadow p-8">

            <h2 className="text-lg text-gray-500">

                {title}

            </h2>

            <h1 className={`text-4xl font-bold mt-4 ${color}`}>

                {value}

            </h1>

        </div>

    );

}

export default BuyerDashboard;