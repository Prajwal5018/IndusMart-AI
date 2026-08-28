import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import productService from "../../services/productService";
import cartService from "../../services/cartService";
import wishlistService from "../../services/wishlistService";

function ProductDetails() {

    const { id } = useParams();

    const navigate = useNavigate();

    const buyerId = 2;

    const [product, setProduct] = useState(null);

    const [loading, setLoading] = useState(true);

    const [quantity, setQuantity] = useState(1);

    const [rental, setRental] = useState(false);

    const [rentalDays, setRentalDays] = useState(1);

    useEffect(() => {

        loadProduct();

    }, [id]);

    const loadProduct = async () => {

        try {

            const data = await productService.getProductById(id);

            setProduct(data);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const handleAddToCart = async () => {

        try {

            await cartService.addToCart(

                buyerId,

                product.id,

                quantity,

                rental,

                rentalDays

            );

            alert("Product added to cart.");

        }

        catch (error) {

            alert(error.response?.data || "Unable to add product to cart.");

        }

    };

    const handleWishlist = async () => {

        try {

            await wishlistService.addToWishlist(

                buyerId,

                product.id

            );

            alert("Product added to wishlist.");

        }

        catch (error) {

            alert(error.response?.data || "Unable to add to wishlist.");

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Product...

                </h1>

            </div>

        );

    }

    if (!product) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl text-red-600">

                    Product Not Found

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto px-8 py-10">

                <button

                    onClick={() => navigate(-1)}

                    className="mb-8 bg-gray-700 text-white px-5 py-2 rounded-lg"

                >

                    ← Back

                </button>

                <div className="grid lg:grid-cols-2 gap-10">

                    <div>

                        <img

                            src={

                                product.imageUrl ||

                                "https://placehold.co/700x500?text=No+Image"

                            }

                            alt={product.productName}

                            className="rounded-xl shadow w-full h-[500px] object-cover"

                        />

                    </div>

                    <div>

                        <h1 className="text-4xl font-bold">

                            {product.productName}

                        </h1>

                        <p className="mt-3 text-gray-600">

                            Brand : {product.brand}

                        </p>

                        <p className="text-gray-600">

                            Category : {product.category}

                        </p>

                        <p className="mt-6 text-gray-700 leading-8">

                            {product.description}

                        </p>

                        {

                            product.availableForSale &&

                            <h2 className="text-4xl font-bold text-blue-700 mt-8">

                                ₹ {product.purchasePrice}

                            </h2>

                        }

                        {

                            product.availableForRent &&

                            <h3 className="text-2xl text-green-700 font-semibold mt-3">

                                Rent ₹ {product.rentalPricePerDay} / day

                            </h3>

                        }

                        <p className="mt-5">

                            Available Quantity :

                            <span className="font-bold ml-2">

                                {product.quantity}

                            </span>

                        </p>

                        <div className="mt-8">

                            <label className="font-semibold">

                                Quantity

                            </label>

                            <input

                                type="number"

                                min="1"

                                max={product.quantity}

                                value={quantity}

                                onChange={(e) =>

                                    setQuantity(Number(e.target.value))

                                }

                                className="border rounded-lg px-4 py-2 ml-4 w-24"

                            />

                        </div>

                        {

                            product.availableForRent &&

                            <>

                                <div className="mt-8">

                                    <label>

                                        <input

                                            type="checkbox"

                                            checked={rental}

                                            onChange={(e) =>

                                                setRental(e.target.checked)

                                            }

                                            className="mr-3"

                                        />

                                        Rent this product

                                    </label>

                                </div>

                                {

                                    rental &&

                                    <div className="mt-5">

                                        <label>

                                            Rental Days

                                        </label>

                                        <input

                                            type="number"

                                            min="1"

                                            value={rentalDays}

                                            onChange={(e) =>

                                                setRentalDays(Number(e.target.value))

                                            }

                                            className="border rounded-lg px-4 py-2 ml-4 w-24"

                                        />

                                    </div>

                                }

                            </>

                        }

                        <div className="grid md:grid-cols-3 gap-5 mt-10">

                            <button

                                onClick={handleAddToCart}

                                className="bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg"

                            >

                                Add To Cart

                            </button>

                            <button

                                onClick={handleWishlist}

                                className="bg-pink-600 hover:bg-pink-700 text-white py-3 rounded-lg"

                            >

                                Wishlist

                            </button>

                            <button

                                onClick={() => navigate("/checkout")}

                                className="bg-green-600 hover:bg-green-700 text-white py-3 rounded-lg"

                            >

                                Buy Now

                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default ProductDetails;