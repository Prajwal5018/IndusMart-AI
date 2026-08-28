function WishlistItem({

    item,

    onRemove,

    onMoveToCart

}) {

    return (

        <div className="bg-white rounded-2xl shadow-lg overflow-hidden hover:shadow-2xl transition">

            <img

                src={
                    item.product.imageUrl ||
                    "https://placehold.co/600x400?text=No+Image"
                }

                alt={item.product.productName}

                className="w-full h-56 object-cover"

            />

            <div className="p-6">

                <div className="flex justify-between">

                    <span className="bg-pink-100 text-pink-600 px-3 py-1 rounded-full">

                        Wishlist

                    </span>

                    <span className="text-gray-500">

                        {item.product.brand}

                    </span>

                </div>

                <h2 className="text-2xl font-bold mt-4">

                    {item.product.productName}

                </h2>

                <p className="text-gray-600 mt-3 line-clamp-3">

                    {item.product.description}

                </p>

                <div className="mt-6 space-y-2">

                    <p>

                        <strong>Category :</strong>

                        {" "}

                        {item.product.category}

                    </p>

                    <p>

                        <strong>Company :</strong>

                        {" "}

                        {item.product.company?.companyName}

                    </p>

                    <p>

                        <strong>Price :</strong>

                        {" "}

                        ₹ {item.product.purchasePrice}

                    </p>

                    {

                        item.product.availableForRent && (

                            <p>

                                <strong>Rental :</strong>

                                {" "}

                                ₹ {item.product.rentalPricePerDay}

                                {" "} / Day

                            </p>

                        )

                    }

                </div>

                <div className="grid grid-cols-2 gap-4 mt-8">

                    <button

                        onClick={() =>
                            onMoveToCart(item)
                        }

                        className="bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg"

                    >

                        Move To Cart

                    </button>

                    <button

                        onClick={() =>
                            onRemove(item)
                        }

                        className="bg-red-600 hover:bg-red-700 text-white py-3 rounded-lg"

                    >

                        Remove

                    </button>

                </div>

            </div>

        </div>

    );

}

export default WishlistItem;