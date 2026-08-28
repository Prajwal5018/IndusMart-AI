import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import paymentService from "../../services/paymentService";
import orderService from "../../services/orderService";

function PaymentPage() {

    const navigate = useNavigate();

    const location = useLocation();

    const orderId = location.state?.orderId;

    const [order, setOrder] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        if (!orderId) {

            navigate("/orders");

            return;

        }

        loadOrder();

    }, []);

    const loadOrder = async () => {

        try {

            const response = await orderService.getOrder(orderId);

            setOrder(response);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const payNow = async () => {

        try {

            const paymentOrder = await paymentService.createOrder({

                orderId: order.id,

                amount: order.totalAmount,

                customerName: order.buyer.fullName,

                customerEmail: order.buyer.email,

                customerPhone: order.buyer.phoneNumber

            });

            const options = {

                key: paymentOrder.key,

                amount: paymentOrder.amount,

                currency: paymentOrder.currency,

                name: "IndusMart AI",

                description: "Industrial Equipment Payment",

                order_id: paymentOrder.razorpayOrderId,

                handler: async function (response) {

                    try {

                        await paymentService.verifyPayment(

                            order.id,

                            response.razorpay_order_id,

                            response.razorpay_payment_id,

                            response.razorpay_signature

                        );

                        alert("Payment Successful");

                        navigate("/orders");

                    }

                    catch (error) {

                        alert("Payment Verification Failed");

                    }

                },

                prefill: {

                    name: order.buyer.fullName,

                    email: order.buyer.email,

                    contact: order.buyer.phoneNumber

                },

                theme: {

                    color: "#2563EB"

                }

            };

            const razorpay = new window.Razorpay(options);

            razorpay.open();

        }

        catch (error) {

            alert("Unable to initiate payment");

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl">

                    Loading Payment...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-2xl mx-auto py-12">

                <div className="bg-white rounded-2xl shadow-xl p-10">

                    <h1 className="text-4xl font-bold mb-10">

                        Payment

                    </h1>

                    <div className="space-y-6">

                        <div className="flex justify-between">

                            <span>Order Number</span>

                            <span>

                                {order.orderNumber}

                            </span>

                        </div>

                        <div className="flex justify-between">

                            <span>Order Type</span>

                            <span>

                                {order.orderType}

                            </span>

                        </div>

                        <div className="flex justify-between">

                            <span>Payment Status</span>

                            <span>

                                {order.paymentStatus}

                            </span>

                        </div>

                        <div className="flex justify-between text-2xl font-bold">

                            <span>

                                Total Amount

                            </span>

                            <span className="text-green-700">

                                ₹ {order.totalAmount}

                            </span>

                        </div>

                    </div>

                    <button

                        onClick={payNow}

                        className="w-full mt-10 bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-xl text-xl"

                    >

                        Pay With Razorpay

                    </button>

                </div>

            </div>

        </div>

    );

}

export default PaymentPage;