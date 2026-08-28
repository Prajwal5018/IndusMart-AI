import { useEffect, useState } from "react";
import orderService from "../../services/orderService";

function OrderHistory() {

    // Temporary
    // Later this will come from JWT
    const buyerId = 2;

    const [orders, setOrders] = useState([]);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadOrders();

    }, []);

    const loadOrders = async () => {

        try {

            const response = await orderService.getBuyerOrders(

                buyerId

            );

            setOrders(response);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const cancelOrder = async (orderId) => {

        const confirmCancel = window.confirm(

            "Are you sure you want to cancel this order?"

        );

        if (!confirmCancel) {

            return;

        }

        try {

            const message = await orderService.cancelOrder(

                orderId

            );

            alert(message);

            loadOrders();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to cancel order."

            );

        }

    };

    const paymentColor = (status) => {

        switch (status) {

            case "SUCCESS":
                return "text-green-600";

            case "FAILED":
                return "text-red-600";

            case "REFUNDED":
                return "text-purple-600";

            default:
                return "text-orange-600";

        }

    };

    const orderColor = (status) => {

        switch (status) {

            case "DELIVERED":
                return "text-green-600";

            case "SHIPPED":
                return "text-blue-600";

            case "CONFIRMED":
                return "text-indigo-600";

            case "CANCELLED":
                return "text-red-600";

            default:
                return "text-orange-600";

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Orders...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto py-10 px-6">

                <h1 className="text-4xl font-bold mb-10">

                    My Orders

                </h1>

                {

                    orders.length === 0 ?

                        (

                            <div className="bg-white rounded-xl shadow p-12 text-center">

                                <h2 className="text-2xl font-semibold">

                                    No Orders Found

                                </h2>

                            </div>

                        )

                        :

                        (

                            <div className="space-y-8">

                                {

                                    orders.map(order => (

                                        <div

                                            key={order.id}

                                            className="bg-white rounded-2xl shadow-lg p-8"

                                        >

                                            <div className="flex flex-col lg:flex-row justify-between gap-8">

                                                <div>

                                                    <h2 className="text-2xl font-bold">

                                                        {order.orderNumber}

                                                    </h2>

                                                    <p className="text-gray-500 mt-2">

                                                        {new Date(

                                                            order.orderDate

                                                        ).toLocaleString()}

                                                    </p>

                                                    <p className="mt-4">

                                                        <span className="font-semibold">

                                                            Order Type :

                                                        </span>

                                                        {" "}

                                                        {order.orderType}

                                                    </p>

                                                    <p className="mt-2">

                                                        <span className="font-semibold">

                                                            Total :

                                                        </span>

                                                        {" "}

                                                        ₹ {order.totalAmount}

                                                    </p>

                                                </div>

                                                <div>

                                                    <p className="font-semibold">

                                                        Order Status

                                                    </p>

                                                    <h2

                                                        className={`text-2xl font-bold ${orderColor(order.orderStatus)}`}

                                                    >

                                                        {order.orderStatus}

                                                    </h2>

                                                    <p className="font-semibold mt-6">

                                                        Payment

                                                    </p>

                                                    <h2

                                                        className={`text-xl font-bold ${paymentColor(order.paymentStatus)}`}

                                                    >

                                                        {order.paymentStatus}

                                                    </h2>

                                                </div>

                                                <div className="flex flex-col gap-4">

                                                    <button

                                                        className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg"

                                                        onClick={() =>

                                                            alert(

                                                                "Order Details page will be added next."

                                                            )

                                                        }

                                                    >

                                                        View Details

                                                    </button>

                                                    {

                                                        order.paymentStatus === "PENDING" &&

                                                        <button

                                                            className="bg-green-600 hover:bg-green-700 text-white px-6 py-3 rounded-lg"

                                                            onClick={() =>

                                                                alert(

                                                                    "Redirect to Payment Page."

                                                                )

                                                            }

                                                        >

                                                            Pay Now

                                                        </button>

                                                    }

                                                    {

                                                        order.orderStatus !== "DELIVERED" &&

                                                        order.orderStatus !== "CANCELLED" &&

                                                        <button

                                                            className="bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg"

                                                            onClick={() =>

                                                                cancelOrder(

                                                                    order.id

                                                                )

                                                            }

                                                        >

                                                            Cancel Order

                                                        </button>

                                                    }

                                                </div>

                                            </div>

                                        </div>

                                    ))

                                }

                            </div>

                        )

                }

            </div>

        </div>

    );

}

export default OrderHistory;