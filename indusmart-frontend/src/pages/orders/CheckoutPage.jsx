import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import cartService from "../../services/cartService";
import orderService from "../../services/orderService";

function CheckoutPage() {

    // Temporary
    // Later take buyerId from JWT
    const buyerId = 2;

    const navigate = useNavigate();

    const [cartTotal, setCartTotal] = useState(0);

    const [loading, setLoading] = useState(true);

    const [placingOrder, setPlacingOrder] = useState(false);

    const [shippingAddress, setShippingAddress] = useState("");

    useEffect(() => {

        loadCartTotal();

    }, []);

    const loadCartTotal = async () => {

        try {

            const total = await cartService.getCartTotal(

                buyerId

            );

            setCartTotal(total);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const placeOrder = async () => {

        if (shippingAddress.trim() === "") {

            alert("Please enter shipping address.");

            return;

        }

        setPlacingOrder(true);

        try {

            const response = await orderService.checkout({

                buyerId,

                shippingAddress

            });

            alert(response);

            navigate("/orders");

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to place order."

            );

        }

        finally {

            setPlacingOrder(false);

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Checkout...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-3xl mx-auto py-12 px-6">

                <div className="bg-white rounded-2xl shadow-lg p-10">

                    <h1 className="text-4xl font-bold mb-10">

                        Checkout

                    </h1>

                    <div className="mb-8">

                        <label className="font-semibold">

                            Shipping Address

                        </label>

                        <textarea

                            rows="5"

                            value={shippingAddress}

                            onChange={(e) =>

                                setShippingAddress(

                                    e.target.value

                                )

                            }

                            className="w-full mt-3 border rounded-lg px-4 py-3"

                            placeholder="Enter complete shipping address"

                        />

                    </div>

                    <div className="bg-gray-100 rounded-xl p-6 mb-8">

                        <div className="flex justify-between">

                            <span className="text-xl">

                                Cart Total

                            </span>

                            <span className="text-2xl font-bold text-green-700">

                                ₹ {cartTotal}

                            </span>

                        </div>

                    </div>

                    <button

                        onClick={placeOrder}

                        disabled={placingOrder}

                        className="w-full bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-xl text-xl font-semibold"

                    >

                        {

                            placingOrder

                                ? "Placing Order..."

                                : "Place Order"

                        }

                    </button>

                </div>

            </div>

        </div>

    );

}

export default CheckoutPage;