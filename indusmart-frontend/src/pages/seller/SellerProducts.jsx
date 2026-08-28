import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import sellerService from "../../services/sellerService";

function SellerProducts() {

    const navigate = useNavigate();

    /*
     * Temporary
     * Later from JWT
     */
    const companyId = 1;

    const [products, setProducts] = useState([]);

    const [filteredProducts, setFilteredProducts] = useState([]);

    const [loading, setLoading] = useState(true);

    const [search, setSearch] = useState("");

    useEffect(() => {

        loadProducts();

    }, []);

    useEffect(() => {

        const keyword = search.toLowerCase();

        setFilteredProducts(

            products.filter(product =>

                product.productName.toLowerCase().includes(keyword)

                ||

                product.brand.toLowerCase().includes(keyword)

                ||

                product.category.toLowerCase().includes(keyword)

            )

        );

    }, [search, products]);

    const loadProducts = async () => {

        try {

            const response =

                await sellerService.getProducts(companyId);

            setProducts(response);

            setFilteredProducts(response);

        }

        catch (error) {

            console.log(error);

        }

        finally {

            setLoading(false);

        }

    };

    const deleteProduct = async (id) => {

        const confirmDelete =

            window.confirm(

                "Delete this product?"

            );

        if (!confirmDelete) {

            return;

        }

        try {

            const message =

                await sellerService.deleteProduct(id);

            alert(message);

            loadProducts();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to delete product."

            );

        }

    };

    if (loading) {

        return (

            <div className="min-h-screen flex justify-center items-center">

                <h1 className="text-3xl font-bold">

                    Loading Products...

                </h1>

            </div>

        );

    }

    return (

        <div className="min-h-screen bg-gray-100">

            <div className="max-w-7xl mx-auto py-10 px-6">

                <div className="flex justify-between items-center mb-10">

                    <div>

                        <h1 className="text-4xl font-bold">

                            My Products

                        </h1>

                        <p className="text-gray-500 mt-2">

                            Manage all company products

                        </p>

                    </div>

                    <button

                        onClick={() =>

                            navigate("/seller/add-product")

                        }

                        className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg"

                    >

                        + Add Product

                    </button>

                </div>

                <div className="mb-8">

                    <input

                        type="text"

                        placeholder="Search products..."

                        value={search}

                        onChange={(e) =>

                            setSearch(e.target.value)

                        }

                        className="w-full border rounded-lg px-5 py-3"

                    />

                </div>

                {

                    filteredProducts.length === 0 ?

                        (

                            <div className="bg-white rounded-xl shadow p-10 text-center">

                                <h2 className="text-2xl font-bold">

                                    No Products Found

                                </h2>

                            </div>

                        )

                        :

                        (

                            <div className="grid lg:grid-cols-3 md:grid-cols-2 gap-8">

                                                            {

                                                                filteredProducts.map(product => (

                                                                    <div

                                                                        key={product.id}

                                                                        className="bg-white rounded-2xl shadow-lg overflow-hidden"

                                                                    >

                                                                        <img

                                                                            src={
                                                                                product.imageUrl ||
                                                                                "https://via.placeholder.com/400x250?text=No+Image"
                                                                            }

                                                                            alt={product.productName}

                                                                            className="w-full h-56 object-cover"

                                                                        />

                                                                        <div className="p-6">

                                                                            <h2 className="text-2xl font-bold">

                                                                                {product.productName}

                                                                            </h2>

                                                                            <p className="text-gray-500 mt-2">

                                                                                {product.brand}

                                                                            </p>

                                                                            <p className="text-gray-500">

                                                                                {product.category}

                                                                            </p>

                                                                            <div className="mt-5 space-y-2">

                                                                                <p>

                                                                                    <span className="font-semibold">

                                                                                        Purchase Price:

                                                                                    </span>

                                                                                    {" "}₹ {product.purchasePrice}

                                                                                </p>

                                                                                <p>

                                                                                    <span className="font-semibold">

                                                                                        Rental / Day:

                                                                                    </span>

                                                                                    {" "}₹ {product.rentalPricePerDay}

                                                                                </p>

                                                                                <p>

                                                                                    <span className="font-semibold">

                                                                                        Quantity:

                                                                                    </span>

                                                                                    {" "}

                                                                                    {product.quantity}

                                                                                </p>

                                                                            </div>

                                                                            <div className="flex gap-3 mt-5">

                                                                                {

                                                                                    product.availableForSale &&

                                                                                    <span className="bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm">

                                                                                        Sale

                                                                                    </span>

                                                                                }

                                                                                {

                                                                                    product.availableForRent &&

                                                                                    <span className="bg-blue-100 text-blue-700 px-3 py-1 rounded-full text-sm">

                                                                                        Rent

                                                                                    </span>

                                                                                }

                                                                            </div>

                                                                            <div className="flex gap-4 mt-8">

                                                                                <button

                                                                                    onClick={() =>

                                                                                        navigate(

                                                                                            `/seller/edit-product/${product.id}`

                                                                                        )

                                                                                    }

                                                                                    className="flex-1 bg-yellow-500 hover:bg-yellow-600 text-white py-3 rounded-lg font-semibold"

                                                                                >

                                                                                    Edit

                                                                                </button>

                                                                                <button

                                                                                    onClick={() =>

                                                                                        deleteProduct(product.id)

                                                                                    }

                                                                                    className="flex-1 bg-red-600 hover:bg-red-700 text-white py-3 rounded-lg font-semibold"

                                                                                >

                                                                                    Delete

                                                                                </button>

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

                            export default SellerProducts;