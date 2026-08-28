import { Link } from "react-router-dom";

function ProductCard({ product }) {

    return (

        <div className="bg-white rounded-2xl shadow-lg overflow-hidden hover:shadow-2xl transition duration-300">

            <img

                src={
                    product.imageUrl ||
                    "https://placehold.co/600x400?text=No+Image"
                }

                alt={product.productName}

                className="w-full h-56 object-cover"

            />

            <div className="p-6">

                <div className="flex justify-between items-center">

                    <span className="bg-blue-100 text-blue-700 px-3 py-1 rounded-full text-sm">

                        {product.category}

                    </span>

                    <span className="text-sm text-gray-500">

                        {product.brand}

                    </span>

                </div>

                <h2 className="text-2xl font-bold mt-4">

                    {product.productName}

                </h2>

                <p className="text-gray-600 mt-3 line-clamp-3">

                    {product.description}

                </p>

                <div className="mt-5 space-y-2">

                    <p>

                        <span className="font-semibold">

                            Purchase :

                        </span>

                        ₹ {product.purchasePrice}

                    </p>

                    <p>

                        <span className="font-semibold">

                            Rental :

                        </span>

                        ₹ {product.rentalPricePerDay} / day

                    </p>

                    <p>

                        <span className="font-semibold">

                            Quantity :

                        </span>

                        {product.quantity}

                    </p>

                    <p>

                        <span className="font-semibold">

                            Company :

                        </span>

                        {product.company?.companyName}

                    </p>

                </div>

                <div className="flex flex-wrap gap-3 mt-6">

                    {

                        product.availableForSale && (

                            <button

                                className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg"

                            >

                                Buy Now

                            </button>

                        )

                    }

                    {

                        product.availableForRent && (

                            <button

                                className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg"

                            >

                                Rent

                            </button>

                        )

                    }

                    <button

                        className="bg-pink-500 hover:bg-pink-600 text-white px-4 py-2 rounded-lg"

                    >

                        Wishlist

                    </button>

                    <button

                        className="bg-yellow-500 hover:bg-yellow-600 text-white px-4 py-2 rounded-lg"

                    >

                        Cart

                    </button>

                </div>

                <Link

                    to={`/products/${product.id}`}

                    className="block text-center mt-6 bg-gray-900 hover:bg-black text-white py-3 rounded-lg"

                >

                    View Details

                </Link>

            </div>

        </div>

    );

}

export default ProductCard;