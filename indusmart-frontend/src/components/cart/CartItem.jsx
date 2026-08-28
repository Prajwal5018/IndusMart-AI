function CartItem({

    item,

    onIncrease,

    onDecrease,

    onRemove

}) {

    return (

        <div className="bg-white rounded-2xl shadow-lg p-6">

            <div className="grid md:grid-cols-5 gap-6 items-center">

                {/* Image */}

                <img

                    src={
                        item.product.imageUrl ||
                        "https://placehold.co/300x250?text=No+Image"
                    }

                    alt={item.product.productName}

                    className="w-full h-40 object-cover rounded-xl"

                />

                {/* Details */}

                <div className="md:col-span-2">

                    <h2 className="text-2xl font-bold">

                        {item.product.productName}

                    </h2>

                    <p className="text-gray-500 mt-2">

                        {item.product.company?.companyName}

                    </p>

                    <p className="mt-2">

                        Brand :

                        <strong>

                            {" "}

                            {item.product.brand}

                        </strong>

                    </p>

                    <p>

                        Category :

                        <strong>

                            {" "}

                            {item.product.category}

                        </strong>

                    </p>

                    {

                        item.rental && (

                            <p className="text-green-600 mt-2">

                                Rental :

                                {" "}

                                {item.rentalDays}

                                {" "}Day(s)

                            </p>

                        )

                    }

                </div>

                {/* Quantity */}

                <div className="flex flex-col items-center">

                    <h3 className="font-semibold">

                        Quantity

                    </h3>

                    <div className="flex items-center gap-4 mt-4">

                        <button

                            onClick={() =>

                                onDecrease(item)

                            }

                            className="bg-red-500 hover:bg-red-600 text-white w-10 h-10 rounded-full"

                        >

                            -

                        </button>

                        <span className="text-2xl font-bold">

                            {item.quantity}

                        </span>

                        <button

                            onClick={() =>

                                onIncrease(item)

                            }

                            className="bg-green-600 hover:bg-green-700 text-white w-10 h-10 rounded-full"

                        >

                            +

                        </button>

                    </div>

                </div>

                {/* Price */}

                <div className="text-center">

                    <h2 className="text-3xl font-bold text-blue-700">

                        ₹ {item.totalPrice}

                    </h2>

                    <button

                        onClick={() =>

                            onRemove(item.id)

                        }

                        className="bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg mt-6"

                    >

                        Remove

                    </button>

                </div>

            </div>

        </div>

    );

}

export default CartItem;