import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import wishlistService from "../../services/wishlistService";

function WishlistPage() {

    // Temporary
    // Later we'll get buyerId from JWT
    const buyerId = 2;

    const [wishlist, setWishlist] = useState([]);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadWishlist();

    }, []);

    const loadWishlist = async () => {

        try {

            const data = await wishlistService.getWishlist(buyerId);

            setWishlist(data);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const removeItem = async (productId) => {

        try {

            await wishlistService.removeFromWishlist(

                buyerId,

                productId

            );

            loadWishlist();

        }

        catch (error) {

            alert("Unable to remove item");

        }

    };

    const clearWishlist = async () => {

        if (!window.confirm("Clear Wishlist?")) {

            return;

        }

        try {

            await wishlistService.clearWishlist(

                buyerId

            );

            loadWishlist();

        }

        catch (error) {

            alert("Unable to clear wishlist");

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl">

                    Loading Wishlist...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto px-8 py-10">

                <div className="flex justify-between items-center">

                    <h1 className="text-4xl font-bold">

                        My Wishlist

                    </h1>

                    {

                        wishlist.length > 0 &&

                        <button

                            onClick={clearWishlist}

                            className="bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg"

                        >

                            Clear Wishlist

                        </button>

                    }

                </div>

                {

                    wishlist.length === 0 ?

                        (

                            <div className="bg-white rounded-xl shadow mt-10 p-10 text-center">

                                <h2 className="text-2xl font-semibold">

                                    Wishlist is Empty

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

                            <div className="grid lg:grid-cols-4 md:grid-cols-2 gap-8 mt-10">

                                {

                                    wishlist.map(item => (

                                        <div

                                            key={item.id}

                                            className="bg-white rounded-xl shadow hover:shadow-xl overflow-hidden"

                                        >

                                            <img

                                                src={

                                                    item.product.imageUrl ||

                                                    "https://placehold.co/600x400?text=No+Image"

                                                }

                                                alt={item.product.productName}

                                                className="w-full h-52 object-cover"

                                            />

                                            <div className="p-5">

                                                <h2 className="text-xl font-bold">

                                                    {item.product.productName}

                                                </h2>

                                                <p className="text-gray-500 mt-2">

                                                    {item.product.brand}

                                                </p>

                                                <p className="text-sm text-gray-500">

                                                    {item.product.category}

                                                </p>

                                                {

                                                    item.product.availableForSale &&

                                                    <h2 className="text-blue-700 font-bold mt-4">

                                                        ₹ {item.product.purchasePrice}

                                                    </h2>

                                                }

                                                {

                                                    item.product.availableForRent &&

                                                    <h3 className="text-green-700 font-semibold">

                                                        Rent ₹ {item.product.rentalPricePerDay}/day

                                                    </h3>

                                                }

                                                <Link

                                                    to={`/products/${item.product.id}`}

                                                    className="block mt-5"

                                                >

                                                    <button

                                                        className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg"

                                                    >

                                                        View Product

                                                    </button>

                                                </Link>

                                                <button

                                                    onClick={() =>

                                                        removeItem(item.product.id)

                                                    }

                                                    className="w-full mt-3 bg-red-600 hover:bg-red-700 text-white py-3 rounded-lg"

                                                >

                                                    Remove

                                                </button>

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

export default WishlistPage;