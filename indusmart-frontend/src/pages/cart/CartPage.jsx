import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import cartService from "../../services/cartService";

function CartPage() {

    // Temporary
    // Later we'll take buyerId from JWT
    const buyerId = 2;

    const [cartItems, setCartItems] = useState([]);

    const [total, setTotal] = useState(0);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadCart();

    }, []);

    const loadCart = async () => {

        try {

            const items = await cartService.getCart(buyerId);

            setCartItems(items);

            const amount = await cartService.getCartTotal(buyerId);

            setTotal(amount);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const updateQuantity = async (

        cartId,

        quantity

    ) => {

        if (quantity <= 0) return;

        try {

            await cartService.updateQuantity(

                cartId,

                quantity

            );

            loadCart();

        }

        catch (error) {

            alert("Unable to update quantity");

        }

    };

    const removeItem = async (cartId) => {

        try {

            await cartService.removeItem(cartId);

            loadCart();

        }

        catch (error) {

            alert("Unable to remove item");

        }

    };

    const clearCart = async () => {

        if (!window.confirm("Clear entire cart?")) {

            return;

        }

        try {

            await cartService.clearCart(buyerId);

            loadCart();

        }

        catch (error) {

            alert("Unable to clear cart");

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl">

                    Loading Cart...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto py-10 px-8">

                <div className="flex justify-between items-center">

                    <h1 className="text-4xl font-bold">

                        Shopping Cart

                    </h1>

                    {

                        cartItems.length > 0 &&

                        <button

                            onClick={clearCart}

                            className="bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg"

                        >

                            Clear Cart

                        </button>

                    }

                </div>

                {

                    cartItems.length === 0 ?

                        (

                            <div className="bg-white mt-10 rounded-xl shadow p-10 text-center">

                                <h2 className="text-2xl font-semibold">

                                    Cart is Empty

                                </h2>

                                <Link

                                    to="/products"

                                    className="inline-block mt-6 bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-lg"

                                >

                                    Browse Products

                                </Link>

                            </div>

                        )

                        :

                        (

                            <>

                                <div className="space-y-6 mt-10">

                                    {

                                        cartItems.map(item => (

                                            <div

                                                key={item.id}

                                                className="bg-white rounded-xl shadow p-6 flex justify-between items-center"

                                            >

                                                <div>

                                                    <h2 className="text-2xl font-bold">

                                                        {item.product.productName}

                                                    </h2>

                                                    <p className="text-gray-500">

                                                        {item.product.brand}

                                                    </p>

                                                    {

                                                        item.rental ?

                                                            (

                                                                <p className="text-green-700 font-semibold mt-2">

                                                                    Rental •

                                                                    {item.rentalDays}

                                                                    day(s)

                                                                </p>

                                                            )

                                                            :

                                                            (

                                                                <p className="text-blue-700 font-semibold mt-2">

                                                                    Purchase

                                                                </p>

                                                            )

                                                    }

                                                </div>

                                                <div className="text-center">

                                                    <label>

                                                        Qty

                                                    </label>

                                                    <input

                                                        type="number"

                                                        min="1"

                                                        value={item.quantity}

                                                        onChange={(e) =>

                                                            updateQuantity(

                                                                item.id,

                                                                Number(e.target.value)

                                                            )

                                                        }

                                                        className="border rounded-lg w-20 px-3 py-2 block mt-2"

                                                    />

                                                </div>

                                                <div>

                                                    <h2 className="text-2xl font-bold">

                                                        ₹ {item.totalPrice}

                                                    </h2>

                                                </div>

                                                <button

                                                    onClick={() =>

                                                        removeItem(item.id)

                                                    }

                                                    className="bg-red-600 hover:bg-red-700 text-white px-5 py-3 rounded-lg"

                                                >

                                                    Remove

                                                </button>

                                            </div>

                                        ))

                                    }

                                </div>

                                <div className="bg-white rounded-xl shadow mt-10 p-8">

                                    <div className="flex justify-between items-center">

                                        <h2 className="text-3xl font-bold">

                                            Grand Total

                                        </h2>

                                        <h2 className="text-4xl font-bold text-green-700">

                                            ₹ {total}

                                        </h2>

                                    </div>

                                    <Link

                                        to="/checkout"

                                        className="block mt-8"

                                    >

                                        <button

                                            className="w-full bg-blue-600 hover:bg-blue-700 text-white py-4 rounded-lg text-xl"

                                        >

                                            Proceed To Checkout

                                        </button>

                                    </Link>

                                </div>

                            </>

                        )

                }

            </div>

        </div>

    );

}

export default CartPage;